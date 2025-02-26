package edu.ijse.crs.dao.custom.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.EnrollmentDao;
import edu.ijse.crs.entity.EnrollmentEntity;
import edu.ijse.crs.entity.StudentEntity;

public class EnrollmentDaoImpl extends CrudUtil<EnrollmentEntity,String,Session> implements EnrollmentDao{

    public EnrollmentDaoImpl(Class<EnrollmentEntity> entityClass) {
        super(entityClass);
    }

    @Override
    public List<EnrollmentEntity> getAllWhereStudent(StudentEntity studentEntity, Session session) {

        String studentId = studentEntity.getStudentId();
        Query<EnrollmentEntity> query=session.createQuery("FROM EnrollmentEntity WHERE student.studentId = :studentId",EnrollmentEntity.class);
        query.setParameter("studentId",studentId);
        return query.list();

    }

}
