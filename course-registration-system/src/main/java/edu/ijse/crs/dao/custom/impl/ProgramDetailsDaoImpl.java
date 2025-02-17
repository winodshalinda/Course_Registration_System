package edu.ijse.crs.dao.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.ProgramDetailsDao;
import edu.ijse.crs.entity.ProgramDetailsEntity;

public class ProgramDetailsDaoImpl extends CrudUtil<ProgramDetailsEntity, String, Session> implements ProgramDetailsDao{

    public ProgramDetailsDaoImpl(Class<ProgramDetailsEntity> entityClass) {
        super(entityClass);
    }

}
