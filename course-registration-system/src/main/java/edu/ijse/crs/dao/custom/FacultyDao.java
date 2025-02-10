package edu.ijse.crs.dao.custom;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.FacultyEntity;

public interface FacultyDao extends CrudDao<FacultyEntity, String, Session>{
    
}
