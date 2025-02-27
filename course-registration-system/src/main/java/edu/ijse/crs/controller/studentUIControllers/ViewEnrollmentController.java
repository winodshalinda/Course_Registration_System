package edu.ijse.crs.controller.studentUIControllers;

import java.util.List;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.EnrollmentDTO;
import edu.ijse.crs.dto.SemesterDTO;
import edu.ijse.crs.dto.StudentDTO;
import edu.ijse.crs.entity.EnrollmentEntity.EnrollmentStatus;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.EnrollmentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewEnrollmentController {

    @FXML
    private TableColumn<EnrollmentDTO,EnrollmentStatus> colStatus;

    @FXML
    private TableColumn<EnrollmentDTO,CourseDTO> colCourse;

    @FXML
    private TableColumn<EnrollmentDTO, Integer> colGrade;

    @FXML
    private TableColumn<EnrollmentDTO, SemesterDTO> colSemester;

    @FXML
    private TableView<EnrollmentDTO> tblEnrollment;

    private StudentDTO studentDTO;

    EnrollmentService enrollmentService = (EnrollmentService) ServiceFactory.getInstance()
            .getService(ServiceTypes.ENROLLMENT);

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
        loadTable();
        // TODO
    }

    public void initialize(){

        colSemester.setCellValueFactory(new PropertyValueFactory<>("semesterDTO"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("courseDTO"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    void loadTable() {
        List<EnrollmentDTO> enrollments= enrollmentService.loadTable(studentDTO);
        ObservableList<EnrollmentDTO> observableList=FXCollections.observableArrayList(enrollments);
        tblEnrollment.setItems(observableList);

    }

}
