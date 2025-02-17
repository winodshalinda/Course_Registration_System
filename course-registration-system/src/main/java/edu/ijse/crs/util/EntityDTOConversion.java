package edu.ijse.crs.util;

import java.util.ArrayList;
import java.util.List;

import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.dto.UserDTO;
import edu.ijse.crs.entity.FacultyEntity;
import edu.ijse.crs.entity.UserEntity;

public class EntityDTOConversion {
    // User
    public static UserEntity toUserEntity(UserDTO dto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(dto.getUserName());
        userEntity.setPassword(dto.getPassword());
        userEntity.setRole(dto.getRole());
        userEntity.setFaculty(toFacultyEntity(dto.getFacultyDTO()));
        userEntity.setStudent(null);
        return userEntity;
    }

    public static UserDTO toUserDTO(UserEntity entity){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(entity.getUserName());
        userDTO.setPassword(entity.getPassword());
        userDTO.setRole(entity.getRole());
        userDTO.setFacultyDTO(EntityDTOConversion.toFacultyDTO(entity.getFaculty()));
        return userDTO;
    }


    //Faculty

    public static FacultyEntity toFacultyEntity(FacultyDTO dto){
        return new FacultyEntity(
            dto.getFacultyId(),dto.getFacultyName()
        );
    }

    public static FacultyDTO toFacultyDTO(FacultyEntity entity){
        if(entity==null){
            return null;
        }
        return new FacultyDTO(entity.getFacultyId(), entity.getFacultyName(), null, null);
    }

    public static List<FacultyDTO> toListFacultyDTOs(List<FacultyEntity> entityList){
        List<FacultyDTO> dtoList = new ArrayList<FacultyDTO>();
        for (FacultyEntity entity : entityList) {
            FacultyDTO facultyDTO = toFacultyDTO(entity);
            dtoList.add(facultyDTO);
        }
        return dtoList;
    }
}
