package edu.ijse.crs.service.custom;

import java.util.List;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.ProgramDetailsDTO;
import edu.ijse.crs.dto.SemesterDTO;
import edu.ijse.crs.dto.StudentDTO;
import edu.ijse.crs.service.SuperService;

public interface EnrollmentService extends SuperService{
    String enrollCourse(StudentDTO studentDTO, CourseDTO courseDTO, SemesterDTO semesterDTO);

    SemesterDTO canEnroll(StudentDTO studentDTO);

    List<ProgramDetailsDTO> getStudentProgramCourse(StudentDTO studentDTO) throws Exception;

    CourseDTO searchCourse(String id, List<ProgramDetailsDTO> detailsDTOs) throws Exception;
}
