package edu.ijse.crs.controller.studentUIControllers;

import java.util.List;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.ProgramDetailsDTO;
import edu.ijse.crs.dto.StudentDTO;
import edu.ijse.crs.exception.CustomException;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.CourseService;
import edu.ijse.crs.service.custom.ProgramDetailsService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class StudentHomeController {

    @FXML
    private HBox hBoxProgramDetails;

    @FXML
    private TextFlow textFlowCourse;

    @FXML
    private TextField txtSearch;

    private StudentDTO studentDTO;

    Alert alert = new Alert(AlertType.INFORMATION);

    ProgramDetailsService detailsService = (ProgramDetailsService) ServiceFactory.getInstance()
            .getService(ServiceTypes.PROGRAMDETAILS);
    CourseService courseService = (CourseService) ServiceFactory.getInstance().getService(ServiceTypes.COURSE);

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        textFlowCourse.getChildren().clear();

        alert.setHeaderText(null);

        if (txtSearch.getText().isEmpty()) {
            alert.setContentText("Search Field Empty");
            alert.show();
            return;
        }
        try {

            CourseDTO searchCourse = courseService.searchCourse(txtSearch.getText());

            if (searchCourse == null) {
                return;
            }

            textFlowCourse.setVisible(true);

            List<CourseDTO> courseAllPrerequisites = courseService.getCourseAllPrerequisites(searchCourse);

            textFlowCourse.getChildren().addAll(
                    new Text(searchCourse.toShow()),
                    new Text("Prerequisites Courses:-\n"));

            if (courseAllPrerequisites.isEmpty()) {
                // textFlowCourse.getChildren().remove(1);
                textFlowCourse.getChildren().add(new Text("\t\t\"N/A\""));
            }

            for (CourseDTO courseDTO : courseAllPrerequisites) {
                textFlowCourse.getChildren()
                        .addAll(new Text("\t\t" + courseDTO.getCourseTitle() + " (" + courseDTO.getCourseId() + ")\n"));
            }

        } catch (CustomException e) {
            alert.setContentText(e.getMessage());
            alert.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadProgram() {
        hBoxProgramDetails.getChildren().clear();
        alert.setHeaderText(null);

        try {
            List<ProgramDetailsDTO> tables = detailsService.loadTables(studentDTO.getProgram());

            for (int i = 0; i < studentDTO.getProgram().getTotalSemester(); i++) {

                TextFlow textFlow = new TextFlow();
                textFlow.setPrefWidth(300);
                textFlow.setPrefHeight(hBoxProgramDetails.getPrefHeight());

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
                hBoxProgramDetails.getChildren().add(textFlow);
            }

        } catch (Exception e) {
            alert.setContentText("Table Load Failed");
            alert.show();
            e.printStackTrace();
        }
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
        loadProgram();
    }

}
