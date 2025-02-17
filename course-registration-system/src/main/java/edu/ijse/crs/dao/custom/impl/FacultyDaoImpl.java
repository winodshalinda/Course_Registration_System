package edu.ijse.crs.dao.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.FacultyDao;
import edu.ijse.crs.entity.FacultyEntity;

public class FacultyDaoImpl  extends CrudUtil<FacultyEntity,String,Session>  implements FacultyDao {

    public FacultyDaoImpl(Class<FacultyEntity> entityClass) {
        super(entityClass);
    }
}
