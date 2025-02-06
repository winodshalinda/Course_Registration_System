package edu.ijse.crs.util;

import edu.ijse.crs.dto.UserDTO;
import edu.ijse.crs.entity.UserEntity;

public class EntityDTOConversion {
    // User
    public static UserEntity toUserEntity(UserDTO dto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(dto.getUserName());
        userEntity.setPassword(dto.getPassword());
        userEntity.setRole(dto.getRole());
        userEntity.setStudentOrFacultyId(dto.getStudentOrFacultyId());
        return userEntity;
    }

    public static UserDTO toUserDTO(UserEntity entity){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(entity.getUserName());
        userDTO.setPassword(entity.getPassword());
        userDTO.setRole(entity.getRole());
        userDTO.setStudentOrFacultyId(entity.getStudentOrFacultyId());
        return userDTO;
    }
}
