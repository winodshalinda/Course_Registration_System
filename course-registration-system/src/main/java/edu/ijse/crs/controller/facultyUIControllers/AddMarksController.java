package edu.ijse.crs.controller.facultyUIControllers;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.EnrollmentDTO;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.entity.EnrollmentEntity;
import edu.ijse.crs.entity.EnrollmentEntity.EnrollmentStatus;
import edu.ijse.crs.entity.embeddableId.SemesterId;
import edu.ijse.crs.exception.CustomException;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.FacultyService;
import edu.ijse.crs.util.EntityDTOConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;

public class AddMarksController {
    
    @FXML
    private Label lblMarks;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<CourseDTO> cbCourse;

    @FXML
    private ChoiceBox<String> cbPartYear;

    @FXML
    private CheckBox checkBoxStatus;

    @FXML
    private TextFlow tFlowDetails;

    @FXML
    private TextField txtMarks;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txtStudentId;

    private FacultyDTO facultyDTO;

    private EnrollmentDTO enrollmentDTO;

    Alert alert = new Alert(AlertType.INFORMATION);

    FacultyService facultyService = (FacultyService) ServiceFactory.getInstance().getService(ServiceTypes.FACULTY);

    public void setFacultyDTO(FacultyDTO facultyDTO) {
        this.facultyDTO = facultyDTO;
        alert.setHeaderText(null);
        loadCourseChoiceBox();
        loadPartYearChoiceBox();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        txtMarks.setVisible(false);
        clearForm();
        tFlowDetails.getChildren().clear();
        btnSearch.setVisible(true);
        btnCancel.setVisible(false);
        btnUpdate.setVisible(false);
        lblMarks.setVisible(false);
        checkBoxStatus.setVisible(false);
        
        enrollmentDTO = null;
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        tFlowDetails.getChildren().clear();

        if (txtYear.getText().isEmpty() || cbPartYear.getValue() == null ||
                cbCourse.getValue() == null || txtStudentId.getText().isEmpty()) {

            alert.setContentText("All Fields Required");
            alert.show();
            return;
        }

        SemesterId semesterId = new SemesterId();
        semesterId.setYear(Integer.parseInt(txtYear.getText()));
        semesterId.setPartOfSemester(cbPartYear.getValue());
        semesterId.setFaculty(EntityDTOConversion.toFacultyEntity(this.facultyDTO));

        CourseDTO courseDTO = cbCourse.getValue();

        try {
            enrollmentDTO = facultyService.searchEnrollment(txtStudentId.getText(), semesterId, courseDTO);
            tFlowDetails.getChildren().clear();
            tFlowDetails.getChildren().add(new Text(enrollmentDTO.toShow()));

            btnSearch.setVisible(false);
            btnCancel.setVisible(true);
            btnUpdate.setVisible(true);
            lblMarks.setVisible(true);
            checkBoxStatus.setVisible(true);
            txtMarks.setVisible(true);

            txtMarks.setText(Integer.toString(enrollmentDTO.getGrade()));
            checkBoxStatus.setSelected(enrollmentDTO.getStatus() == EnrollmentEntity.EnrollmentStatus.COMPLETED);

        } catch (CustomException e) {
            alert.setContentText(e.getMessage());
            alert.show();

        } catch (Exception e) {
            alert.setContentText("Something went wrong");
            alert.show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        enrollmentDTO.setGrade(Integer.parseInt(txtMarks.getText()));
        enrollmentDTO.setStatus(checkBoxStatus.isSelected()?EnrollmentStatus.COMPLETED:EnrollmentStatus.ENROLLED);

        String updateEnrollment = facultyService.updateEnrollment(enrollmentDTO);
        alert.setContentText(updateEnrollment);
        alert.show();
        
        if(updateEnrollment.equals("Updated")){
            btnCancelOnAction(event);
        }
    }

    void clearForm() {
        txtMarks.setText("");
        txtStudentId.setText("");
        checkBoxStatus.setSelected(false);
    }

    void loadCourseChoiceBox() {
        try {
            List<CourseDTO> courseDTOList = facultyService.loadCourse(facultyDTO);
            cbCourse.getItems().clear();
            cbCourse.getItems().addAll(courseDTOList);

        } catch (Exception e) {
            alert.setContentText(e.getCause().getMessage());
            alert.show();
        }
    }

    void loadPartYearChoiceBox() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.removeAll();
        observableList.addAll("FIRST", "SECOND");
        cbPartYear.setItems(observableList);
    }
}
