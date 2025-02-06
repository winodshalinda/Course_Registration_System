package edu.ijse.crs.dao;

import java.util.List;

public interface CrudDao<Entity, ID> extends SuperDao {
    void save(Entity entity) throws Exception;

    void update(Entity entity) throws Exception;

    void delete(ID id) throws Exception;

    Entity search(ID id) throws Exception;

    List<Entity> getAll() throws Exception;

}
