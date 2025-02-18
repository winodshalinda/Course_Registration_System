package edu.ijse.crs.dao.custom;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.StudentEntity;

public interface StudentDao extends CrudDao<StudentEntity,String,Session>{

}
