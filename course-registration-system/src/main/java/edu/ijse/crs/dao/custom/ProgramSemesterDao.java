package edu.ijse.crs.dao.custom;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.SemesterEntity;

public interface ProgramSemesterDao extends CrudDao<SemesterEntity, String, Session>{

}
