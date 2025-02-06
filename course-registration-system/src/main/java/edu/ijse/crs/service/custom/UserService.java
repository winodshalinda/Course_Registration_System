package edu.ijse.crs.service.custom;

import edu.ijse.crs.dto.UserDTO;
import edu.ijse.crs.service.SuperService;

public interface UserService extends SuperService{
    void saveUser(UserDTO userDTO) throws Exception;
    UserDTO login(UserDTO userDTO) throws Exception;
}
