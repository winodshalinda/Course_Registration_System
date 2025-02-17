package edu.ijse.crs.dao.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.EnrollmentDao;
import edu.ijse.crs.entity.EnrollmentEntity;

public class EnrollmentDaoImpl extends CrudUtil<EnrollmentEntity,String,Session> implements EnrollmentDao{

    public EnrollmentDaoImpl(Class<EnrollmentEntity> entityClass) {
        super(entityClass);
    }

}
