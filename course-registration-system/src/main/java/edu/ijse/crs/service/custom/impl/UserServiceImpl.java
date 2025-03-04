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
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {
    UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoTypes.USER);

    @Override
    public void saveUser(UserDTO userDTO) throws Exception {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        userDao.save(EntityDTOConversion.toUserEntity(userDTO), session);
        session.getTransaction().commit();
    }

    @Override
    public UserDTO login(String userName, String password) throws Exception {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        UserEntity searchUserEntity = userDao.search(userName, session);
        session.getTransaction().commit();
        session.close();

        if (searchUserEntity != null) {
            System.out.println(hashPassword(password));
            if (checkPassword(password,searchUserEntity.getPassword())) {
                return EntityDTOConversion.toUserDTO(searchUserEntity);
            } else {
                throw new RuntimeException("Invalid Password");
            }
        } else {
            throw new RuntimeException("Invalid User Name");
        }
    }

    public static String hashPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password,hashedPassword);
    }
}
