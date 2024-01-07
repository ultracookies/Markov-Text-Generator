package p1.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import p1.model.user_functions.User;
import p1.model.user_functions.UserStorage;

import java.io.*;
import java.util.TreeMap;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("users.bin"));
            var oos = new ObjectOutputStream(fos);
            oos.writeObject(UserStorage.getUserStorage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        try {
            var fis = new FileInputStream(new File("users.bin"));
            var ois = new ObjectInputStream(fis);
            UserStorage.getUserStorage().putAll((TreeMap<String, User>) ois.readObject());
            ois.close();
            fis.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sign In/Sign Up");
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/Sign_In.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.show();
    }
}
