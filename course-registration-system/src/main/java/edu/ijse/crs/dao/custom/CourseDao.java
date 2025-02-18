package edu.ijse.crs.dao.custom;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.CourseEntity;

public interface CourseDao extends CrudDao<CourseEntity, String, Session> {

}
