package p1.controller;

import javafx.event.ActionEvent;
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
import p1.model.CurrentUser;
import p1.model.user_functions.UserStorage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignIn implements Initializable {

    @FXML
    private Label errorLbl;

    @FXML
    private TextField uNameTF;

    @FXML
    private PasswordField pWordTF;

    @FXML
    private Button signInBtn;

    @FXML
    private Button signUpBtn;

    //ultra Hell0world

    @FXML
    void signInAction(ActionEvent event) {
        if (p1.model.user_functions.SignIn.authenticate(uNameTF.getText(), pWordTF.getText())) {
            CurrentUser.setCurrentUser(UserStorage.getUserStorage().get(uNameTF.getText()));
            // TODO set stage for the text editor
            var stage = (Stage) signInBtn.getScene().getWindow();
            stage.setTitle("Text Editor");
            Pane root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../view/TextEditor.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(root));
        }
        else {
            //TODO write msg that input is wrong
            errorLbl.setVisible(true);
        }
        //TODO find user obj from user data storage and make it in control and then switch scene to texteditor
    }

    @FXML
    void signUpAction(ActionEvent event) {
        var stage = (Stage) signUpBtn.getScene().getWindow();
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/Sign_Up.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLbl.setVisible(false);
    }
}
