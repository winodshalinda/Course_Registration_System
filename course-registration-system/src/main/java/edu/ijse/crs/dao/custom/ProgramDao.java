package edu.ijse.crs.dao.custom;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.ProgramEntity;

public interface ProgramDao extends CrudDao<ProgramEntity,String,Session>{

}
