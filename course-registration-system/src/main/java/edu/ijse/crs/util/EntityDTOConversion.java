package edu.ijse.crs.util;

import edu.ijse.crs.dto.UserDTO;
import edu.ijse.crs.entity.UserEntity;

public class EntityDTOConversion {
    // User
    public static UserEntity toEntity(UserDTO dto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(dto.getUserName());
        userEntity.setPassword(dto.getPassword());
        userEntity.setRole(dto.getRole());
        userEntity.setStudentOrFacultyId(dto.getStudentOrFacultyId());
        return userEntity;
    }
}
