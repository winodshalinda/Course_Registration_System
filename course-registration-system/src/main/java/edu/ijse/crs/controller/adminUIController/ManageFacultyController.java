package edu.ijse.crs.controller.adminUIController;

import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.exception.CustomException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ManageFacultyController {
    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSelectedFacultyId;

    @FXML
    private TextField txtSelectedFacultyName;

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
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtRePassword;

    @FXML
    private Pane paneGetFaculty;

    FacultyService facultyService = (FacultyService) ServiceFactory.getInstance().getService(ServiceTypes.FACULTY);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public void initialize() {

        getAllFaculty();

        colFacId.setCellValueFactory(new PropertyValueFactory<>("facultyId"));
        colFacName.setCellValueFactory(new PropertyValueFactory<>("facultyName"));
        startPolling();

        tblFaculty.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                paneGetFaculty.setVisible(true);
                txtSelectedFacultyId.setText(newSelection.getFacultyId());
                txtSelectedFacultyName.setText(newSelection.getFacultyName());
            }
        });
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
        FacultyDTO facultyDTO = new FacultyDTO(txtSelectedFacultyId.getText(), txtSelectedFacultyName.getText(), null,
                null);
        alert.setTitle("Update Faculty");
        alert.setHeaderText(null);
        try {
            String updateFaculty = facultyService.updateFaculty(facultyDTO);
            alert.setContentText(updateFaculty);
            alert.showAndWait();
            paneGetFaculty.setVisible(false);
            getAllFaculty();
        } catch (OptimisticLockException e) {
            alert.setContentText("Faculty Not Found");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        alert.setTitle("Delete Faculty");
        alert.setHeaderText(null);
        try {
            String deleteFaculty = facultyService.deleteFaculty(txtSelectedFacultyId.getText());
            alert.setContentText(deleteFaculty);
            alert.showAndWait();
            paneGetFaculty.setVisible(false);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            FacultyDTO searchFaculty = facultyService.searchFaculty(txtSearch.getText());
            if (searchFaculty != null) {
                paneGetFaculty.setVisible(true);
                txtSelectedFacultyId.setText(searchFaculty.getFacultyId());
                txtSelectedFacultyName.setText(searchFaculty.getFacultyName());
            } else {
                alert.setContentText("Faculty Not Found");
                alert.showAndWait();
            }

        } catch (CustomException e) {
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
