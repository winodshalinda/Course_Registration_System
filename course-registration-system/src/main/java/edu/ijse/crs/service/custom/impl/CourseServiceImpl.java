package edu.ijse.crs.service.custom.impl;

import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.DaoFactory;
import edu.ijse.crs.dao.DaoFactory.DaoTypes;
import edu.ijse.crs.dao.custom.CourseDao;
import edu.ijse.crs.dao.custom.PrerequisitesDao;
import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.entity.CourseEntity;
import edu.ijse.crs.entity.PrerequisitesEntity;
import edu.ijse.crs.exception.CustomException;
import edu.ijse.crs.service.custom.CourseService;
import edu.ijse.crs.util.EntityDTOConversion;
import edu.ijse.crs.util.HibernateUtil;

public class CourseServiceImpl implements CourseService {
    CourseDao courseDao = (CourseDao) DaoFactory.getInstance().getDao(DaoTypes.COURSE);
    PrerequisitesDao prerequisitesDao = (PrerequisitesDao) DaoFactory.getInstance().getDao(DaoTypes.PREREQUISITES);

    @Override
    public String saveCourse(CourseDTO courseDTO, List<CourseDTO> prerequisites) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            CourseEntity courseEntity = EntityDTOConversion.toCourseEntity(courseDTO);
            Boolean save = courseDao.save(courseEntity, session);

            if (!save) {
                session.getTransaction().rollback();
                return "Course save Failed";
            } else {

                for (CourseDTO prerequisitesCourseDTO : prerequisites) {
                    
                    PrerequisitesEntity prerequisitesEntity = new PrerequisitesEntity(
                            courseEntity,
                            EntityDTOConversion.toCourseEntity(prerequisitesCourseDTO));

                    try {

                        prerequisitesDao.save(prerequisitesEntity, session);

                    } catch (Exception e) {
                        e.printStackTrace();
                        session.getTransaction().rollback();
                        return "Prerequisites Courses Save Failed";
                    }
                }
                session.getTransaction().commit();
                return "Course Saved";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Course save Failed";

        } finally {
            session.close();
        }
    }

    @Override
    public List<CourseDTO> AddPrerequisites(String id, List<CourseDTO> prerequisites) throws Exception {
        Session session = HibernateUtil.getSession();

        CourseEntity search;

        try {
            session.beginTransaction();
            search = courseDao.search(id, session);
            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            session.close();
            throw e;
        }

        if (search == null) {
            throw new CustomException("Course Not Found");
        }

        for (CourseDTO courseDTO : prerequisites) {
            if (courseDTO.getCourseId().equals(search.getCourseId())) {
                throw new CustomException("Course Already Added");
            }
        }

        CourseDTO searchDTO = EntityDTOConversion.toCourseDTO(search);
        prerequisites.add(searchDTO);

        return prerequisites;
    }
}
