package edu.ijse.crs.controller.facultyUIControllers;

import java.time.LocalDate;
import java.util.List;

import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.dto.SemesterDTO;
import edu.ijse.crs.entity.SemesterEntity.PartOfSemester;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.SemesterService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageFacultyTremController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<?> cbPartYear;

    @FXML
    private ChoiceBox<?> cbSearchPartYear;

    @FXML
    private TableColumn<SemesterDTO,LocalDate> colEndDate;

    @FXML
    private TableColumn<SemesterDTO, PartOfSemester> colPartYear;

    @FXML
    private TableColumn<SemesterDTO,LocalDate> colStartDate;

    @FXML
    private TableColumn<SemesterDTO, Integer> colYear;

    @FXML
    private DatePicker dpEndDate;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private TableView<SemesterDTO> tblSemester;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtYear;

    private FacultyDTO facultyDTO;

    Alert alert = new Alert(AlertType.INFORMATION);

    SemesterService semesterService = (SemesterService) ServiceFactory.getInstance().getService(ServiceTypes.SEMESTER);

    public void setFacultyDTO(FacultyDTO facultyDTO) {
        this.facultyDTO = facultyDTO;
        loadTable();
    }

    public void initialize() {
        alert.setHeaderText(null);

        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colPartYear.setCellValueFactory(new PropertyValueFactory<>("partOfSemester"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("starDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
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

    void loadTable() {

        try {
            List<SemesterDTO> table = semesterService.loadTable(facultyDTO);
            ObservableList<SemesterDTO> observableList = FXCollections.observableArrayList(table);
            tblSemester.setItems(observableList);

        } catch (Exception e) {
            alert.setContentText("Table Loading Failed");
            alert.show();
            e.printStackTrace();
        }

    }

}
