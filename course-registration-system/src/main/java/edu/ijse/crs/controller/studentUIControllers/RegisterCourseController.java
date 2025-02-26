package edu.ijse.crs.controller.studentUIControllers;

import java.util.List;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.ProgramDetailsDTO;
import edu.ijse.crs.dto.SemesterDTO;
import edu.ijse.crs.dto.StudentDTO;
import edu.ijse.crs.exception.CustomException;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.EnrollmentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class RegisterCourseController {

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnView;

    @FXML
    private Label lblSemesterInfo;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDrop;

    @FXML
    private Button btnEnroll;

    @FXML
    private TextFlow textFlowCourse;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxCourse;

    private StudentDTO studentDTO;

    SemesterDTO availableEnrollSemester;

    CourseDTO searchCourse;

    List<ProgramDetailsDTO> detailsDTOs;

    Alert alert = new Alert(AlertType.INFORMATION);

    EnrollmentService enrollmentService = (EnrollmentService) ServiceFactory.getInstance()
            .getService(ServiceTypes.ENROLLMENT);

    public void initialize() {
        alert.setHeaderText(null);
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        textFlowCourse.getChildren().clear();

        textFlowCourse.setVisible(false);
        btnEnroll.setVisible(false);
        btnDrop.setVisible(false);
        btnCancel.setVisible(false);
        btnSearch.setVisible(true);
        txtSearch.setDisable(false);


    }

    @FXML
    void btnDropOnAction(ActionEvent event) {

        String dropCourse=enrollmentService.dropCourse(studentDTO, searchCourse,availableEnrollSemester);
        alert.setContentText(dropCourse);
        alert.show();

    }

    @FXML
    void btnEnrollOnAction(ActionEvent event) {

        String enrollCourse = enrollmentService.enrollCourse(studentDTO, searchCourse,availableEnrollSemester);
        alert.setContentText(enrollCourse);
        alert.show();

    }

    @FXML
    void btnSearchCourseOnAction(ActionEvent event) {

        if (txtSearch.getText().isEmpty()) {
            alert.setContentText("Search Field Empty");
            alert.show();
            return;
        }

        try {
            // search course
            searchCourse = enrollmentService.searchCourse(txtSearch.getText(), detailsDTOs);

            if (searchCourse == null) {
                alert.setContentText("Course Not Found");
                alert.show();
                return;
            }

            textFlowCourse.setVisible(true);
            btnEnroll.setVisible(true);
            btnDrop.setVisible(true);
            btnCancel.setVisible(true);
            btnSearch.setVisible(false);
            txtSearch.setDisable(true);

            textFlowCourse.getChildren().clear();
            textFlowCourse.getChildren().add(new Text(searchCourse.toShow()));

        } catch (CustomException e) {
            searchCourse = null;
            alert.setContentText(e.getMessage());
            alert.show();

        } catch (Exception e) {
            alert.setContentText("Course Search Error");
            alert.show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnViewVacanciesOnAction(ActionEvent event) {
        // TODO
    }

    void getAvailableEnrollSemester() {
        availableEnrollSemester = enrollmentService.canEnroll(studentDTO);
        if (availableEnrollSemester == null) {
            lblSemesterInfo.setText("Enrollment Not Available(Enroll Date Expired)");
            txtSearch.setVisible(false);
            btnSearch.setVisible(false);
            btnView.setVisible(false);

        } else {
            lblSemesterInfo.setText(availableEnrollSemester.toString() + " Semester Enrollment");
        }
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
        getAvailableEnrollSemester();
        loadCourses();
    }

    void loadCourses() {

        try {

            detailsDTOs = enrollmentService.getStudentProgramCourse(studentDTO);

            for (int i = 0; i < studentDTO.getProgram().getTotalSemester(); i++) {

                VBox newVBox = new VBox();
                newVBox.setPrefHeight(200.0);

                Label semester = new Label("Semester " + (i + 1));

                semester.setStyle(
                        "-fx-font-weight: bold;");

                semester.setUnderline(true);

                newVBox.getChildren().add(semester);

                for (ProgramDetailsDTO programDetailsDTO : detailsDTOs) {

                    if ((i + 1) == programDetailsDTO.getSemester()) {
                        newVBox.getChildren().add(new Label(programDetailsDTO.getCourse().toString()));
                    }

                }

                newVBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
                vBoxCourse.getChildren().add(newVBox);
            }

        } catch (Exception e) {
            alert.setContentText("Courses load failed");
            alert.show();
            e.printStackTrace();
        }

    }
}
