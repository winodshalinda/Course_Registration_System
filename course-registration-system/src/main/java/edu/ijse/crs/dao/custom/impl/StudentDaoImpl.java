package edu.ijse.crs.dao.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.StudentDao;
import edu.ijse.crs.entity.StudentEntity;

public class StudentDaoImpl extends CrudUtil<StudentEntity,String,Session> implements StudentDao{

    public StudentDaoImpl(Class<StudentEntity> entityClass) {
        super(entityClass);
    }

}
