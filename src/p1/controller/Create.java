package p1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import p1.model.texteditor_functions.markovtextgenerator.CreatorInputs;

import java.net.URL;
import java.util.ResourceBundle;

public class Create implements Initializable {

    private static String keyWord;
    private static int numOfWords;

    protected CreatorInputs creatorInputs;

    @FXML
    private TextField startingWordTF;

    @FXML
    private TextField numOfWordsTF;

    @FXML
    private Button generateBtn;

    public static String getKeyWord() {
        return keyWord;
    }

    public static int getNumOfWords() {
        return numOfWords;
    }

    @FXML
    void generateAction(ActionEvent event) {
        if (validateWord() && validateNumOfWords()) {
            TextEditor.keyWord = startingWordTF.getText();
            //TextEditor.numOfWords = Integer.parseInt(numOfWordsTF.getText());
        }
    }

    @FXML
    void keyPressed(KeyEvent event) {
        if (!startingWordTF.getText().isEmpty() && !numOfWordsTF.getText().isEmpty())
            generateBtn.setDisable(false);
    }

    private boolean validateNumOfWords() {
        if (numOfWordsTF.getText().isEmpty())
            return false;
        return true;
    }

    private boolean validateWord() {
        if (startingWordTF.getText().contains(" "))
            return false;
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateBtn.setDisable(true);
    }
}
