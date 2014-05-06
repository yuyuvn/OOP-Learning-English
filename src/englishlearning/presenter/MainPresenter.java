/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.model.IArticle;
import englishlearning.model.model.IUser;
import englishlearning.model.model.IWord;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.ArticleWrapper;
import englishlearning.model.wrapper.UserWrapper;
import englishlearning.util.DataInDisk;
import englishlearning.util.DataInNet;
import englishlearning.util.Lookup;
import englishlearning.views.ArticlesList;
import englishlearning.views.MainContent;
import englishlearning.views.MainWindow;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
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
        if (user == null) user = new WrapperProperty(this, "user", new UserWrapper());
        return user;
    }
//</editor-fold>
    
    private final Collection<IWord> words = new ArrayList<>();
    private Collection<IArticle> articles;
    
    public MainPresenter(V view) {
        super(view);
    }

    public MainPresenter() {
        super();
    }
    
    @Override
    protected void initialize() {
        setArticlesListData();
        getView().userProperty().bindBidirectional(userProperty());
        MainContent mainContent = getView().getMainContent();
        ArticlesList articlesList = mainContent.getArticlesList();

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
        
        // user login susscess
        userProperty().fireValueChangedEvent();
        userProperty().addListener((ObservableValue<? extends IUser> observable, IUser oldValue, IUser newValue) -> {
            if (newValue.getUser().getPlayState() == null)
                returnToMain();
            else {
                // TODO resume play state
            }
        });
        
        // filter articles
        articlesList.filterTextProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue != null && !newValue.equals("")) {
                mainContent.setData(articles.stream().filter(a -> a.getArticle().getTitle().toLowerCase().contains(newValue.toLowerCase())).collect(Collectors.toList()));
            } else {
                mainContent.setData(articles);
            }
        });
        
        // user selected a word
        mainContent.getReadArticle().selectedWordProperty().addListener((ObservableValue<? extends IWord> observable, IWord oldValue, IWord newValue) -> {            
            newValue.getWord().setMean(Lookup.get(newValue.getWord()));
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
            String word = newValue.getWord().getWord();
            
            if (!mainContent.getWordList().contains(word)) {
                if(newValue.getWord().getMean()!=null && !newValue.getWord().getMean().equals("")) {
                    mainContent.getWordList().add(word);
                    words.add(newValue);
                }
            }
        });
        
        // bind canCanTest to e.getList().isEmpty()
        mainContent.getListView().getItems().addListener((ListChangeListener.Change e) -> {
            mainContent.setCanTest(!e.getList().isEmpty());
        });
    }
    
    private void setArticlesListData() {
        ExecutorService executor = Executors.newCachedThreadPool();
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
            executor.shutdown();
        });
        
        executor.submit(task);
    }
    
    private void setParsedContent() {
        getView().getMainContent().getReadArticle().setParsedContent("");
        ExecutorService executor = Executors.newCachedThreadPool();
        Task<String> task = new Task<String>() {
        @Override
        protected String call() throws Exception {
            IArticle article = (IArticle) getView().getMainContent().getData();
            return article.getParsedContent();
        }
        };
        
        task.valueProperty().addListener(t -> {
            getView().getMainContent().getReadArticle().setParsedContent(task.getValue());
            executor.shutdown();
        });
        
        executor.submit(task);
    }
    
    private void returnToMain() {
        MainContent mainContent = getView().getMainContent();
        mainContent.setData(articles);
        mainContent.getArticlesList().setFilterText("");
        mainContent.getWordList().clear();
        words.clear();
        mainContent.getArticlesList().clearSelection();
    }
}
