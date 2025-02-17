package edu.ijse.crs.dao.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.ProgramDao;
import edu.ijse.crs.entity.ProgramEntity;

public class ProgramDaoImpl extends CrudUtil<ProgramEntity, String, Session> implements ProgramDao{

    public ProgramDaoImpl(Class<ProgramEntity> entityClass) {
        super(entityClass);
    }

}
