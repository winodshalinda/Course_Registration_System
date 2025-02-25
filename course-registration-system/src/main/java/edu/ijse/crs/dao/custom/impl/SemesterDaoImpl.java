package edu.ijse.crs.dao.custom.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.SemesterDao;
import edu.ijse.crs.entity.FacultyEntity;
import edu.ijse.crs.entity.SemesterEntity;
import edu.ijse.crs.entity.embeddableId.SemesterId;

public class SemesterDaoImpl extends CrudUtil<SemesterEntity, String, Session> implements SemesterDao {

    public SemesterDaoImpl(Class<SemesterEntity> entityClass) {
        super(entityClass);
    }

    @Override
    public List<SemesterEntity> getAllWhereFaculty(FacultyEntity facultyEntity, Session session) throws Exception {

        String facultyId = facultyEntity.getFacultyId();

        Query<SemesterEntity> query = session
                .createQuery("FROM SemesterEntity WHERE faculty.facultyId = :facultyId", SemesterEntity.class);

        query.setParameter("facultyId", facultyId);
        return query.list();
    }

    public SemesterEntity searchSemester(SemesterId id, Session session) {

        SemesterEntity semesterEntity = session.get(SemesterEntity.class, id);

        return semesterEntity;
    }
}
