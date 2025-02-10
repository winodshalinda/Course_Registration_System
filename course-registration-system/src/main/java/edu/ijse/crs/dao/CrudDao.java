package edu.ijse.crs.dao;

import java.util.List;

public interface CrudDao<Entity, ID, Session> extends SuperDao {
    Boolean save(Entity entity, Session session) throws Exception;

    void update(Entity entity, Session session) throws Exception;

    void delete(ID id, Session session) throws Exception;

    Entity search(ID id,Session session) throws Exception;

    List<Entity> getAll(Session session) throws Exception;

}
