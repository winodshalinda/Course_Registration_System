package edu.ijse.crs.dao.custom;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.EnrollmentEntity;
public interface EnrollmentDao extends CrudDao<EnrollmentEntity, String, Session>{
    
}
