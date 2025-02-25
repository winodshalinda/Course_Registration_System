package edu.ijse.crs.controller.adminUIControllers;

import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.dto.StudentDTO;

import java.time.LocalDate;
import java.util.List;

import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ManageStudentController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<ProgramDTO> choiceProgram;

    @FXML
    private TableColumn<StudentDTO, String> colAddress;

    @FXML
    private TableColumn<StudentDTO, LocalDate> colDob;

    @FXML
    private TableColumn<StudentDTO, String> colEmail;

    @FXML
    private TableColumn<StudentDTO, String> colId;

    @FXML
    private TableColumn<StudentDTO, String> colName;

    @FXML
    private TableColumn<StudentDTO, String> colProgram;

    @FXML
    private TableColumn<StudentDTO, Integer> colYear;

    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private Pane paneStudentDetails;

    @FXML
    private TableView<StudentDTO> tblStudent;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtRePassword;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtYear;

    @FXML
    private TextFlow tFlowStudent;

    StudentService studentService = (StudentService) ServiceFactory.getInstance().getService(ServiceTypes.STUDENT);

    Alert alert = new Alert(AlertType.INFORMATION);

    private StudentDTO searchStudentDTO;

    public void initialize() {

        // Student Table Load

        loadTable();
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Program ChoiseBox Load
        loadChoiseProgram();

        // Only allows numeric input in txtYear field
        txtYear.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtYear.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        alert.setHeaderText(null);

        try {

            boolean deleteStudent = studentService.deleteStudent(searchStudentDTO);

            if (deleteStudent) {

                alert.setContentText("Student Deleted Successfully");
                alert.show();
                btnCancelOnAction(event);
                loadTable();

            }else{

                alert.setContentText("Student Delete Failed");
                alert.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            alert.setContentText("Student Delete Error: "+e.getMessage());
            alert.show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        alert.setHeaderText(null);

        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() ||
                dpDateOfBirth.getValue() == null || choiceProgram.getValue() == null ||
                txtYear.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                txtAddress.getText().isEmpty() || txtPassword.getText().isEmpty()
                || txtRePassword.getText().isEmpty()) {

            alert.setContentText("All Field Required");
            alert.show();
            return;

        } else if (txtPassword.getText().equals(txtRePassword.getText())) {

            StudentDTO studentDTO = new StudentDTO(
                    txtId.getText(),
                    txtName.getText(),
                    dpDateOfBirth.getValue(),
                    choiceProgram.getValue(),
                    Integer.parseInt(txtYear.getText()),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    txtPassword.getText(),
                    txtRePassword.getText());

            String saveStudent = studentService.saveStudent(studentDTO);
            if (saveStudent.equals("Student Save Successfully")) {
                clearForm();
            }
            alert.setContentText(saveStudent);
            alert.show();
            loadTable();
        } else {
            alert.setContentText("Password not match");
            alert.show();
            return;
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        clearForm();

        if (txtSearch.getText().isEmpty()) {
            alert.setContentText("Search Field Empty");
            alert.show();
            return;
        }

        try {
            searchStudentDTO = studentService.searchStudent(txtSearch.getText());

            if (searchStudentDTO == null) {
                alert.setContentText("Student Not Found");
                alert.show();

            } else {
                tFlowStudent.setVisible(true);
                tFlowStudent.getChildren().add(new Text(searchStudentDTO.toShow()));

                btnSave.setVisible(false);
                btnUpdate.setVisible(true);
                btnDelete.setVisible(true);
                btnCancel.setVisible(true);
                txtId.setDisable(true);
                txtPassword.setDisable(true);
                txtRePassword.setDisable(true);

                txtId.setText(searchStudentDTO.getStudentId());
                txtName.setText(searchStudentDTO.getStudentName());
                dpDateOfBirth.setValue(searchStudentDTO.getDob());
                choiceProgram.setValue(searchStudentDTO.getProgram());
                txtYear.setText(Integer.toString(searchStudentDTO.getYear()));
                txtEmail.setText(searchStudentDTO.getEmail());
                txtAddress.setText(searchStudentDTO.getAddress());

            }
        } catch (Exception e) {
            e.printStackTrace();
            alert.setContentText("Search Error " + e.getMessage());
            alert.show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        alert.setHeaderText(null);

        if (txtName.getText().isEmpty() || dpDateOfBirth.getValue() == null ||
                choiceProgram.getValue() == null || txtAddress.getText().isEmpty() ||
                txtYear.getText().isEmpty() || txtEmail.getText().isEmpty()) {

            alert.setContentText("All Field Required");
            alert.show();
            return;
        }

        StudentDTO studentDTO = new StudentDTO(
                searchStudentDTO.getStudentId(),
                txtName.getText(),
                dpDateOfBirth.getValue(),
                choiceProgram.getValue(),
                Integer.parseInt(txtYear.getText()),
                txtEmail.getText(),
                txtAddress.getText());
        
        Boolean isUpdateStudent = studentService.updateStudent(studentDTO);

        if (isUpdateStudent) {
            alert.setContentText("Student Update Successfully");
            alert.show();
            btnCancelOnAction(event);
            loadTable();
        } else {
            alert.setContentText("Student Update Failed");
            alert.show();
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearForm();
        btnSave.setVisible(true);
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);
        btnCancel.setVisible(false);
        txtId.setDisable(false);
        txtPassword.setDisable(false);
        txtRePassword.setDisable(false);
        tFlowStudent.setVisible(false);
        searchStudentDTO = null;
    }

    public void loadChoiseProgram() {
        try {
            List<ProgramDTO> choice = studentService.loadChoiseBox();
            System.out.println(choice.size());
            ObservableList<ProgramDTO> observableList = FXCollections.observableArrayList(choice);
            choiceProgram.setItems(observableList);

        } catch (Exception e) {
            alert.setHeaderText("ChoiceBox Load Failed");
            alert.setContentText(e.getMessage());
            alert.show();
            e.printStackTrace();
        }
    }

    public void loadTable() {

        try {
            List<StudentDTO> table = studentService.loadTable();
            ObservableList<StudentDTO> observableList = FXCollections.observableArrayList();
            observableList.addAll(table);
            tblStudent.setItems(observableList);

        } catch (Exception e) {
            alert.setHeaderText("Table Load Failed");
            alert.setContentText(e.getMessage());
            alert.show();
            e.printStackTrace();

        }

    }

    public void clearForm() {
        tFlowStudent.getChildren().clear();
        txtId.clear();
        txtName.clear();
        dpDateOfBirth.setValue(null);
        choiceProgram.setValue(null);
        txtYear.clear();
        txtEmail.clear();
        txtAddress.clear();
        txtPassword.clear();
        txtRePassword.clear();
    }
}
