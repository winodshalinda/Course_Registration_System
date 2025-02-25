package edu.ijse.crs.service.custom;

import java.util.List;

import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.dto.SemesterDTO;
import edu.ijse.crs.service.SuperService;

public interface SemesterService extends SuperService {

    List<SemesterDTO> loadTable(FacultyDTO facultyDTO) throws Exception;

    String saveSemester(SemesterDTO semesterDTO);

    SemesterDTO searchSemester(SemesterDTO semesterDTO);

    String updateSemester(SemesterDTO semesterDTO);

    String deleteSemester(SemesterDTO semesterDTO);
    
}
