package edu.ijse.crs.controller.facultyUIControllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.PersistenceException;

import edu.ijse.crs.controller.FacultyUIController;
import edu.ijse.crs.controller.facultyUIControllers.departmentUIStageController.ManageCourseController;
import edu.ijse.crs.dto.DepartmentDTO;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.exception.CustomException;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.DepartmentService;
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

public class ManageDepartmentController {
    public static FacultyDTO facultyDTO;

    @FXML
    private TableColumn<DepartmentDTO, String> colId;

    @FXML
    private TableColumn<DepartmentDTO, String> colName;

    @FXML
    private TableView<DepartmentDTO> tblDepartment;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnMngCourse;

    @FXML
    private TextField txtDepartmentId;

    @FXML
    private TextField txtDepartmentName;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextFlow tFlowDepartment;

    DepartmentService departmentService = (DepartmentService) ServiceFactory.getInstance()
            .getService(ServiceTypes.DEPARTMENT);
    Alert alert = new Alert(AlertType.INFORMATION);

    private DepartmentDTO departmentDTO;

    public void initialize() {
        ManageDepartmentController.facultyDTO = FacultyUIController.facultyDTO;
        loadTable();

        colId.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearform();
        btnSave.setVisible(true);
        btnCancel.setVisible(false);
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);
        txtDepartmentId.setDisable(false);
        tFlowDepartment.setVisible(false);
        tFlowDepartment.getChildren().clear();
        departmentDTO = null;
    }

    @FXML // TODO
    void btnDeleteOnAction(ActionEvent event) {
        try {
            String deleteDepartment = departmentService.deleteDepartment(departmentDTO);

            alert.setContentText(deleteDepartment);
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
    void btnSaveOnAction(ActionEvent event) {
        alert.setHeaderText(null);

        if (txtDepartmentId.getText().isEmpty() ||
                txtDepartmentName.getText().isEmpty()) {

            alert.setContentText("All Fields Required");
            alert.showAndWait();
            return;
        }

        try {
            String saveDepartment = departmentService.saveDepartment(new DepartmentDTO(
                    txtDepartmentId.getText(),
                    txtDepartmentName.getText(),
                    facultyDTO));

            alert.setContentText(saveDepartment);
            alert.showAndWait();
            loadTable();
        } catch (PersistenceException e) {
            alert.setContentText("Department ID Already Exists");
            alert.show();
        } catch (Exception e) {
            alert.setContentText("Error Saving Department");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        tFlowDepartment.getChildren().clear();
        alert.setHeaderText(null);

        if (txtSearch.getText().isEmpty()) {
            alert.setContentText("Search Field Empty");
            alert.showAndWait();
            return;
        }
        try {
            departmentDTO = departmentService.searchDepartment(txtSearch.getText(), facultyDTO.getFacultyId());

            txtDepartmentId.setText(departmentDTO.getDepartmentId());
            txtDepartmentName.setText(departmentDTO.getDepartmentName());

            btnSave.setVisible(false);
            btnCancel.setVisible(true);
            btnUpdate.setVisible(true);
            btnDelete.setVisible(true);
            btnMngCourse.setVisible(true);
            txtDepartmentId.setDisable(true);

            tFlowDepartment.setVisible(true);
            tFlowDepartment.getChildren().addAll(new Text(departmentDTO.toString()));

        } catch (CustomException e) {
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (Exception e) {
            alert.setContentText("Department Service Error");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (txtDepartmentName.getText().isEmpty()) {

            alert.setContentText("All Fields Required");
            alert.show();
            return;
        }

        try {
            String updateDepartment = departmentService.updateDepartment(new DepartmentDTO(
                    departmentDTO.getDepartmentId(),
                    txtDepartmentName.getText(),
                    facultyDTO));

            alert.setContentText(updateDepartment);
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
    void btnMngCourseOnAction(ActionEvent event) throws IOException {
        
        btnSearchOnAction(event);
        if (departmentDTO == null) {
            return;
        }

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/edu/ijse/crs/view/facultyUiPane/departmentUiStage/ManageCourseUIStage.fxml"));
        stage.setScene(new Scene(loader.load()));

        ManageCourseController controller = loader.getController();
        controller.setDepartmentDTO(departmentDTO);

        stage.setTitle("Course Management");
        stage.show();
    }

    public void loadTable() {
        try {
            List<DepartmentDTO> table = departmentService.loadTable(facultyDTO);
            ObservableList<DepartmentDTO> observableList = FXCollections.observableArrayList();
            observableList.addAll(table);
            tblDepartment.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearform() {
        txtDepartmentId.clear();
        txtDepartmentName.clear();
    }

}
