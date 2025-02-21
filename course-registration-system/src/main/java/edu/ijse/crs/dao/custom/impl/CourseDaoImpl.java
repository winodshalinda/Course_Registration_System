package edu.ijse.crs.dao.custom.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.CourseDao;
import edu.ijse.crs.entity.CourseEntity;

public class CourseDaoImpl extends CrudUtil<CourseEntity,String,Session> implements CourseDao{

    public CourseDaoImpl(Class<CourseEntity> entityClass) {
        super(entityClass);
    }

    @Override
    public List<CourseEntity> getAllWhereDepartment(String departmentId, Session session)throws Exception {
        Query<CourseEntity> query=session.createQuery("FROM CourseEntity WHERE department.departmentId = :departmentId",CourseEntity.class);
        query.setParameter("departmentId",departmentId);
        return query.list();
    }
}
