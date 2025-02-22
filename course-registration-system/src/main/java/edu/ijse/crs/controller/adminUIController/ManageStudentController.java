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
import javafx.scene.layout.Pane;

public class ManageStudentController {

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

    public void initialize(){
        
        // Student Table Load

        loadTable();
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        //Program ChoiseBox Load
        loadChoiseProgram();   
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

    public void loadChoiseProgram(){
        try {
            List<ProgramDTO> choice = studentService.loadChoiseBox();
            System.out.println(choice.size());
            ObservableList<ProgramDTO> observableList=FXCollections.observableArrayList(choice);
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
}
