package edu.ijse.crs.service.custom;

import java.util.List;

import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.service.SuperService;

public interface FacultyService extends SuperService{
    String saveFaculty(FacultyDTO facultyDTO) throws Exception;
    String updateFaculty(FacultyDTO facultyDTO) throws Exception;
    String deleteFaculty(String id) throws Exception;
    FacultyDTO searchFaculty(String id) throws Exception;
    List<FacultyDTO> getAllFaculties() throws Exception;
}
