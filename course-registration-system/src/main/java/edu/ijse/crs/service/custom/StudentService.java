package edu.ijse.crs.service.custom;

import java.util.List;

import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.dto.StudentDTO;
import edu.ijse.crs.service.SuperService;

public interface StudentService extends SuperService {
    String saveStudent() throws Exception;

    List<StudentDTO> loadTable() throws Exception;

    List<ProgramDTO> loadChoiseBox() throws Exception;
}
