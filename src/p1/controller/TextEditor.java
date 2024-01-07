package p1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import p1.model.CurrentUser;
import p1.model.texteditor_IO.Load;
import p1.model.texteditor_IO.Save;
import p1.model.texteditor_functions.markovtextgenerator.Create;
import p1.model.texteditor_functions.markovtextgenerator.CreatorInputs;
import p1.model.texteditor_functions.markovtextgenerator.Learn;
import p1.model.texteditor_functions.spellcheck.SpellCheck;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TextEditor implements Initializable {

    private FileChooser fc = new FileChooser();
    private File currentFile;
    protected Learn learn = new Learn();
    private SpellCheck spellCheck;
    private int wordCount = 0;

    protected static String keyWord;
    protected static String numOfWords;
    protected CreatorInputs creatorInputs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setWrapText(true);
        welcomeLbl.setText("Welcome " + CurrentUser.getCurrentUser().getUsername() + "!");
        currentFile = null;
        learn = new Learn();
        try {
            spellCheck = new SpellCheck();
        } catch (IOException e) {
            e.printStackTrace();
        }
        wordCountLbl.setText(String.valueOf(wordCount));
        createBtn.setDisable(true);
        clearStatusBar();
        setSpellCheckVisible(false);
        creatorInputs = new CreatorInputs();
    }

    @FXML
    private MenuItem createBtn;

    @FXML
    private MenuItem learnBtn;

    @FXML
    private Label wordCountLbl;

    @FXML
    private Label sentenceCountLbl;

    @FXML
    private Label fleschScoreLbl;

    @FXML
    private TextArea textArea;

    @FXML
    private Label welcomeLbl;

    @FXML
    private TextArea spellCheckTA;

    @FXML
    private Button signOutBtn;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label spellCheckLbl;

    @FXML
    private Label wordCountWordLbl;

    @FXML
    private Label sentenceCountWordLbl;

    @FXML
    private Label fleschScoreWordLbl;

    @FXML
    private Button hideSpellCheckBtn;


    @FXML
    void clearStatusBarAction(ActionEvent event) {
        clearStatusBar();
    }

    @FXML
    void closeAction(ActionEvent event) {
        textArea.setText(null);
        textArea.setDisable(true);
        createBtn.setDisable(true);
        fc.setInitialDirectory(null);
    }

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void createAction(ActionEvent event) {
        var create = new Create(learn.getParents());
        var window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create");
        window.setScene(new Scene(generateTextParameters(window, create)));
        window.show();
    }

    @FXML
    void fleschScoreAction(ActionEvent event) {
        fleschScoreWordLbl.setVisible(true);
        fleschScoreLbl.setVisible(true);
    }

    @FXML
    void learnAction(ActionEvent event) {
        // TODO open file chooser?
        textArea.setDisable(false);
        var fc = new FileChooser();
        Window stage = menuBar.getScene().getWindow();
        fc.setTitle("Learn Text File");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        try {
            currentFile = fc.showOpenDialog(stage);
            if (currentFile != null) {
                learn.inputTxt(Load.test(currentFile));
                createBtn.setDisable(false);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void newAction(ActionEvent event) {
        textArea.setDisable(false);
        textArea.setText(null);
        currentFile = null;
        fc.setInitialDirectory(null);
    }


    @FXML
    void signOutAction(ActionEvent event) {
        CurrentUser.setCurrentUser(null);
        var stage = (Stage) signOutBtn.getScene().getWindow();
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/Sign_In.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
    }

    @FXML
    void openAction(ActionEvent event) {
        textArea.setDisable(false);
        var fc = new FileChooser();
        Window stage = menuBar.getScene().getWindow();
        fc.setTitle("Open Text File");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        try {
            currentFile = fc.showOpenDialog(stage);
            if (currentFile != null)
                textArea.setText(Load.test(currentFile));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveAction(ActionEvent event) {
        if (currentFile == null) {
            saveAsAction(event);
            return;
        }
        try {
            Save.saveTxt(textArea.getText(), currentFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveAsAction(ActionEvent event) {
        var fc = new FileChooser();
        var stage = menuBar.getScene().getWindow();
        fc.setTitle("Save As");
        fc.setInitialFileName("untitled");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        try {
            currentFile = fc.showSaveDialog(stage);
            if (currentFile == null)
                return;
            fc.setInitialDirectory(currentFile.getParentFile());
            Save.saveTxt(textArea.getText(), currentFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void hideSpellCheckAction(ActionEvent event) {
        setSpellCheckVisible(false);
    }

    @FXML
    void sentenceCountAction(ActionEvent event) {
        sentenceCountWordLbl.setVisible(true);
        sentenceCountLbl.setVisible(true);
    }

    @FXML
    void spellCheckAction(ActionEvent event) {
        //TODO generate separate window for spellcheck
        setSpellCheckVisible(true);
    }

    @FXML
    void undoAction(ActionEvent event) {
        textArea.undo();
    }

    @FXML
    void keyTyped(KeyEvent event) {

    }

    @FXML
    void keyPressed(KeyEvent event) {
        if (event.getCode() == event.getCode().SPACE || event.getCode() == event.getCode().BACK_SPACE) {
            //TODO once i hit space, the number of words should be checked
            wordCountLbl.setText(String.valueOf(countWords()));
            sentenceCountLbl.setText(String.valueOf(countSentences()));
            fleschScoreLbl.setText(String.valueOf(calcFleschScore(countWords(), countSentences(), countSyllables(textArea.getText()))));
            spellCheckTA.setText(spellCheck.spellCheck(textArea.getText()));
        }

    }

    private int countSentences() {
        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance();
        sentenceIterator.setText(textArea.getText());
        int count = 0;
        int boundary = sentenceIterator.first();
        while (boundary != BreakIterator.DONE) {
            ++count;
            boundary = sentenceIterator.next();
        }
        return count-1;
    }

    public static int countSyllables(String word) {
        int num = 0;
        var pattern = "[AEIOUYaeiouy]+";
        var tokenSplitter = Pattern.compile(pattern);
        var matcher = tokenSplitter.matcher(word);

        var lastToken = "";
        while (matcher.find()) {
            num++;
            lastToken = matcher.group();
        }
        if (lastToken.equals("e") && num > 1 && word.charAt(word.length()-1) == 'e') {
            num--;
        }
        return num;
    }

    private int countWords() {
        var pattern = Pattern.compile("[A-Za-z]+");
        var matcher = pattern.matcher(textArea.getText());
        int count = 0;
        while (matcher.find()) { ++count; }
        return count;
    }

    @FXML
    void wordCountAction(ActionEvent event) {
        wordCountWordLbl.setVisible(true);
        wordCountLbl.setVisible(true);
    }

    private double calcFleschScore(int wordCount, int sentences, int totalSyllables) {
        return (0.39 * ((double) wordCount/sentences)) + (11.8 * ((double) totalSyllables/wordCount)) - 15.59;
    }

    private void clearStatusBar() {
        wordCountWordLbl.setVisible(false);
        wordCountLbl.setVisible(false);
        wordCountLbl.setVisible(false);
        sentenceCountWordLbl.setVisible(false);
        sentenceCountLbl.setVisible(false);
        fleschScoreWordLbl.setVisible(false);
        fleschScoreLbl.setVisible(false);
    }

    private void setSpellCheckVisible(boolean isVisible) {
        spellCheckTA.setVisible(isVisible);
        spellCheckLbl.setVisible(isVisible);
        hideSpellCheckBtn.setVisible(isVisible);
    }

    private VBox generateTextParameters(Stage window, Create create) {
        var keyWordLbl = new Label("Keyword");
        var keyWordTF = new TextField();
        var numOfWordsTF = new TextField();
        var numLbl = new Label("# Of Words:");
        var errorMsg = new Label("Input not valid");
        errorMsg.setVisible(false);
        var generateBtn = new Button("Generate");
        var numOfWords2 = "";
        generateBtn.setOnAction(e -> {
            keyWord = keyWordTF.getText();
            numOfWords = numOfWordsTF.getText();
            if (keyWord.isEmpty() && numOfWords.isEmpty()) {
                window.close();
                return;
            }
            create.create(keyWordTF.getText(), Integer.parseInt(numOfWordsTF.getText()));
            textArea.setText(create.getNewTxt());
            window.close();
        });
        var hbox1 = new HBox();
        var hbox2 = new HBox();
        hbox1.getChildren().addAll(keyWordLbl, keyWordTF);
        hbox2.getChildren().addAll(numLbl, numOfWordsTF);
        var vbox = new VBox();
        vbox.getChildren().addAll(hbox1, hbox2, generateBtn, errorMsg);
        return vbox;
    }

}
