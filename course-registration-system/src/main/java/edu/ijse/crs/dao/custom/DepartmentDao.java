package edu.ijse.crs.dao.custom;

import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.DepartmentEntity;
import edu.ijse.crs.entity.FacultyEntity;

public interface DepartmentDao extends CrudDao<DepartmentEntity, String, Session> {
    List<DepartmentEntity> getAllWhereFaculty(FacultyEntity facultyEntity, Session session) throws Exception;
}
