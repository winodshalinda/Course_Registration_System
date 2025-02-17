package edu.ijse.crs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public abstract class CrudUtil<T, ID, S> implements CrudDao<T, ID, Session> {
    private final Class<T> entityClass;

    public CrudUtil(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Boolean save(T entity, Session session) throws Exception {
        Object save = session.save(entity);
        return save != null;
    }

    @Override
    public void update(T entity, Session session) throws Exception {
        session.update(entity);
    }

    @Override
    public T search(ID id, Session session) throws Exception {
        return session.get(entityClass, id);
    }

    @Override
    public void delete(T entity, Session session) throws Exception {
        session.delete(entity);
    }

    @Override
    public List<T> getAll(Session session) throws Exception {
        Query<T> query = session.createQuery("FROM " + entityClass.getSimpleName(), entityClass);
        return query.list();
    }

}
