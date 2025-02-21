package edu.ijse.crs.dao.custom;

import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.CourseEntity;
import edu.ijse.crs.entity.PrerequisitesEntity;

public interface PrerequisitesDao extends CrudDao<PrerequisitesEntity, String, Session> {
    List<PrerequisitesEntity> getAllWhereCourse(CourseEntity courseEntity, Session session)
            throws Exception;

}
