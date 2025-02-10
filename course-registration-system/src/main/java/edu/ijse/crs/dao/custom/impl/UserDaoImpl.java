package edu.ijse.crs.dao.custom.impl;

import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.custom.UserDao;
import edu.ijse.crs.entity.UserEntity;

public class UserDaoImpl implements UserDao {

    @Override
    public Boolean save(UserEntity userEntity, Session session) throws Exception {
        return session.save(userEntity)!=null;
    }

    @Override
    public UserEntity search(String id, Session session) throws Exception {
        UserEntity userEntity = session.get(UserEntity.class, id);
        return userEntity;
    }

    @Override
    public void update(UserEntity entity, Session session) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id, Session session) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<UserEntity> getAll(Session session) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
}
