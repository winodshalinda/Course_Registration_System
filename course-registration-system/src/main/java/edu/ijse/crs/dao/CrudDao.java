package edu.ijse.crs.dao;

import java.util.List;

public interface CrudDao<E, ID, S> extends SuperDao {
    Boolean save(E e, S s) throws Exception;

    void update(E e, S s) throws Exception;

    void delete(E e, S s) throws Exception;

    E search(ID id, S s) throws Exception;

    List<E> getAll(S s) throws Exception;
}
