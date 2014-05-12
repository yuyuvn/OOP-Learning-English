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
import englishlearning.model.property.ReadOnlyWrapperProperty;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.WordWrapper;
import englishlearning.util.DataInDisk;
import englishlearning.util.Lookup;
import englishlearning.views.Exercise;
import englishlearning.views.MainContent;
import englishlearning.views.MainWindow;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
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
    
    private ArticlesListPresenter articlesList;
    private ReadArticlePresenter readArticle;
    
    private final Collection<IWord> words = new ArrayList<>();
        
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
        articlesList = new ArticlesListPresenter(mainContent.getArticlesList());
        readArticle = new ReadArticlePresenter(mainContent.getReadArticle());
        
        // user login susscess
        if (getUser() != null) userLoginSusscess();
        userProperty().addListener((ObservableValue<? extends IUser> observable, IUser oldValue, IUser newValue) -> {
            // need check oldValue != newValue because it is wraperProperty
            if (oldValue != newValue && newValue != null) userLoginSusscess();
        });
        
        // can't get list articles
        articlesList.exceptionProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            cantGetListArticle((Throwable)newValue);
        });
        
        // user selected a word
        readArticle.selectedWordProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            userSelectedWord((IWord)newValue);
        });
        
        // User click return after read article or test
        mainContent.setOnReturn(e -> returnToMain());
        getView().getExercise().setOnReturn(e -> {
            returnAfterExercise();
        });
        
        // User start read article
        articlesList.selectedProperty().addListener(e -> {
            userStartReadArticle();
        });
        
        // bind canCanTest to e.getList().isEmpty()
        mainContent.getListView().getItems().addListener((ListChangeListener.Change e) -> {
            mainContent.setCanTest(!e.getList().isEmpty());
        });
        
        // User start test
        mainContent.setOnDoTest(e -> {
            userStartExcercise();
        });
        
        // User choose an option
        getView().getExercise().choiceProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            userChooseOption(newValue);
        });
    }
    
    private void returnToMain() {
        getView().setContent(getView().getMainContent());
        getUser().setPlayState(null);
        MainContent mainContent = getView().getMainContent();
        articlesList.reset();
        mainContent.setData(articlesList);
        mainContent.getWordList().clear();
        words.clear();
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
            getUser().fireValueChangedEvent();
            mainContent.setResult(getUser().getPlayState());
            returnToMain();
            mainContent.showResult();
        }
        DataInDisk.saveUserInfo(getUser().getUser());
    }
    
    protected void cantGetListArticle(Throwable e) {
        Dialogs.create().title("Can't download data")
                .masthead(null)
                .message("Please connect to internet and retry")
                .showException(e);
        getView().closeWindow();
    }
    
    protected void userSelectedWord(IWord word) {
        String mean = word.getMean();
        if (mean != null && !"".equals(mean)) {
            String w = word.getWord().getWord().toLowerCase();
            if (!getView().getMainContent().getWordList().contains(w)) {
                getView().getMainContent().getWordList().add(w);
                words.add(word);
            }
        }
    }
    protected void returnAfterExercise() {
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
    }
    
    protected void userLoginSusscess() {
        if (getUser().getPlayState() != null) {
            resumeState();
        } else {
            returnToMain();
        }
    }
    
    protected void userStartReadArticle() {
        IArticle article = articlesList.getSelectedArticle();
        if (article != null) {
            getUser().getReadList().putIfAbsent(article.getArticle().getGuid(), 0);
            readArticle.setData(article);
            getView().getMainContent().setData(readArticle);
            getUser().fireValueChangedEvent();
            DataInDisk.saveUserInfo(getUser().getUser());
        }
    }
    
    protected void userStartExcercise() {
        try {
            IArticle article = readArticle.getData();
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
    }
    
    protected void userChooseOption(String value) {
        if (value != null) {
            try {
                IWord word = (IWord) getView().getMainContent().getData();
                word.getWord().setChose(value);
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, e);
            }
            resumeState();
        }
    }
}
