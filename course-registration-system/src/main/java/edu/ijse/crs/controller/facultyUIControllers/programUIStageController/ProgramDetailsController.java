package edu.ijse.crs.controller.facultyUIControllers.programUIStageController;

import java.util.List;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.dto.ProgramDetailsDTO;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.ProgramDetailsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
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
    private HBox hBoxCourseTable;

    Alert alert = new Alert(AlertType.INFORMATION);

    private ProgramDTO programDTO;
    private CourseDTO courseDTO;

    ProgramDetailsService detailsService = (ProgramDetailsService) ServiceFactory.getInstance()
            .getService(ServiceTypes.PROGRAMDETAILS);

    public void setProgramDTO(ProgramDTO programDTO) {
        this.programDTO = programDTO;
        lblProgramName.setText(programDTO.getProgramTitle());
        loadTables();
    }

    public void initialize() {
        alert.setHeaderText(null);

    }

    @FXML
    void btnAddOrRemoveOnAction(ActionEvent event) {

        tFlowCourse.getChildren().clear();

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
                loadChoiceBox();

                txtSearch.setDisable(true);
                btnAddOrRemove.setVisible(false);
                btnAdd.setVisible(true);
                btnCancel.setVisible(true);
                btnRemove.setVisible(true);
                lblSemester.setVisible(true);
                choiceBoxSemester.setVisible(true);
                tFlowCourse.setVisible(true);
                tFlowCourse.getChildren().add(new Text(courseDTO.toShow()));
            }

        } catch (Exception e) {
            alert.setContentText("Course Search Error: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();

        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        if (choiceBoxSemester.getValue() == null) {
            alert.setContentText("Please Select Semester");
            alert.show();

        } else {

            ProgramDetailsDTO programDetailsDTO = new ProgramDetailsDTO(choiceBoxSemester.getValue(), courseDTO,
                    programDTO);
            String course;
            try {
                course = detailsService.addCourse(programDetailsDTO);
                alert.setContentText(course);
                alert.show();
                loadTables();
                btnCancelOnAction(event);

            } catch (Exception e) {
                alert.setContentText("Adding Course Failed");
                alert.show();

            }

        }
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {

        ProgramDetailsDTO programDetailsDTO = new ProgramDetailsDTO();
        programDetailsDTO.setCourse(courseDTO);
        programDetailsDTO.setProgram(programDTO);

        String removeCoures = detailsService.removeCourse(programDetailsDTO);
        alert.setContentText(removeCoures);
        alert.show();
        loadTables();
        btnCancelOnAction(event);
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

        txtSearch.setDisable(false);

        courseDTO = null;
        btnAddOrRemove.setVisible(true);
        btnAdd.setVisible(false);
        btnCancel.setVisible(false);
        btnRemove.setVisible(false);
        lblSemester.setVisible(false);
        choiceBoxSemester.setVisible(false);
        tFlowCourse.getChildren().clear();
        tFlowCourse.setVisible(false);
        choiceBoxSemester.setValue(null);
    }

    void loadTables() {
        hBoxCourseTable.getChildren().clear();
        alert.setHeaderText(null);

        try {
            List<ProgramDetailsDTO> tables = detailsService.loadTables(programDTO);

            for (int i = 0; i < programDTO.getTotalSemester(); i++) {

                TextFlow textFlow = new TextFlow();
                textFlow.setPrefWidth(300);
                textFlow.setPrefHeight(hBoxCourseTable.getPrefHeight());

                Text semester = new Text("Semester " + (i + 1) + "\n\n");

                semester.setStyle(
                        "-fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 1px;");

                textFlow.getChildren().add(semester);

                for (ProgramDetailsDTO programDetailsDTO : tables) {

                    if ((i + 1) == programDetailsDTO.getSemester()) {
                        textFlow.getChildren().add(new Text(programDetailsDTO.getCourse().toString() + "\n"));
                    }

                }

                textFlow.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
                hBoxCourseTable.getChildren().add(textFlow);
            }

        } catch (Exception e) {
            alert.setContentText("Table Load Failed");
            alert.show();
            e.printStackTrace();
        }
    }

    void loadChoiceBox() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList();
        for (int i = 1; i <= programDTO.getTotalSemester(); i++) {
            observableList.add(i);
        }
        choiceBoxSemester.setItems(observableList);
    }
}
