package edu.ijse.crs.dao.custom.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.ProgramDao;
import edu.ijse.crs.entity.FacultyEntity;
import edu.ijse.crs.entity.ProgramEntity;

public class ProgramDaoImpl extends CrudUtil<ProgramEntity, String, Session> implements ProgramDao{

    public ProgramDaoImpl(Class<ProgramEntity> entityClass) {
        super(entityClass);
    }

    @Override
    public List<ProgramEntity> getAllWhereFaculty(FacultyEntity facultyEntity, Session session) throws Exception {
        String facultyId = facultyEntity.getFacultyId();
        Query<ProgramEntity> query=session.createQuery("FROM ProgramEntity p WHERE p.faculty.facultyId = :facultyId",ProgramEntity.class);
        query.setParameter("facultyId",facultyId);
        return query.list();
    }
}
