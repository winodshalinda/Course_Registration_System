package edu.ijse.crs.dao.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.CourseDao;
import edu.ijse.crs.entity.CourseEntity;

public class CourseDaoImpl extends CrudUtil<CourseEntity,String,Session> implements CourseDao{

    public CourseDaoImpl(Class<CourseEntity> entityClass) {
        super(entityClass);
    }
}
