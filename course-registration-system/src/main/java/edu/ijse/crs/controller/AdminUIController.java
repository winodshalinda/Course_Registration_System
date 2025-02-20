package edu.ijse.crs.controller;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminUIController {
    @FXML
    private AnchorPane ancAdminUI;

    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane ancScrollPane;

    @FXML
    void btnBackOnAction(ActionEvent event) {
        btnBack.setVisible(false);
        //TODO
    }

    @FXML
    void btnMngFacultyOnAction(ActionEvent event) throws Exception {
        ancScrollPane.getChildren().clear();
        btnBack.setVisible(true);
        URL resource = getClass().getResource("../view/adminUiPane/ManageFacultyUI.fxml");
        Parent node = FXMLLoader.load(resource);
        ancScrollPane.getChildren().add(node);
        ((Stage) ancAdminUI.getScene().getWindow()).setMinWidth(968);
    }

    @FXML
    void btnMngStudenOnAction(ActionEvent event) throws Exception {
        ancScrollPane.getChildren().clear();
        btnBack.setVisible(true);
        URL resource = getClass().getResource("../view/adminUiPane/ManageStudentUI.fxml");
        Parent node = FXMLLoader.load(resource);
        ancScrollPane.getChildren().add(node);
        //TODO
    }
}
