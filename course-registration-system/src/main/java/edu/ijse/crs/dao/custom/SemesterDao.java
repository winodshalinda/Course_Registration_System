package edu.ijse.crs.dao.custom;

import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.FacultyEntity;
import edu.ijse.crs.entity.SemesterEntity;
import edu.ijse.crs.entity.embeddableId.SemesterId;

public interface SemesterDao extends CrudDao<SemesterEntity, String, Session> {
    List<SemesterEntity> getAllWhereFaculty(FacultyEntity facultyEntity, Session session) throws Exception;

    SemesterEntity searchSemester(SemesterId id, Session session);
}
