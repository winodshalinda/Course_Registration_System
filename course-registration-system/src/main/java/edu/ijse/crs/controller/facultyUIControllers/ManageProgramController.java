package edu.ijse.crs.controller.facultyUIControllers;

import java.util.List;

import javax.persistence.PersistenceException;

import edu.ijse.crs.controller.FacultyUIController;
import edu.ijse.crs.controller.facultyUIControllers.programUIStageController.ProgramDetailsController;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.exception.CustomException;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.ProgramService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

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
    private Button btnCancel;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCourse;

    @FXML
    private TextField txtProgramId;

    @FXML
    private TextField txtProgramName;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtTotalSemester;

    @FXML
    private TextFlow tFlowProgram;

    ProgramService programService = (ProgramService) ServiceFactory.getInstance().getService(ServiceTypes.PROGRAM);
    Alert alert = new Alert(AlertType.INFORMATION);

    private ProgramDTO programDTO;

    public void initialize() {
        ManageProgramController.facultyDTO = FacultyUIController.facultyDTO;
        loadTable();
        
        // Only allows numeric input in txtTotalSemester
        txtTotalSemester.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
            txtTotalSemester.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });


        colId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("programTitle"));
        colTotalSemester.setCellValueFactory(new PropertyValueFactory<>("totalSemester"));

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearform();
        btnSave.setVisible(true);
        btnCancel.setVisible(false);
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);
        btnCourse.setVisible(false);
        txtProgramId.setDisable(false);
        tFlowProgram.setVisible(false);
        tFlowProgram.getChildren().clear();
        programDTO=null;
    }

    @FXML // TODO
    void btnDeleteOnAction(ActionEvent event) {
        String deleteProgram;
        try {
            deleteProgram = programService.deleteProgram(programDTO);

            alert.setContentText(deleteProgram);
            alert.show();
            loadTable();
            btnCancelOnAction(event);

        } catch (CustomException e) {
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (Exception e) {
            alert.setContentText(e.getMessage());
            alert.show();
            e.printStackTrace();
        }
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

        int totalSemester = Integer.parseInt(txtTotalSemester.getText());

        if (totalSemester <= 0) {
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
        }catch(PersistenceException e){
            alert.setContentText("Program ID Already Exists");
            alert.show();
        } catch (Exception e) {
            alert.setContentText("Error saving program");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        tFlowProgram.getChildren().clear();
        alert.setHeaderText(null);
        
        if (txtSearch.getText().isEmpty()) {
            alert.setContentText("Search Field Empty");
            alert.showAndWait();
            return;
        }
        try {
            programDTO = programService.searchProgram(txtSearch.getText(), facultyDTO.getFacultyId());

            txtProgramId.setText(programDTO.getProgramId());
            txtProgramName.setText(programDTO.getProgramTitle());
            txtTotalSemester.setText(String.valueOf(programDTO.getTotalSemester()));

            btnSave.setVisible(false);
            btnCancel.setVisible(true);
            btnUpdate.setVisible(true);
            btnDelete.setVisible(true);
            btnCourse.setVisible(true);
            txtProgramId.setDisable(true);

            tFlowProgram.setVisible(true);
            tFlowProgram.getChildren().addAll(new Text(programDTO.toString(1)));

        } catch (CustomException e) {
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (Exception e) {
            alert.setContentText("Program Service Error");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (txtProgramName.getText().isEmpty() || txtTotalSemester.getText().isEmpty()) {

            alert.setContentText("All Fields Required");
            alert.show();
            return;
        }

        int totalSemester = Integer.parseInt(txtTotalSemester.getText());

        if (totalSemester <= 0) {
            alert.setHeaderText("Total Semesters Wrong ");
            alert.setContentText("Must be more then 0");
            alert.showAndWait();
            return;
        }
        try {
            String updateProgram = programService.updateProgram(new ProgramDTO(
                    programDTO.getProgramId(),
                    txtProgramName.getText(),
                    totalSemester,
                    ManageProgramController.facultyDTO));

            alert.setContentText(updateProgram);
            alert.show();
            loadTable();
            btnCancelOnAction(event);
        } catch (CustomException e) {
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnCourseOnAction(ActionEvent event) {
        Stage stage=new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/ijse/crs/view/facultyUiPane/programUiStage/ProgramDetailsUIStage.fxml"));
            stage.setScene(new Scene(loader.load()));
            // send programDTO loaded UI
            ProgramDetailsController controller = loader.getController();
            controller.setProgramDTO(programDTO);

            stage.setTitle("Program Course Management");
            stage.show();
        } catch (Exception e) {
            alert.setContentText("Window load Field");
            alert.show();
            e.printStackTrace();
        }
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

    public void clearform() {
        txtProgramId.clear();
        txtProgramName.clear();
        txtTotalSemester.clear();
    }

}
