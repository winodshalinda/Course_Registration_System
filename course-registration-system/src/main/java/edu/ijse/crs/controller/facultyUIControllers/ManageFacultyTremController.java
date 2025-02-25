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
    private ChoiceBox<PartOfSemester> cbPartYear;

    @FXML
    private ChoiceBox<PartOfSemester> cbSearchPartYear;

    @FXML
    private TableColumn<SemesterDTO, LocalDate> colEndDate;

    @FXML
    private TableColumn<SemesterDTO, PartOfSemester> colPartYear;

    @FXML
    private TableColumn<SemesterDTO, LocalDate> colStartDate;

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

    private SemesterDTO searchSemester;

    Alert alert = new Alert(AlertType.INFORMATION);

    SemesterService semesterService = (SemesterService) ServiceFactory.getInstance().getService(ServiceTypes.SEMESTER);

    public void setFacultyDTO(FacultyDTO facultyDTO) {
        this.facultyDTO = facultyDTO;
        loadTable();
    }

    public void initialize() {
        alert.setHeaderText(null);

        loadChoiseBox();

        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colPartYear.setCellValueFactory(new PropertyValueFactory<>("partOfSemester"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("starDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        // Only allows numeric input in txtYear field
        txtYear.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtYear.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Only allows numeric input in txtSearch field
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSearch.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        
        btnSave.setVisible(true);
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);
        btnCancel.setVisible(false);
        txtYear.setDisable(false);
        cbPartYear.setDisable(false);

        txtYear.clear();
        cbPartYear.setValue(null);
        dpStartDate.setValue(null);
        dpEndDate.setValue(null);
        searchSemester = null;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (txtYear.getText().isEmpty() || cbPartYear.getValue() == null ||
                dpStartDate.getValue() == null || dpEndDate.getValue() == null) {

            alert.setContentText("All Field Required");
            alert.show();
            return;
        }

        SemesterDTO semesterDTO = new SemesterDTO(Integer.parseInt(
                txtYear.getText()),
                cbPartYear.getValue(),
                dpStartDate.getValue(),
                dpEndDate.getValue(),
                facultyDTO);

        String saveSemester = semesterService.saveSemester(semesterDTO);

        if (saveSemester.equals("Semester Saved")) {
            btnCancelOnAction(event);
            loadTable();
        }
        alert.setContentText(saveSemester);
        alert.show();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        if (txtSearch.getText().isEmpty() || cbSearchPartYear.getValue() == null) {
            alert.setContentText("Search And Semester Field Needed");
            alert.show();
            return;
        }
        SemesterDTO semesterDTO = new SemesterDTO(
                Integer.parseInt(txtSearch.getText()),
                cbSearchPartYear.getValue(),
                facultyDTO);

        searchSemester = semesterService.searchSemester(semesterDTO);

        if (searchSemester == null) {
            alert.setContentText("Semester Not Found");
            alert.show();
            return;
        }

        txtYear.setText(Integer.toString(searchSemester.getYear()));
        cbPartYear.setValue(searchSemester.getPartOfSemester());
        dpStartDate.setValue(searchSemester.getStarDate());
        dpEndDate.setValue(searchSemester.getEndDate());

        btnSave.setVisible(false);
        btnUpdate.setVisible(true);
        btnDelete.setVisible(true);
        btnCancel.setVisible(true);
        txtYear.setDisable(true);
        cbPartYear.setDisable(true);

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // TODO
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

    void loadChoiseBox() {
        ObservableList<PartOfSemester> observableList = FXCollections.observableArrayList();
        observableList.addAll(PartOfSemester.FRIST, PartOfSemester.SECONED);
        cbPartYear.setItems(observableList);
        cbSearchPartYear.setItems(observableList);
    }
}
