package edu.ijse.crs.controller.adminUIController;

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

    StudentService studentService = (StudentService) ServiceFactory.getInstance().getService(ServiceTypes.STUDENT);

    Alert alert = new Alert(AlertType.INFORMATION);

    private StudentDTO studentDTO;

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
        // TODO
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
        // TODO
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearForm();
        btnSave.setVisible(true);
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);
        btnCancel.setVisible(false);
        txtId.setDisable(false);
        txtPassword.setVisible(true);
        txtRePassword.setVisible(true);
        studentDTO=null;
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
