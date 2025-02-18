package edu.ijse.crs.dao.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.PrerequisitesDao;
import edu.ijse.crs.entity.PrerequisitesEntity;

public class PrerequisitesDaoImpl extends CrudUtil<PrerequisitesEntity, String, Session> implements PrerequisitesDao{

    public PrerequisitesDaoImpl(Class<PrerequisitesEntity> entityClass) {
        super(entityClass);
    }

}
