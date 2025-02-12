package edu.ijse.crs.controller;

import java.net.URL;

import edu.ijse.crs.dto.UserDTO;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import edu.ijse.crs.entity.UserEntity.Role;

public class UserUIController {
    UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceTypes.USER);

    @FXML
    private AnchorPane ancLoginUI;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws Exception {
        UserDTO userDTO = new UserDTO();
        try {
            userDTO = userService.login(txtUserName.getText(), txtPassword.getText());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        if (userDTO.getRole() != null) {
            Stage stage = (Stage) ancLoginUI.getScene().getWindow();
            stage.setResizable(true);
            ancLoginUI.getScene().getWindow().setWidth(1300.00);
            ancLoginUI.getScene().getWindow().setHeight(610.00);
            ancLoginUI.getChildren().clear();
            switch (userDTO.getRole()) {
                case Role.ADMIN: {
                    URL resource = getClass().getResource("../view/AdminUI.fxml");
                    Parent node = FXMLLoader.load(resource);
                    ancLoginUI.getChildren().add(node);
                    AnchorPane.setTopAnchor(node, 0.0);
                    AnchorPane.setBottomAnchor(node, 0.0);
                    AnchorPane.setLeftAnchor(node, 0.0);
                    AnchorPane.setRightAnchor(node, 0.0);
                    stage.setTitle("Admin Panel");
                    stage.centerOnScreen();
                }
                    break;
                case Role.FACULTY: {
                    System.out.println(userDTO.getFacultyDTO());
                    URL resource = getClass().getResource("../view/FacultyUI.fxml");
                    FXMLLoader fxmlLoader = new FXMLLoader(resource);
                    Parent node = fxmlLoader.load();
                    FacultyUIController controller = fxmlLoader.getController();
                    controller.setFacultyDTO(userDTO.getFacultyDTO());
                    ancLoginUI.getChildren().add(node);
                    AnchorPane.setTopAnchor(node, 0.0);
                    AnchorPane.setBottomAnchor(node, 0.0);
                    AnchorPane.setLeftAnchor(node, 0.0);
                    AnchorPane.setRightAnchor(node, 0.0);
                    stage.setTitle("Faculty Panel");
                    stage.centerOnScreen();
                }
                    break;
                default:
                    break;
            }
        }
    }
}