/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.PlayState;
import englishlearning.model.Word;
import englishlearning.model.model.IArticle;
import englishlearning.model.model.IUser;
import englishlearning.model.model.IWord;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.ArticleWrapper;
import englishlearning.model.wrapper.WordWrapper;
import englishlearning.util.DataInDisk;
import englishlearning.util.DataInNet;
import englishlearning.util.Lookup;
import englishlearning.views.ArticlesList;
import englishlearning.views.MainContent;
import englishlearning.views.MainWindow;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Clicia
 * @param <V>
 */
public class MainPresenter<V extends MainWindow> extends Presenter<V> {
    //<editor-fold defaultstate="collapsed" desc="IUser user">
    private WrapperProperty<IUser> user;
    public final IUser getUser() { return userProperty().get(); }
    public final void setUser(IUser value) { userProperty().set(value); }
    public final WrapperProperty<IUser> userProperty() {
        if (user == null) user = new WrapperProperty(this, "user");
        return user;
    }
//</editor-fold>
    
    private final Collection<IWord> words = new ArrayList<>();
    private Collection<IArticle> articles = null;
    private ExecutorService executor;
    
    public MainPresenter(V view) {
        super(view);
    }

    public MainPresenter() {
        super();
    }
    
    @Override
    protected void initialize() {
        // 準備する
        getView().userProperty().bindBidirectional(userProperty());
        MainContent mainContent = getView().getMainContent();
        ArticlesList articlesList = mainContent.getArticlesList();
        
        
        // user login susscess
        if (getUser().getUser().getPlayState() != null) {
            resumeState();
        } else {
            returnToMain();
        }
        
        // User click return after read article
        mainContent.setOnReturn(e -> returnToMain());
        
        // User start read article
        articlesList.selectedProperty().addListener(e -> {
            IArticle article = articlesList.getSelectedArticle();
            if (article != null) {
                getUser().getUser().getReadList().putIfAbsent(article.getArticle().getGuid(), 0.0);
                mainContent.setData(article);
                // Don't know why need to fire 2 times.
                // bug some where or java sync like shit?
                getUser().getProperty().fireValueChangedEvent();
                getUser().getProperty().fireValueChangedEvent();
                DataInDisk.saveUserInfo(getUser().getUser());
            }
        });
        mainContent.getReadArticle().articleProperty().addListener(e -> {
                setParsedContent();            
        });
        
        // filter articles
        articlesList.filterTextProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue != null && !newValue.equals("")) {
                if (oldValue != null && !oldValue.equals("") && newValue.contains(oldValue)) {
                    mainContent.setData(
                            mainContent.getArticlesList().getArticles()
                                    .stream().filter(
                                            a -> a.getArticle().getTitle().toLowerCase().contains(
                                                    newValue.toLowerCase())).collect(Collectors.toList())
                    );
                } else {
                mainContent.setData(
                                articles.stream()
                                        .filter(a -> a.getArticle().getTitle().toLowerCase().contains(newValue.toLowerCase()))
                                        .collect(Collectors.toList()));
                }
            } else {
                mainContent.setData(articles);
            }
        });
        
        // user selected a word
        mainContent.getReadArticle().selectedWordProperty().addListener((ObservableValue<? extends IWord> observable, IWord oldValue, IWord newValue) -> {
            if (newValue == oldValue) return;
            mainContent.showPopOver();
            EventHandler<MouseEvent> hd = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mainContent.hidePopOver();
                    Scene scene = (Scene)mouseEvent.getSource();
                    scene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
                }
            };
            getView().getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, hd);
            
            
            if (executor != null && !executor.isTerminated()) {                
                executor.shutdownNow();
            }            
            executor = Executors.newCachedThreadPool();
            
            Task<String> task = new Task<String>() {
                @Override
                protected String call() throws Exception {
                    return Lookup.get(newValue.getWord());
                }
            };

            task.valueProperty().addListener(t -> {
                String word = newValue.getWord().getWord().toLowerCase();
                if (!mainContent.getWordList().contains(word)) {
                    if(task.getValue()!=null && !task.getValue().equals("")) {
                        mainContent.getWordList().add(word);
                        words.add(newValue);
                    }
                }
                mainContent.getReadArticle().selectedWordProperty().fireValueChangedEvent();
                executor.shutdown();
            });

            executor.submit(task);
            
            
        });
        
        // bind canCanTest to e.getList().isEmpty()
        mainContent.getListView().getItems().addListener((ListChangeListener.Change e) -> {
            mainContent.setCanTest(!e.getList().isEmpty());
        });
        
        // User start test
        mainContent.setOnDoTest(e -> {
            PlayState q = new PlayState();
            words.forEach(w -> {
                Word word = w.getWord();
                Lookup.populateOption(word);
                q.add(word);
            });
            getUser().getUser().setPlayState(q);
            DataInDisk.saveUserInfo(getUser().getUser());
            resumeState();
        });
        
        mainContent.getExercise().choiceProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            Integer value = (Integer) newValue;
            if (value > 0) {
                // TODO
                try {
                    IWord word = (IWord) getView().getMainContent().getData();
                    word.getWord().setChoiced(value);
                } catch (Exception e) {
                    
                }
                resumeState();
            }
        });
    }
    
    private void setArticlesListData() {
        if (articles != null) return;
        ExecutorService ect = Executors.newCachedThreadPool();
        Task<Collection<IArticle>> task = new Task<Collection<IArticle>>() {
            @Override
            protected Collection<IArticle> call() throws Exception {
                return DataInNet.getListArticle().stream()
                    .flatMap(a -> Stream.of(new ArticleWrapper(a)))
                    .collect(Collectors.toList());
            }
        };
        
        task.valueProperty().addListener(t -> {
            articles = task.getValue();
            getView().getMainContent().setData(articles);
            ect.shutdown();
        });
        
        ect.submit(task);
    }
    
    private void setParsedContent() {
        getView().getMainContent().getReadArticle().setParsedContent(null);
        ExecutorService ect = Executors.newCachedThreadPool();
        Task<String> task = new Task<String>() {
        @Override
        protected String call() throws Exception {
            IArticle article = (IArticle) getView().getMainContent().getData();
            return article.getParsedContent();
        }
        };
        
        task.valueProperty().addListener(t -> {
            getView().getMainContent().getReadArticle().setParsedContent(task.getValue());
            ect.shutdown();
        });
        
        ect.submit(task);
    }
    
    private void returnToMain() {
        setArticlesListData();
        MainContent mainContent = getView().getMainContent();
        mainContent.setData(articles);
        mainContent.getArticlesList().setFilterText("");
        mainContent.getWordList().clear();
        getView().getMainContent().setProcess(0);
        words.clear();
        mainContent.getArticlesList().clearSelection();
        getUser().getUser().setPlayState(null);
    }
    
    private void resumeState() {
        List<Word> questions = getUser().getUser().getPlayState().stream().filter(w -> w.getChoiced() == 0).collect(toList());
        if (questions.size() > 0 ) {
            Random generator = new Random();
            IWord question = new WordWrapper(questions.get(generator.nextInt(questions.size())));
            getView().getMainContent().setData(question);
            getView().getMainContent().setProcess(1-((double)questions.size()-1)/getUser().getUser().getPlayState().size());
        } else {
            // TODO show result window
            PlayState result = getUser().getUser().getPlayState();
            returnToMain();
        }
        DataInDisk.saveUserInfo(getUser().getUser());
    }
}
