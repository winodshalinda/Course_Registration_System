package edu.ijse.crs.dao.custom.impl;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.ProgramSemesterDao;
import edu.ijse.crs.entity.SemesterEntity;

public class ProgramSemesterDaoImpl extends CrudUtil<SemesterEntity, String, Session> implements ProgramSemesterDao {

    public ProgramSemesterDaoImpl(Class<SemesterEntity> entityClass) {
        super(entityClass);
    }

}
