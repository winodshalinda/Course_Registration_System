package edu.ijse.crs.dao.custom.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.DepartmentDao;
import edu.ijse.crs.entity.DepartmentEntity;
import edu.ijse.crs.entity.FacultyEntity;

public class DepartmentDaoImpl extends CrudUtil<DepartmentEntity,String,Session> implements DepartmentDao{

    public DepartmentDaoImpl(Class<DepartmentEntity> entityClass) {
        super(entityClass);
    }

    @Override
    public List<DepartmentEntity> getAllWhereFaculty(FacultyEntity facultyEntity, Session session) throws Exception {
        String facultyId = facultyEntity.getFacultyId();
        Query<DepartmentEntity> query=session.createQuery("FROM DepartmentEntity WHERE faculty.facultyId = :facultyId",DepartmentEntity.class);
        query.setParameter("facultyId",facultyId);
        return query.list();
    }

}
