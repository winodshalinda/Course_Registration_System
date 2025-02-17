package edu.ijse.crs.dao.custom;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.FacultyEntity;
import edu.ijse.crs.entity.StudentEntity;
import edu.ijse.crs.entity.UserEntity;

public interface UserDao extends CrudDao<UserEntity, String, Session> {
    Boolean deleteByFacultyId(FacultyEntity entity, Session session) throws Exception;
    Boolean deleteByStudentId(StudentEntity entity, Session session) throws Exception;
}
