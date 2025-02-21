package edu.ijse.crs.dao.custom.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.PrerequisitesDao;
import edu.ijse.crs.entity.CourseEntity;
import edu.ijse.crs.entity.PrerequisitesEntity;

public class PrerequisitesDaoImpl extends CrudUtil<PrerequisitesEntity, String, Session> implements PrerequisitesDao{

    public PrerequisitesDaoImpl(Class<PrerequisitesEntity> entityClass) {
        super(entityClass);
    }

    @Override
    public List<PrerequisitesEntity> getAllWhereCourse(CourseEntity courseEntity, Session session) throws Exception {
        String courseId = courseEntity.getCourseId();

        Query<PrerequisitesEntity> query=session.createQuery("FROM PrerequisitesEntity WHERE course.courseId = :courseId",PrerequisitesEntity.class);
        query.setParameter("courseId",courseId);
        
        return query.list();
    }

}
