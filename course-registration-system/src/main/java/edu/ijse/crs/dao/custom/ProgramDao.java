package edu.ijse.crs.dao.custom;

import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.FacultyEntity;
import edu.ijse.crs.entity.ProgramEntity;

public interface ProgramDao extends CrudDao<ProgramEntity,String,Session>{
    List<ProgramEntity> getAllWhereFaculty(FacultyEntity facultyEntity, Session session) throws Exception;
}
