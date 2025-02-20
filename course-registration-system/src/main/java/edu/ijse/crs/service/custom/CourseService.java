package edu.ijse.crs.service.custom;

import java.util.List;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.service.SuperService;

public interface CourseService extends SuperService{
    String saveCourse(CourseDTO courseDTO,List<CourseDTO> prerequisites);
    List<CourseDTO> AddPrerequisites(String id,List<CourseDTO> prerequisites) throws Exception;
}
