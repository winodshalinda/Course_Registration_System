package edu.ijse.crs.controller.studentUIControllers.stageController;

import java.util.List;

import edu.ijse.crs.dto.StudentDTO;
import edu.ijse.crs.dto.VacanciesDTO;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.EnrollmentService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class VacanciesStageController {

    @FXML
    private TableColumn<VacanciesDTO, Long> colAvailableSeats;

    @FXML
    private TableColumn<VacanciesDTO, String> colCourse;

    @FXML
    private TableView<VacanciesDTO> tblVacanxies;

    private StudentDTO studentDTO;

    private Timeline timeline;

    EnrollmentService enrollmentService = (EnrollmentService) ServiceFactory.getInstance()
            .getService(ServiceTypes.ENROLLMENT);

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
        loadVacanciesTable();
        startPolling();
    }

    public void initialize() {
        startPolling();

        colCourse.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colAvailableSeats.setCellValueFactory(new PropertyValueFactory<>("available"));
    }

    void loadVacanciesTable() {
        List<VacanciesDTO> vacancies = enrollmentService.loadVacancies(studentDTO);
        ObservableList<VacanciesDTO> observableList = FXCollections.observableArrayList(vacancies);

        tblVacanxies.setItems(observableList);
    }

    void startPolling() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            loadVacanciesTable();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void setupStageCloseListener(Stage stage) {
        stage.setOnCloseRequest((WindowEvent event) -> {
            timeline.stop();
        });
    }
}
