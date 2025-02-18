package edu.ijse.crs.service.custom.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.DaoFactory;
import edu.ijse.crs.dao.DaoFactory.DaoTypes;
import edu.ijse.crs.dao.custom.ProgramDao;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.entity.ProgramEntity;
import edu.ijse.crs.exception.CustomException;
import edu.ijse.crs.service.custom.ProgramService;
import edu.ijse.crs.util.EntityDTOConversion;
import edu.ijse.crs.util.HibernateUtil;

public class ProgramServiceImpl implements ProgramService {
    ProgramDao programDao = (ProgramDao) DaoFactory.getInstance().getDao(DaoTypes.PROGRAM);

    @Override
    public String saveProgram(ProgramDTO programDTO) throws Exception {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            Boolean save = programDao.save(EntityDTOConversion.toProgramEntity(programDTO), session);
            if (!save) {
                session.getTransaction().rollback();
                return "Program Save Failed";
            } else {
                session.getTransaction().commit();
                return "Program Saved";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Program Save Failed";
        } finally {
            session.close();
        }
    }

    public List<ProgramDTO> loadTable(FacultyDTO facultyDTO) throws Exception {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<ProgramEntity> all = programDao.getAllWhereFaculty(EntityDTOConversion.toFacultyEntity(facultyDTO),
                session);
        session.getTransaction().commit();
        List<ProgramDTO> programDTOs = new ArrayList<>();
        for (ProgramEntity programEntity : all) {
            programDTOs.add(EntityDTOConversion.toProgramDTO(programEntity));
        }
        session.close();
        return programDTOs;
    }

    @Override
    public ProgramDTO searchProgram(String id,String facultyId) throws Exception {
        Session session=HibernateUtil.getSession();
        ProgramEntity search = programDao.search(id, session);
        if(search==null){
            throw new CustomException("Program Not Found");
        }
        ProgramDTO programDTO = EntityDTOConversion.toProgramDTO(search);
        if(!programDTO.getFacultyDTO().getFacultyId().equals(facultyId)){
            throw new CustomException("Program Not Allowed This Faculty");
        }
        session.close();
        return programDTO;
    }

    @Override
    public String deleteProgram(ProgramDTO programDTO)throws Exception{
        Session session=HibernateUtil.getSession();
        session.beginTransaction();
        try {
            programDao.delete(EntityDTOConversion.toProgramEntity(programDTO), session);
            session.getTransaction().commit();
            return ""+programDTO.getProgramId()+" Program Delete Success";
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new CustomException("program DAO Error");
        }finally{
            session.close();
        }
    }
}
