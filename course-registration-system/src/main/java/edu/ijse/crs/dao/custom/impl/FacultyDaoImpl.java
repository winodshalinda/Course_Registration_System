package edu.ijse.crs.dao.custom.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.ijse.crs.dao.custom.FacultyDao;
import edu.ijse.crs.entity.FacultyEntity;

public class FacultyDaoImpl implements FacultyDao {

    @Override
    public Boolean save(FacultyEntity entity, Session session) throws Exception {
        Object save = session.save(entity);
        System.out.println(save);
        return save != null;
    }

    @Override
    public void update(FacultyEntity entity, Session session) throws Exception {
        session.update(entity);
    }

    @Override
    public FacultyEntity search(String id, Session session) throws Exception {
        FacultyEntity entity = session.get(FacultyEntity.class, id);
        return entity;
    }

    @Override
    public void delete(String id, Session session) throws Exception {
        FacultyEntity entity = search(id, session);
        session.delete(entity);
    }

    @Override
    public List<FacultyEntity> getAll(Session session) throws Exception {
        Query<FacultyEntity> query = session.createQuery("FROM FacultyEntity", FacultyEntity.class);
        return query.list();
    }
}
