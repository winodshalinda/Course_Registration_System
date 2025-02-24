package edu.ijse.crs.controller;

import java.io.IOException;
import java.net.URL;

import edu.ijse.crs.controller.facultyUIControllers.ManageDepartmentController;
import edu.ijse.crs.controller.facultyUIControllers.ManageProgramController;
import edu.ijse.crs.dto.FacultyDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class FacultyUIController {
    public static FacultyDTO facultyDTO;

    @FXML
    private AnchorPane ancFacultyUI;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private Button btnHome;

    @FXML
    private Label txtFacultyName;

    public void setFacultyDTO(FacultyDTO facultyDTO) {

        FacultyUIController.facultyDTO = facultyDTO;
        
        if (facultyDTO != null) {
            txtFacultyName.setText(facultyDTO.getFacultyName());
        }
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnMngDepartmentOnAction(ActionEvent event) throws IOException {
        ancPane.getChildren().clear();

        ManageDepartmentController.facultyDTO = FacultyUIController.facultyDTO;

        URL resourse = getClass().getResource("../view/facultyUiPane/ManageDepartmentUI.fxml");
        FXMLLoader loader = new FXMLLoader(resourse);
        Parent parent = loader.load();

        ancPane.getChildren().add(parent);

        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
    }

    @FXML
    void btnMngProgramOnAction(ActionEvent event) throws Exception {
        ancPane.getChildren().clear();

        ManageProgramController.facultyDTO = FacultyUIController.facultyDTO;

        URL resourse = getClass().getResource("../view/facultyUiPane/ManageProgramUI.fxml");
        FXMLLoader loader = new FXMLLoader(resourse);
        Parent parent = loader.load();

        ancPane.getChildren().add(parent);

        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
    }

    @FXML
    void btnMngFacultyTremOnAction(ActionEvent event) {
        //TODO
    }
}
