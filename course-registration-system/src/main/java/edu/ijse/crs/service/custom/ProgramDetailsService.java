package edu.ijse.crs.service.custom;

import java.util.List;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.dto.ProgramDetailsDTO;
import edu.ijse.crs.service.SuperService;

public interface ProgramDetailsService extends SuperService{
    List<ProgramDetailsDTO> loadTables(ProgramDTO programDTO)throws Exception;

    CourseDTO searchCourse(String text) throws Exception;
}
