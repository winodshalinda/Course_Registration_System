package edu.ijse.crs.dao.custom;

import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.CrudDao;
import edu.ijse.crs.entity.ProgramDetailsEntity;
import edu.ijse.crs.entity.ProgramEntity;

public interface ProgramDetailsDao extends CrudDao<ProgramDetailsEntity, String, Session> {
    List<ProgramDetailsEntity> getAllWhereProgram(ProgramEntity programEntity, Session session) throws Exception;

}
