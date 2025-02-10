package edu.ijse.crs.service.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.DaoFactory;
import edu.ijse.crs.dao.DaoFactory.DaoTypes;
import edu.ijse.crs.dao.custom.UserDao;
import edu.ijse.crs.dto.UserDTO;
import edu.ijse.crs.entity.UserEntity;
import edu.ijse.crs.service.custom.UserService;
import edu.ijse.crs.util.EntityDTOConversion;
import edu.ijse.crs.util.HibernateUtil;

public class UserServiceImpl implements UserService {
    UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoTypes.USER);
    Session session = HibernateUtil.getSession();

    @Override
    public void saveUser(UserDTO userDTO) throws Exception {
        session.beginTransaction();
        userDao.save(EntityDTOConversion.toUserEntity(userDTO), session);
        session.getTransaction().commit();
    }

    @Override
    public UserDTO login(String userName, String password) throws Exception {
        
        session.beginTransaction();
        UserEntity searchUserEntity = userDao.search(userName, session);

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
