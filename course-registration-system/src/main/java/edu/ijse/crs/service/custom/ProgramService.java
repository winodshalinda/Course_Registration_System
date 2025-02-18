package edu.ijse.crs.service.custom;

import java.util.List;

import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.service.SuperService;

public interface ProgramService extends SuperService{
    String saveProgram(ProgramDTO programDTO) throws Exception;
    List<ProgramDTO> loadTable(FacultyDTO facultyDTO) throws Exception;
}
