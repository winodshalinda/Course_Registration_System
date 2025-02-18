package edu.ijse.crs.dao.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.UserDao;
import edu.ijse.crs.entity.FacultyEntity;
import edu.ijse.crs.entity.StudentEntity;
import edu.ijse.crs.entity.UserEntity;

public class UserDaoImpl extends CrudUtil<UserEntity, String, Session> implements UserDao {

    public UserDaoImpl(Class<UserEntity> entityClass) {
        super(entityClass);
    }

    public Boolean deleteByFacultyId(FacultyEntity entity, Session session) throws Exception {
        return session.createQuery("DELETE FROM UserEntity WHERE faculty = :entity").setParameter("entity", entity)
                .executeUpdate() > 0;
    }

    @Override
    public Boolean deleteByStudentId(StudentEntity entity, Session session) throws Exception {
        return session.createQuery("DELETE FROM UserEntity WHERE student = :entity").setParameter("entity", entity)
                .executeUpdate() > 0;
    }
}
