package edu.ijse.crs.dao.custom;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.DepartmentEntity;

public interface DepartmentDao extends CrudDao<DepartmentEntity, String, Session>{

}
