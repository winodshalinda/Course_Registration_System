package edu.ijse.crs.controller;

import java.io.IOException;
import java.net.URL;

import edu.ijse.crs.controller.studentUIControllers.RegisterCourseController;
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

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    @FXML
    void btnRegisterCourseOnAction(ActionEvent event) throws IOException{
        ancPane.getChildren().clear();

        URL url=getClass().getResource("../view/studentUiPane/RegisterCourseUI.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent parent=loader.load();

        RegisterCourseController controller = loader.getController();
        controller.setStudentDTO(studentDTO);

        ancPane.getChildren().add(parent);
        
    }

    @FXML
    void btnViewEnrollmentOnAction(ActionEvent event) {
        // TODO
    }

}
