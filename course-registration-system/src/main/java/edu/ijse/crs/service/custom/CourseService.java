package edu.ijse.crs.service.custom;

import java.util.List;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.DepartmentDTO;
import edu.ijse.crs.service.SuperService;

public interface CourseService extends SuperService{
    String saveCourse(CourseDTO courseDTO,List<CourseDTO> prerequisites);

    List<CourseDTO> AddPrerequisites(String id,List<CourseDTO> prerequisites) throws Exception;
    List<CourseDTO> RemovePrerequisites(String id,List<CourseDTO> prerequisites) throws Exception;

    CourseDTO searchCourse(String id,DepartmentDTO departmentDTO) throws Exception;

    List<CourseDTO> getCourseAllPrerequisites(CourseDTO courseDTO) throws Exception;

    List<CourseDTO> loadTable(DepartmentDTO departmentDTO) throws Exception;

    String updateCourse(CourseDTO courseDTO, List<CourseDTO> prerequisites);

    String deleteCourse(CourseDTO courseDTO);

    CourseDTO searchCourse(String text) throws Exception;
}
