package edu.ijse.crs.controller;

import edu.ijse.crs.dto.UserDTO;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;


public class UserUIController {
    UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceTypes.USER);

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        UserDTO userDTO = new UserDTO(txtUserName.getText(), txtPassword.getText());
        try {
            UserDTO login = userService.login(userDTO);
            System.out.println(login);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}