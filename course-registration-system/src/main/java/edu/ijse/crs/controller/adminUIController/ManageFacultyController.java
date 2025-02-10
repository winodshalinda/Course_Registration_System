package edu.ijse.crs.controller.adminUIController;

import java.util.List;
import javax.persistence.PersistenceException;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.FacultyService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class ManageFacultyController {
    @FXML
    private TableColumn<FacultyDTO, String> colFacId;

    @FXML
    private TableColumn<FacultyDTO, String> colFacName;

    @FXML
    private TableView<FacultyDTO> tblFaculty;

    @FXML
    private TextField txtFacultyId;

    @FXML
    private TextField txtFacultyName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRePassword;

    FacultyService facultyService = (FacultyService) ServiceFactory.getInstance().getService(ServiceTypes.FACULTY);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public void initialize() {

        getAllFaculty();

        colFacId.setCellValueFactory(new PropertyValueFactory<>("facultyId"));
        colFacName.setCellValueFactory(new PropertyValueFactory<>("facultyName"));
        startPolling();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        FacultyDTO faculty = new FacultyDTO(txtFacultyId.getText(), txtFacultyName.getText(), txtPassword.getText(),
                txtRePassword.getText());
        alert.setTitle("Save Faculty");
        alert.setHeaderText(null);

        try {
            String saveFaculty = facultyService.saveFaculty(faculty);
            alert.setContentText(saveFaculty);
            alert.showAndWait();
        } catch (PersistenceException e) {
            alert.setContentText(txtFacultyId.getText() + " already exists");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // TODO
    }

    public void getAllFaculty() {
        try {
            List<FacultyDTO> allFaculties = facultyService.getAllFaculties();
            ObservableList<FacultyDTO> observableList = FXCollections.observableArrayList();
            observableList.addAll(allFaculties);
            tblFaculty.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startPolling() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            getAllFaculty();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
