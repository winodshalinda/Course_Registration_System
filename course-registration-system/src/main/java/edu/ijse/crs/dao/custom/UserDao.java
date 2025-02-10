package edu.ijse.crs.dao.custom;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.UserEntity;

public interface UserDao extends CrudDao<UserEntity, String, Session> {
    
}
