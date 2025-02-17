package edu.ijse.crs.dao.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.DepartmentDao;
import edu.ijse.crs.entity.DepartmentEntity;

public class DepartmentDaoImpl extends CrudUtil<DepartmentEntity,String,Session> implements DepartmentDao{

    DepartmentDaoImpl(Class<DepartmentEntity> entityClass) {
        super(entityClass);
    }

}
