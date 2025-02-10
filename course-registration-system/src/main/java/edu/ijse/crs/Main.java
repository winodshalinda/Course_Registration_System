package edu.ijse.crs;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL resource = getClass().getResource("view/AdminUI.fxml"); //TODO change to login UI "view/UserUI.fxml" //"view/AdminUI.fxml"
        Parent root=FXMLLoader.load(resource);
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.setResizable(true); //TODO change to false
        stage.show();
        stage.centerOnScreen();
    }
}