package edu.ijse.crs.controller.facultyUIControllers.programUIStageController;

import edu.ijse.crs.dto.ProgramDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class ProgramDetailsController {

    @FXML
    private Label lblProgramName;

    @FXML
    private TableView<?> tblSemesters;

    private ProgramDTO programDTO;

    public ProgramDTO getProgramDTO() {
        return programDTO;
    }

    public void setProgramDTO(ProgramDTO programDTO) {
        this.programDTO = programDTO;
        lblProgramName.setText(programDTO.getProgramTitle());
    }

    public void initialize() {
    }

    //TODO
}
