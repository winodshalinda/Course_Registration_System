package edu.ijse.crs.controller;

import edu.ijse.crs.dto.FacultyDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class FacultyUIController {
    private FacultyDTO facultyDTO;

    @FXML
    private AnchorPane ancFacultyUI;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private Button btnHome;

    @FXML
    private Label txtFacultyName;

    public void setFacultyDTO(FacultyDTO facultyDTO) {
        this.facultyDTO = facultyDTO;
        if (facultyDTO != null) {
            txtFacultyName.setText(facultyDTO.getFacultyName());
        }
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        //TODO
    }

    @FXML
    void btnMngDepartmentOnAction(ActionEvent event) {
        //TODO
    }

    @FXML
    void btnMngProgramOnAction(ActionEvent event) {
        //TODO
    }
}
