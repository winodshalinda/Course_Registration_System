package edu.ijse.crs.controller.facultyUIControllers.departmentUIStageController;

import java.util.ArrayList;
import java.util.List;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.DepartmentDTO;
import edu.ijse.crs.exception.CustomException;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.CourseService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
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

    Alert alert = new Alert(AlertType.INFORMATION);

    private DepartmentDTO departmentDTO;

    List<CourseDTO> prerequisites = new ArrayList<>();

    CourseService courseService = (CourseService) ServiceFactory.getInstance().getService(ServiceTypes.COURSE);

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
        lblDepartmentName.setText("" + departmentDTO.getDepartmentName() + " Department Course Management");
    }

    public void initialize() {

        // Only allows numeric input in txtEnrollmentCapacity field
        txtEnrollmentCapacity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtEnrollmentCapacity.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Only allows numeric input in txtCreditHours field
        txtCreditHours.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtCreditHours.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    void btnAddPrerequisitesOnAction(ActionEvent event) {
        alert.setHeaderText(null);
        if (txtPrerequisites.getText().isEmpty()) {
            alert.setContentText("Please Enter Course Id");
            alert.show();
            return;
        }
        try {
            prerequisites = courseService.AddPrerequisites(txtPrerequisites.getText(), prerequisites);
            alert.setContentText("Prerequisites Course Added");
            alert.show();

            refreshPrerequisites();

        } catch (CustomException e) {
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnRemovePrerequisitesOnAction(ActionEvent event) {
        alert.setHeaderText(null);
        if (txtPrerequisites.getText().isEmpty()) {
            alert.setContentText("Please Enter Course Id");
            alert.show();
            return;
        }
        try {

            prerequisites = courseService.RemovePrerequisites(txtPrerequisites.getText(), prerequisites);
            alert.setContentText("Course Removed From Prerequisites Courses");
            alert.show();

            refreshPrerequisites();

        } catch (CustomException e) {
            alert.setContentText(e.getMessage());
            alert.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearForm();
        btnSave.setVisible(true);
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);
        btnCancel.setVisible(false);
        txtCourseId.setDisable(false);
        tFlowCourse.setVisible(false);
        // TODO
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        alert.setHeaderText(null);

        if (txtCourseId.getText().isEmpty() || txtCourseTitle.getText().isEmpty() ||
                txtEnrollmentCapacity.getText().isEmpty() || txtCreditHours.getText().isEmpty()) {

            alert.setContentText("Please Fill All * Fileds");
            alert.show();
            return;
        }

        String saveCourse = courseService.saveCourse(new CourseDTO(txtCourseId.getText(),
                txtCourseTitle.getText(),
                Integer.parseInt(txtEnrollmentCapacity.getText()),
                Integer.parseInt(txtEnrollmentCapacity.getText()),
                Integer.parseInt(txtCreditHours.getText()),
                departmentDTO), prerequisites);

        alert.setContentText(saveCourse);
        alert.show();
        clearForm();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        tFlowCourse.getChildren().clear();
        tFlowPrerequisites.getChildren().clear();

        alert.setHeaderText(null);

        if (txtSearch.getText().isEmpty()) {
            alert.setContentText("Search Field Empty");
            alert.show();
            return;
        }
        try {

            CourseDTO searchCourse = courseService.searchCourse(txtSearch.getText(), departmentDTO);

            if (searchCourse == null) {
                alert.setContentText("Course Not Found");
                alert.show();
                return;
            }

            txtCourseId.setText(searchCourse.getCourseId());
            txtCourseTitle.setText(searchCourse.getCourseTitle());
            txtEnrollmentCapacity.setText(Integer.toString(searchCourse.getEnrollmentCapacity()));
            txtCreditHours.setText(Integer.toString(searchCourse.getCreditHours()));

            btnSave.setVisible(false);
            btnUpdate.setVisible(true);
            btnDelete.setVisible(true);
            btnCancel.setVisible(true);
            txtCourseId.setDisable(true);
            tFlowCourse.setVisible(true);

            prerequisites = courseService.getCourseAllPrerequisites(searchCourse);

            tFlowCourse.getChildren().addAll(
                    new Text(searchCourse.toString()),
                    new Text("Prerequisites Courses:-\n"));

            for (CourseDTO courseDTO : prerequisites) {
                tFlowCourse.getChildren()
                        .addAll(new Text("\t\t" + courseDTO.getCourseTitle() + " (" + courseDTO.getCourseId() + ")\n"));
            }

            refreshPrerequisites();

        } catch (CustomException e) {
            alert.setContentText(e.getMessage());
            alert.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // TODO
    }

    public void clearForm() {
        txtCourseId.clear();
        txtCourseTitle.clear();
        txtEnrollmentCapacity.clear();
        txtCreditHours.clear();
        txtPrerequisites.clear();
        tFlowPrerequisites.getChildren().clear();
        prerequisites = null;
    }

    public void refreshPrerequisites() {
        if (prerequisites == null) {
            return;
        }
        tFlowPrerequisites.getChildren().clear();
        for (CourseDTO courseDTO : prerequisites) {
            tFlowPrerequisites.getChildren()
                    .addAll(new Text("\t"+courseDTO.getCourseTitle() + " (" + courseDTO.getCourseId() + ")\n"));
        }
    }
}
