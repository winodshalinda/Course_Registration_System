package edu.ijse.crs.dao.custom;

import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.EnrollmentEntity;
import edu.ijse.crs.entity.StudentEntity;
import edu.ijse.crs.entity.embeddableId.EnrollmentId;

public interface EnrollmentDao extends CrudDao<EnrollmentEntity, String, Session> {

    List<EnrollmentEntity> getAllWhereStudent(StudentEntity studentEntity, Session session);

    void deleteEnrollment(EnrollmentId id, Session session);

    EnrollmentEntity searchEnrollment(EnrollmentId id, Session session);
}
