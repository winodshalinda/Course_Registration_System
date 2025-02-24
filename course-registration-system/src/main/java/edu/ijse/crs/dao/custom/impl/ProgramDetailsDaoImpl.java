package edu.ijse.crs.dao.custom.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.ijse.crs.dao.CrudUtil;
import edu.ijse.crs.dao.custom.ProgramDetailsDao;
import edu.ijse.crs.entity.ProgramDetailsEntity;
import edu.ijse.crs.entity.ProgramEntity;

public class ProgramDetailsDaoImpl extends CrudUtil<ProgramDetailsEntity, String, Session>
        implements ProgramDetailsDao {

    public ProgramDetailsDaoImpl(Class<ProgramDetailsEntity> entityClass) {
        super(entityClass);
    }

    @Override
    public List<ProgramDetailsEntity> getAllWhereProgram(ProgramEntity programEntity, Session session)
            throws Exception {

        String programId = programEntity.getProgramId();
        Query<ProgramDetailsEntity> query = session.createQuery(
                "FROM ProgramDetailsEntity p WHERE p.program.programId = :programId", ProgramDetailsEntity.class);
        query.setParameter("programId", programId);
        return query.list();
    }

}
