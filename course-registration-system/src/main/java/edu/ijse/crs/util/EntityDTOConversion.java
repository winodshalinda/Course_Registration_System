package edu.ijse.crs.util;

import java.util.ArrayList;
import java.util.List;

import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.DepartmentDTO;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.dto.PrerequisitesDTO;
import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.dto.UserDTO;
import edu.ijse.crs.entity.CourseEntity;
import edu.ijse.crs.entity.DepartmentEntity;
import edu.ijse.crs.entity.FacultyEntity;
import edu.ijse.crs.entity.PrerequisitesEntity;
import edu.ijse.crs.entity.ProgramEntity;
import edu.ijse.crs.entity.UserEntity;

public class EntityDTOConversion {
    // User
    public static UserEntity toUserEntity(UserDTO dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(dto.getUserName());
        userEntity.setPassword(dto.getPassword());
        userEntity.setRole(dto.getRole());
        userEntity.setFaculty(toFacultyEntity(dto.getFacultyDTO()));
        userEntity.setStudent(null);// TODO
        return userEntity;
    }

    public static UserDTO toUserDTO(UserEntity entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(entity.getUserName());
        userDTO.setPassword(entity.getPassword());
        userDTO.setRole(entity.getRole());
        userDTO.setFacultyDTO(toFacultyDTO(entity.getFaculty()));
        return userDTO;
    }

    // Faculty

    public static FacultyEntity toFacultyEntity(FacultyDTO dto) {
        return new FacultyEntity(
                dto.getFacultyId(), dto.getFacultyName());
    }

    public static FacultyDTO toFacultyDTO(FacultyEntity entity) {
        if (entity == null) {
            return null;
        }
        return new FacultyDTO(entity.getFacultyId(), entity.getFacultyName(), null, null);
    }

    public static List<FacultyDTO> toListFacultyDTOs(List<FacultyEntity> entityList) {
        List<FacultyDTO> dtoList = new ArrayList<FacultyDTO>();
        for (FacultyEntity entity : entityList) {
            FacultyDTO facultyDTO = toFacultyDTO(entity);
            dtoList.add(facultyDTO);
        }
        return dtoList;
    }

    // Program

    public static ProgramEntity toProgramEntity(ProgramDTO dto) {
        return new ProgramEntity(dto.getProgramId(),
                dto.getProgramTitle(),
                dto.getTotalSemester(),
                toFacultyEntity(dto.getFacultyDTO()));
    }

    public static ProgramDTO toProgramDTO(ProgramEntity entity) {
        return new ProgramDTO(entity.getProgramId(),
                entity.getProgramTitle(),
                entity.getTotalSemester(),
                toFacultyDTO(entity.getFaculty()));
    }

    // Department

    public static DepartmentDTO toDepartmentDTO(DepartmentEntity entity) {
        return new DepartmentDTO(entity.getDepartmentId(),
                entity.getDepartmentName(),
                toFacultyDTO(entity.getFaculty()));
    }

    public static DepartmentEntity toDepartmentEntity(DepartmentDTO dto) {
        return new DepartmentEntity(dto.getDepartmentId(),
                dto.getDepartmentName(),
                toFacultyEntity(dto.getFacultyDTO()));
    }

    // Course

    public static CourseEntity toCourseEntity(CourseDTO dto) {
        return new CourseEntity(dto.getCourseId(),
                dto.getCourseTitle(),
                dto.getEnrollmentCapacity(),
                dto.getCreditHours(),
                toDepartmentEntity(dto.getDepartmentDTO()));
    }

    public static CourseDTO toCourseDTO(CourseEntity entity) {
        return new CourseDTO(entity.getCourseId(),
                entity.getCourseTitle(),
                entity.getEnrollmentCapacity(),
                entity.getCreditHours(),
                toDepartmentDTO(entity.getDepartment()));
    }

    // Prerequisites

    public static PrerequisitesDTO toPrerequisitesDTO(PrerequisitesEntity entity) {
        return new PrerequisitesDTO(
                toCourseDTO(entity.getCourse()),
                toCourseDTO(entity.getPrerequisitesCourse()));
    }

    public static PrerequisitesEntity toPrerequisitesEntity(PrerequisitesDTO dto) {
        return new PrerequisitesEntity(
                toCourseEntity(dto.getCourseDTO()),
                toCourseEntity(dto.getPrerequisitesDTO()));
    }
}
