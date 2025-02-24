package edu.ijse.crs.service.custom.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.DaoFactory;
import edu.ijse.crs.dao.DaoFactory.DaoTypes;
import edu.ijse.crs.dao.custom.CourseDao;
import edu.ijse.crs.dao.custom.ProgramDetailsDao;
import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.dto.ProgramDetailsDTO;
import edu.ijse.crs.entity.CourseEntity;
import edu.ijse.crs.entity.ProgramDetailsEntity;
import edu.ijse.crs.entity.ProgramEntity;
import edu.ijse.crs.service.custom.ProgramDetailsService;
import edu.ijse.crs.util.EntityDTOConversion;
import edu.ijse.crs.util.HibernateUtil;

public class ProgramDetailsServiceImpl implements ProgramDetailsService {

    ProgramDetailsDao detailsDao = (ProgramDetailsDao) DaoFactory.getInstance().getDao(DaoTypes.PROGRAMDEATAILS);
    CourseDao courseDao=(CourseDao) DaoFactory.getInstance().getDao(DaoTypes.COURSE);

    @Override
    public List<ProgramDetailsDTO> loadTables(ProgramDTO programDTO) throws Exception {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        ProgramEntity programEntity =EntityDTOConversion.toProgramEntity(programDTO);

        List<ProgramDetailsEntity> allWhereProgarm = detailsDao.getAllWhereProgram(programEntity, session);

        List<ProgramDetailsDTO> detailsDTOs = new ArrayList<>();

        for (ProgramDetailsEntity programDetailsEntity : allWhereProgarm) {
            ProgramDetailsDTO programDetailsDTO = EntityDTOConversion.toProgramDetailsDTO(programDetailsEntity);
            detailsDTOs.add(programDetailsDTO);
        }
        session.close();
        return detailsDTOs;
    }

    @Override
    public CourseDTO searchCourse(String text) throws Exception {
        // TODO Auto-generated method stub
        Session session=HibernateUtil.getSession();

        CourseEntity search = courseDao.search(text, session);

        CourseDTO courseDTO = EntityDTOConversion.toCourseDTO(search);

        session.close();
        return courseDTO;
    }

}
