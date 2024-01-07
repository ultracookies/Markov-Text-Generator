package p1.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    @FXML
    private Label errorMsgLbl;

    @FXML
    private TextField uNameTF;

    @FXML
    private PasswordField pWordTF;

    @FXML
    private PasswordField cpWordTF;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button backBtn;

    @FXML
    void signUpAction(ActionEvent event) {
        p1.model.user_functions.SignUp.signUp(uNameTF.getText(), pWordTF.getText());
    }

    @FXML
    void backAction(ActionEvent event) {

    }

    public void signUpAction(javafx.event.ActionEvent event) {
        if (p1.model.user_functions.SignUp.signUp(uNameTF.getText(), pWordTF.getText())) {
            errorMsgLbl.setVisible(false);
            var stage = (Stage) signUpBtn.getScene().getWindow();
            Pane root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../view/Sign_In.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(root));
        }
        else
            errorMsgLbl.setVisible(true);
    }

    public void backAction(javafx.event.ActionEvent event) {
        var stage = (Stage) backBtn.getScene().getWindow();
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/Sign_In.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorMsgLbl.setVisible(false);
    }
}
