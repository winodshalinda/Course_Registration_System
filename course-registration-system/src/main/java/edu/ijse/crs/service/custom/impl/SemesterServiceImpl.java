package edu.ijse.crs.service.custom.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.DaoFactory;
import edu.ijse.crs.dao.DaoFactory.DaoTypes;
import edu.ijse.crs.dao.custom.SemesterDao;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.dto.SemesterDTO;
import edu.ijse.crs.entity.FacultyEntity;
import edu.ijse.crs.entity.SemesterEntity;
import edu.ijse.crs.entity.embeddableId.SemesterId;
import edu.ijse.crs.service.custom.SemesterService;
import edu.ijse.crs.util.EntityDTOConversion;
import edu.ijse.crs.util.HibernateUtil;

public class SemesterServiceImpl implements SemesterService {

    SemesterDao semesterDao = (SemesterDao) DaoFactory.getInstance().getDao(DaoTypes.SEMESTER);

    @Override
    public List<SemesterDTO> loadTable(FacultyDTO facultyDTO) throws Exception {
        Session session = HibernateUtil.getSession();

        FacultyEntity facultyEntity = EntityDTOConversion.toFacultyEntity(facultyDTO);

        List<SemesterEntity> allWhereFaculty = semesterDao.getAllWhereFaculty(facultyEntity, session);

        List<SemesterDTO> semesterDTOs = new ArrayList<>();

        for (SemesterEntity semesterEntity : allWhereFaculty) {
            SemesterDTO semesterDTO = EntityDTOConversion.toSemesterDTO(semesterEntity);
            semesterDTOs.add(semesterDTO);
        }

        session.close();
        return semesterDTOs;
    }

    @Override
    public String saveSemester(SemesterDTO semesterDTO) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        SemesterEntity semesterEntity = EntityDTOConversion.toSemesterEntity(semesterDTO);

        try {
            semesterDao.save(semesterEntity, session);
            session.getTransaction().commit();
            return "Semester Saved";
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return "Semester save Failed";
        } finally {
            session.close();
        }
    }

    @Override
    public SemesterDTO searchSemester(SemesterDTO semesterDTO) {
        Session session = HibernateUtil.getSession();
        
        SemesterId id = new SemesterId();
        id.setYear(semesterDTO.getYear());
        id.setPartOfSemester(semesterDTO.getPartOfSemester());
        id.setFaculty(EntityDTOConversion.toFacultyEntity(semesterDTO.getFaculty()));

        SemesterEntity searchSemester = semesterDao.searchSemester(id, session);

        return EntityDTOConversion.toSemesterDTO(searchSemester);
    }

}
