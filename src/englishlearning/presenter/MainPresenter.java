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
import englishlearning.views.Exercise;
import englishlearning.views.MainContent;
import englishlearning.views.MainWindow;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.action.Action;
import static org.controlsfx.dialog.Dialog.Actions.*;
import org.controlsfx.dialog.Dialogs;

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
        MainContent mainContent = getView().getMainContent();
        mainContent.userProperty().bindBidirectional(userProperty());
        getView().getExercise().userProperty().bindBidirectional(userProperty());
        ArticlesList articlesList = mainContent.getArticlesList();
        
        
        // user login susscess
        if (getUser().getPlayState() != null) {
            resumeState();
        } else {
            returnToMain();
        }
        
        // User click return after read article or test
        mainContent.setOnReturn(e -> returnToMain());
        getView().getExercise().setOnReturn(e -> {
            Action response = Dialogs.create()
                    .title("Do you really want to go back?")
                    .message("Your process will not be counted")
                    .masthead(null)
                    .actions(new Action[]{YES,NO})
                    .showConfirm();
            if (response == YES) {
                returnToMain();
                DataInDisk.saveUserInfo(getUser().getUser());
            }
        });
        
        // User start read article
        articlesList.selectedProperty().addListener(e -> {
            IArticle article = articlesList.getSelectedArticle();
            if (article != null) {
                getUser().getReadList().putIfAbsent(article.getArticle().getGuid(), 0);
                mainContent.setData(article);
                // Don't know why need to fire 2 times.
                // bug some where or java sync like shit?
                getUser().fireValueChangedEvent();
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
                mainContent.getReadArticle().getSelectedWord().fireValueChangedEvent();
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
            try {
                IArticle article = (IArticle)mainContent.getData();
                PlayState q = new PlayState(article.getGuid());
                words.forEach(w -> {
                    Word word = w.getWord();
                    Lookup.populateOption(word);
                    q.add(word);
                });
                getUser().setPlayState(q);
                resumeState();
            } catch (RuntimeException ex) {
                Action response = Dialogs.create()
                    .owner(this.getView())
                    .title("Can't do test")
                    .masthead(null)
                    .message( ex.getMessage())
                    .showError();
            }
        });
        
        // User choose an option
        getView().getExercise().choiceProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue != null) {
                try {
                    IWord word = (IWord) getView().getMainContent().getData();
                    word.getWord().setChose(newValue);
                } catch (Exception e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, e);
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
        
        task.exceptionProperty().addListener((ObservableValue<? extends Throwable> observable, Throwable oldValue, Throwable newValue) -> {
            Dialogs.create().title("Can't download data")
                    .masthead(null)
                    .message("Please connect to internet and retry")
                    .showException(newValue);
            ect.shutdown();
            getView().closeWindow();
        });
        
        task.valueProperty().addListener(t -> {
            articles = task.getValue();
            getView().getMainContent().setData(articles);
            ect.shutdown();
        });
        
        ect.submit(task);
    }
    
    private void setParsedContent() {
        getView().getMainContent().getReadArticle().setParsedContent(null);
        IArticle article = (IArticle) getView().getMainContent().getData();
        getView().getMainContent().getReadArticle().setParsedContent(article.getContent());
    }
    
    private void returnToMain() {
        getView().setContent(getView().getMainContent());
        getUser().setPlayState(null);
        setArticlesListData();
        MainContent mainContent = getView().getMainContent();
        mainContent.setData(articles);
        mainContent.getArticlesList().setFilterText("");
        mainContent.getWordList().clear();
        words.clear();
        mainContent.getArticlesList().clearSelection();
    }
    
    private void resumeState() {
        List<Word> questions = getUser().getPlayState().stream().filter(w -> w.getChose() == null).collect(toList());
        if (questions.size() > 0 ) {
            Exercise exercise = getView().getExercise();
            getView().setContent(exercise);
            Random generator = new Random();
            IWord question = new WordWrapper(questions.get(generator.nextInt(questions.size())));
            exercise.setData(question);
            getView().getMainContent().setData(question);
            exercise.setProcess(1-((double)questions.size()-1)/getUser().getPlayState().size());
        } else {
            MainContent mainContent = getView().getMainContent();
            getUser().addPoint(getUser().getPlayState().getPoint());
            // 2 times fire event
            getUser().fireValueChangedEvent();
            getUser().fireValueChangedEvent();
            mainContent.setResult(getUser().getPlayState());
            returnToMain();
            mainContent.showResult();
        }
        DataInDisk.saveUserInfo(getUser().getUser());
    }
}
