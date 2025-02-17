package edu.ijse.crs.dao.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.ProgramSemesterDao;
import edu.ijse.crs.entity.ProgramSemesterEntity;

public class ProgramSemesterDaoImpl extends CrudUtil<ProgramSemesterEntity, String, Session> implements ProgramSemesterDao {

    public ProgramSemesterDaoImpl(Class<ProgramSemesterEntity> entityClass) {
        super(entityClass);
    }

}
