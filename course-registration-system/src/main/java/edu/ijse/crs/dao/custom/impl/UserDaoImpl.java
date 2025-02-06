package edu.ijse.crs.dao.custom.impl;

import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.custom.UserDao;
import edu.ijse.crs.entity.UserEntity;
import edu.ijse.crs.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

    @Override
    public void save(UserEntity userEntity) throws Exception {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(UserEntity entity) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public UserEntity search(String id) throws Exception {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        UserEntity userEntity = session.get(UserEntity.class, id);
        session.getTransaction().commit();
        session.close();
        return userEntity;
    }

    @Override
    public List<UserEntity> getAll() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

}
