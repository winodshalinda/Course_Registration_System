package edu.ijse.crs.dao.custom;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.ProgramDetailsEntity;

public interface ProgramDetailsDao  extends CrudDao<ProgramDetailsEntity, String, Session> {

}
