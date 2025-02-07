package edu.ijse.crs.service.custom.impl;

import edu.ijse.crs.dao.DaoFactory;
import edu.ijse.crs.dao.DaoFactory.DaoTypes;
import edu.ijse.crs.dao.custom.UserDao;
import edu.ijse.crs.dto.UserDTO;
import edu.ijse.crs.entity.UserEntity;
import edu.ijse.crs.service.custom.UserService;
import edu.ijse.crs.util.EntityDTOConversion;

public class UserServiceImpl implements UserService {
    UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoTypes.USER);

    @Override
    public void saveUser(UserDTO userDTO) throws Exception {
        userDao.save(EntityDTOConversion.toUserEntity(userDTO));
    }

    @Override
    public UserDTO login(String userName, String password) throws Exception {
        UserEntity searchUserEntity = userDao.search(userName);

        if (searchUserEntity != null) {
            if (searchUserEntity.getPassword().equals(password)) {
                return EntityDTOConversion.toUserDTO(searchUserEntity);
            } else {
                throw new RuntimeException("Invalid Password");
            }
        } else {
            throw new RuntimeException("Invalid User Name");
        }
    }
}
