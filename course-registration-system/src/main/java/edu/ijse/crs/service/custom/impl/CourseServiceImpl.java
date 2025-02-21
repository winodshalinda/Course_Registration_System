package edu.ijse.crs.service.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;

import edu.ijse.crs.dao.DaoFactory;
import edu.ijse.crs.dao.DaoFactory.DaoTypes;
import edu.ijse.crs.dao.custom.CourseDao;
import edu.ijse.crs.dao.custom.PrerequisitesDao;
import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.DepartmentDTO;
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
        } catch (PersistenceException e) {

            session.getTransaction().rollback();
            return "Course ID Already Have";

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

    @Override
    public List<CourseDTO> RemovePrerequisites(String id, List<CourseDTO> prerequisites) throws Exception {
        for (CourseDTO courseDTO : prerequisites) {
            if (courseDTO.getCourseId().equals(id)) {
                prerequisites.remove(courseDTO);
                return prerequisites;
            }
        }
        throw new CustomException("Course Not Found in Prerequisites Course List");
    }

    @Override
    public CourseDTO searchCourse(String id, DepartmentDTO departmentDTO) throws Exception {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();
        CourseEntity search = courseDao.search(id, session);
        session.getTransaction().commit();
        session.close();
        if (search.getDepartment().getDepartmentId().equalsIgnoreCase(departmentDTO.getDepartmentId())) {
            return EntityDTOConversion.toCourseDTO(search);
        } else {
            throw new CustomException("Course Not Allowed This Departmnet");
        }
    }

    @Override
    public List<CourseDTO> getCourseAllPrerequisites(CourseDTO courseDTO) throws Exception {
        Session session = HibernateUtil.getSession();
        List<PrerequisitesEntity> allWhereCourse = prerequisitesDao
                .getAllWhereCourse(EntityDTOConversion.toCourseEntity(courseDTO), session);

        List<CourseDTO> courseDTOs = new ArrayList<>();

        for (PrerequisitesEntity entity : allWhereCourse) {
            courseDTOs.add(EntityDTOConversion.toCourseDTO(entity.getPrerequisitesCourse()));
        }
        return courseDTOs;
    }

    @Override
    public List<CourseDTO> loadTable(DepartmentDTO departmentDTO) throws Exception {
        Session session = HibernateUtil.getSession();

        List<CourseEntity> allWhereDepartment = courseDao.getAllWhereDepartment(departmentDTO.getDepartmentId(),
                session);
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (CourseEntity entity : allWhereDepartment) {
            courseDTOs.add(EntityDTOConversion.toCourseDTO(entity));
        }
        return courseDTOs;
    }

    @Override
    public String updateCourse(CourseDTO courseDTO, List<CourseDTO> prerequisites) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        CourseEntity courseEntity = EntityDTOConversion.toCourseEntity(courseDTO);
        try {

            courseDao.update(courseEntity, session);

            List<PrerequisitesEntity> existingPrerequisites = prerequisitesDao.getAllWhereCourse(courseEntity, session);
            for (PrerequisitesEntity prerequisite : existingPrerequisites) {
                prerequisitesDao.delete(prerequisite, session);
            }

            for (CourseDTO prerequisiteDTO : prerequisites) {
                CourseEntity prerequisiteEntity = EntityDTOConversion.toCourseEntity(prerequisiteDTO);
                PrerequisitesEntity prerequisitesEntity = new PrerequisitesEntity(courseEntity, prerequisiteEntity);
                prerequisitesDao.save(prerequisitesEntity, session);
            }

            session.getTransaction().commit();
            return "Course updated successfully";

        } catch (Exception e) {
            session.getTransaction().rollback();
            return "Course update failed"+e.getMessage();
        }finally {
            session.close();
        }
    }
}
