package edu.ijse.crs.controller;

import edu.ijse.crs.dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void btnRegisterCourseOnAction(ActionEvent event) {
        //TODO
    }

    @FXML
    void btnViewEnrollmentOnAction(ActionEvent event) {
        //TODO
    }

}
