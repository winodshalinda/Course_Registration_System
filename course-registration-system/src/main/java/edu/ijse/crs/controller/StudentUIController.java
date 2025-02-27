package edu.ijse.crs.controller;

import java.io.IOException;
import java.net.URL;

import edu.ijse.crs.controller.studentUIControllers.RegisterCourseController;
import edu.ijse.crs.controller.studentUIControllers.StudentHomeController;
import edu.ijse.crs.controller.studentUIControllers.ViewEnrollmentController;
import edu.ijse.crs.dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class StudentUIController {

    @FXML
    private AnchorPane ancPane;

    @FXML
    private ScrollPane scrollPane;

    private StudentDTO studentDTO;

    public void setStudentDTO(StudentDTO studentDTO) throws IOException {
        this.studentDTO = studentDTO;
        btnHomeOnAction(null);
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        ancPane.getChildren().clear();

        URL url = getClass().getResource("../view/studentUiPane/StudentHomeUI.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent parent = loader.load();

        StudentHomeController controller = loader.getController();
        controller.setStudentDTO(studentDTO);

        ancPane.getChildren().add(parent);

        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
    }

    @FXML
    void btnRegisterCourseOnAction(ActionEvent event) throws IOException {
        ancPane.getChildren().clear();

        URL url = getClass().getResource("../view/studentUiPane/RegisterCourseUI.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent parent = loader.load();

        RegisterCourseController controller = loader.getController();
        controller.setStudentDTO(studentDTO);

        ancPane.getChildren().add(parent);

        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);

    }

    @FXML
    void btnViewEnrollmentOnAction(ActionEvent event) throws IOException {
        
        ancPane.getChildren().clear();

        URL url = getClass().getResource("../view/studentUiPane/ViewEnrollmentUI.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent parent = loader.load();

        ViewEnrollmentController controller = loader.getController();
        controller.setStudentDTO(studentDTO);

        ancPane.getChildren().add(parent);

        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
    }

}
