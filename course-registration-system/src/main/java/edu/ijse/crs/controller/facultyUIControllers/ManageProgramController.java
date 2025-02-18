package edu.ijse.crs.controller.facultyUIControllers;

import java.util.List;

import edu.ijse.crs.controller.FacultyUIController;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.ProgramService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;

public class ManageProgramController {
    public static FacultyDTO facultyDTO;

    @FXML
    private TableColumn<ProgramDTO, String> colId;

    @FXML
    private TableColumn<ProgramDTO, String> colTitle;

    @FXML
    private TableColumn<ProgramDTO, Integer> colTotalSemester;

    @FXML
    private TableView<ProgramDTO> tblProgram;

    @FXML
    private TextField txtProgramId;

    @FXML
    private TextField txtProgramName;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtTotalSemester;

    ProgramService programService = (ProgramService) ServiceFactory.getInstance().getService(ServiceTypes.PROGRAM);
    Alert alert = new Alert(AlertType.INFORMATION);

    public void initialize() {
        ManageProgramController.facultyDTO = FacultyUIController.facultyDTO;
        loadTable();

        colId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("programTitle"));
        colTotalSemester.setCellValueFactory(new PropertyValueFactory<>("totalSemester"));

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        alert.setHeaderText(null);

        if (txtProgramId.getText().isEmpty() ||
                txtProgramName.getText().isEmpty() ||
                txtTotalSemester.getText().isEmpty()) {

            alert.setContentText("All Fields Required");
            alert.showAndWait();
            return;
        }
        int totalSemester= Integer.parseInt(txtTotalSemester.getText());
        if(totalSemester<=0){
            alert.setHeaderText("Total Semesters Wrong ");
            alert.setContentText("Must be more then 0");
            alert.showAndWait();
            return;
        }
        try {
            String saveProgram = programService.saveProgram(new ProgramDTO(txtProgramId.getText(),
                    txtProgramName.getText(), Integer.parseInt(txtTotalSemester.getText()),
                    ManageProgramController.facultyDTO));
            alert.setContentText(saveProgram);
            alert.showAndWait();
            loadTable();
        } catch (Exception e) {
            alert.setContentText("Error saving program");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        System.out.println();
        // TODO
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // TODO
    }

    public void loadTable() {
        try {
            List<ProgramDTO> table = programService.loadTable(ManageProgramController.facultyDTO);
            ObservableList<ProgramDTO> observableList = FXCollections.observableArrayList();
            observableList.addAll(table);
            tblProgram.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
