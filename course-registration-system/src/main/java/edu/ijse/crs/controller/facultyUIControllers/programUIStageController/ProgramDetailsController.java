package edu.ijse.crs.controller.facultyUIControllers.programUIStageController;

import java.util.List;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.dto.ProgramDetailsDTO;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.ProgramDetailsService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class ProgramDetailsController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddOrRemove;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRemove;

    @FXML
    private ChoiceBox<Integer> choiceBoxSemester;

    @FXML
    private Label lblProgramName;

    @FXML
    private Label lblSemester;

    @FXML
    private TextFlow tFlowCourse;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<ProgramDetailsDTO> tblProgramCourse;

    Alert alert = new Alert(AlertType.INFORMATION);

    private ProgramDTO programDTO;
    private CourseDTO courseDTO;

    ProgramDetailsService detailsService = (ProgramDetailsService) ServiceFactory.getInstance()
            .getService(ServiceTypes.PROGRAMDETAILS);

    public void setProgramDTO(ProgramDTO programDTO) {
        this.programDTO = programDTO;
        lblProgramName.setText(programDTO.getProgramTitle());
        createColumn();
        loadTables();
    }

    public void initialize() {
        alert.setHeaderText(null);
        createColumn();
        loadTables();
        loadChoiceBox();
        // TODO
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnAddOrRemoveOnAction(ActionEvent event) {

        if (txtSearch.getText().isEmpty()) {
            alert.setContentText("Search Field Empty");
            alert.showAndWait();
            return;
        }

        try {
            
            courseDTO = detailsService.searchCourse(txtSearch.getText());

            if (courseDTO == null) {
                alert.setContentText("Course Not Found");
                alert.showAndWait();

            } else {
                btnAddOrRemove.setVisible(false);
                btnAdd.setVisible(true);
                btnCancel.setVisible(true);
                btnRemove.setVisible(true);
                lblSemester.setVisible(true);
                choiceBoxSemester.setVisible(true);
            }

        } catch (Exception e) {
            alert.setContentText("Course Search Error: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();

        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        // TODO
    }

    void createColumn() {

        for (int i = 1; i <= programDTO.getTotalSemester(); i++) {

            final int semesterNumber = i;

            TableColumn<ProgramDetailsDTO, String> colSemester = new TableColumn<>("Semester " + semesterNumber);

            colSemester.setCellValueFactory(cellData -> {
                ProgramDetailsDTO detailsDTO = cellData.getValue();

                if (detailsDTO.getSemester() == semesterNumber) {
                    return new SimpleStringProperty(detailsDTO.getCourse().toString());
                } else {
                    return null;
                }
            });

            tblProgramCourse.getColumns().add(colSemester);
        }

    }

    void loadTables() {
        alert.setHeaderText(null);

        try {
            List<ProgramDetailsDTO> tables = detailsService.loadTables(programDTO);
            ObservableList<ProgramDetailsDTO> observableList = FXCollections.observableArrayList(tables);
            tblProgramCourse.setItems(observableList);

        } catch (Exception e) {
            alert.setContentText("Table Load Failed");
            alert.show();
            e.printStackTrace();
        }
    }

    void loadChoiceBox() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < programDTO.getTotalSemester(); i++) {
            observableList.add((i + 1));
        }
        choiceBoxSemester.setItems(observableList);
    }
}
