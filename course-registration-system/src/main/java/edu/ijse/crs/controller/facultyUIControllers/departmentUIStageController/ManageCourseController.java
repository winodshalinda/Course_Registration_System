package edu.ijse.crs.controller.facultyUIControllers.departmentUIStageController;

import edu.ijse.crs.dto.DepartmentDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

public class ManageCourseController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAvailableEnrollment;

    @FXML
    private TableColumn<?, ?> colCreditHours;

    @FXML
    private TableColumn<?, ?> colEnrollmentCapacity;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPrerequisites;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Label lblDepartmentName;

    @FXML
    private TextFlow tFlowCourse;

    @FXML
    private TextFlow tFlowPrerequisites;

    @FXML
    private TableView<?> tblCourse;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseTitle;

    @FXML
    private TextField txtCreditHours;

    @FXML
    private TextField txtEnrollmentCapacity;

    @FXML
    private TextField txtPrerequisites;

    @FXML
    private TextField txtSearch;

    private DepartmentDTO departmentDTO;

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
        lblDepartmentName.setText(""+departmentDTO.getDepartmentName()+" Department Course Management");
    }

    @FXML
    void btnAddPrerequisitesOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
