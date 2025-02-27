package edu.ijse.crs.dao.custom.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.EnrollmentDao;
import edu.ijse.crs.entity.EnrollmentEntity;
import edu.ijse.crs.entity.StudentEntity;
import edu.ijse.crs.entity.embeddableId.EnrollmentId;
import edu.ijse.crs.entity.embeddableId.SemesterId;

public class EnrollmentDaoImpl extends CrudUtil<EnrollmentEntity, String, Session> implements EnrollmentDao {

    public EnrollmentDaoImpl(Class<EnrollmentEntity> entityClass) {
        super(entityClass);
    }

    @Override
    public List<EnrollmentEntity> getAllWhereStudent(StudentEntity studentEntity, Session session) {

        String studentId = studentEntity.getStudentId();
        Query<EnrollmentEntity> query = session
                .createQuery("FROM EnrollmentEntity WHERE student.studentId = :studentId", EnrollmentEntity.class);
        query.setParameter("studentId", studentId);
        return query.list();

    }

    @Override
    public void deleteEnrollment(EnrollmentId id, Session session) {

        EnrollmentEntity searchEnrollment = searchEnrollment(id, session);
        if (searchEnrollment == null) {
            return;
        }
        session.delete(searchEnrollment);

    }

    @Override
    public EnrollmentEntity searchEnrollment(EnrollmentId id, Session session) {

        return session.get(EnrollmentEntity.class, id);

    }

    public List<Object[]> loadVacancies(SemesterId semesterId, String programId, Session session) {

        Query<Object[]> query = session.createQuery(
                "SELECT (c.enrollmentCapacity - COUNT(e)) AS available, e.course " +
                        "FROM EnrollmentEntity e " +
                        "INNER JOIN e.course c " +
                        "WHERE e.id.semester.embeddedId.year = :year " +
                        " AND e.id.semester.embeddedId.partOfSemester = :partOfSemester "+
                        " AND e.id.semester.embeddedId.faculty.facultyId = :facultyId "+
                        " GROUP BY e.course",
                Object[].class);

        query.setParameter("year", semesterId.getYear());
        query.setParameter("partOfSemester", semesterId.getPartOfSemester());
        query.setParameter("facultyId", semesterId.getFaculty().getFacultyId());

        return query.getResultList();
    }

}
