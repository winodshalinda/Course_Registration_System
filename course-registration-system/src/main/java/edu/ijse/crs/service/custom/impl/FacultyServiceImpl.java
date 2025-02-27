package edu.ijse.crs.service.custom.impl;

import java.util.ArrayList;
import java.util.List;

import edu.ijse.crs.dao.custom.*;
import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.entity.*;
import edu.ijse.crs.entity.embeddableId.EnrollmentId;
import edu.ijse.crs.entity.embeddableId.SemesterId;
import org.hibernate.Session;

import edu.ijse.crs.dao.DaoFactory;
import edu.ijse.crs.dao.DaoFactory.DaoTypes;
import edu.ijse.crs.dto.EnrollmentDTO;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.entity.UserEntity.Role;
import edu.ijse.crs.exception.CustomException;
import edu.ijse.crs.service.custom.FacultyService;
import edu.ijse.crs.util.EntityDTOConversion;
import edu.ijse.crs.util.HibernateUtil;

public class FacultyServiceImpl implements FacultyService {

    FacultyDao facultyDao = (FacultyDao) DaoFactory.getInstance().getDao(DaoTypes.FACULTY);
    UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoTypes.USER);
    SemesterDao semesterDao= (SemesterDao) DaoFactory.getInstance().getDao(DaoTypes.SEMESTER);
    StudentDao studentDao= (StudentDao) DaoFactory.getInstance().getDao(DaoTypes.STUDENT);
    EnrollmentDao enrollmentDao= (EnrollmentDao) DaoFactory.getInstance().getDao(DaoTypes.ENROLLMENT);
    CourseDao courseDao= (CourseDao) DaoFactory.getInstance().getDao(DaoTypes.COURSE);
    DepartmentDao departmentDao= (DepartmentDao) DaoFactory.getInstance().getDao(DaoTypes.DEPARTMENT);


    @Override
    public String saveFaculty(FacultyDTO facultyDTO) throws Exception {
        // need to check both attribute null or isEmpty because if UI library changes
        if (facultyDTO.getFacultyId().isEmpty() || facultyDTO.getFacultyName().isEmpty()
                || facultyDTO.getPassword().isEmpty() || facultyDTO.getRePassword().isEmpty()) {
            return "All fields are required";
        }
        if (facultyDTO.getPassword().equals(facultyDTO.getRePassword())) {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            Boolean isFacultySaved = facultyDao.save(EntityDTOConversion.toFacultyEntity(facultyDTO), session);
            if (isFacultySaved) {
                Boolean isUserSaved = userDao
                        .save(new UserEntity(facultyDTO.getFacultyId(), facultyDTO.getPassword(),
                                Role.FACULTY, EntityDTOConversion.toFacultyEntity(facultyDTO), null), session);
                if (isUserSaved) {
                    session.getTransaction().commit();
                    session.close();
                    return "Saved";
                } else {
                    session.getTransaction().rollback();
                    session.close();
                    return "User Save Failed";
                }
            } else {
                session.getTransaction().rollback();
                session.close();
                return "Faculty Save Failed";
            }
        } else {
            return "Password not match";
        }
    }

    @Override
    public String updateFaculty(FacultyDTO facultyDTO) throws Exception {
        if (facultyDTO.getFacultyId().isEmpty() || facultyDTO.getFacultyName().isEmpty()) {
            return "Faculty ID and Name are required";
        }
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        facultyDao.update(EntityDTOConversion.toFacultyEntity(facultyDTO), session);
        session.getTransaction().commit();
        session.close();
        return "Faculty Updated Successfully";
    }

    @Override
    public String deleteFaculty(String id) throws Exception {
        if (id.isEmpty()) {
            return "Faculty ID is required";
        }
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            FacultyEntity search = facultyDao.search(id, session);
        if (search == null) {
            return "Faculty Not Found";
        } else {
            Boolean isUserFacultyDeleted = userDao.deleteByFacultyId(search, session);
            if (!isUserFacultyDeleted) {
                session.getTransaction().rollback();
                return "Faculty User Data not found";
            }else{
                facultyDao.delete(search, session);
                session.getTransaction().commit();
                return "Faculty Delete Success";
            }
        }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }finally{
            session.close();
        }
    }

    @Override
    public FacultyDTO searchFaculty(String id) throws Exception {
        Session session = HibernateUtil.getSession();
        if (id.isEmpty()) {
            throw new CustomException("Faculty ID cannot be null or empty");
        } else {
            FacultyEntity facultyEntity = facultyDao.search(id, session);
            if (facultyEntity != null) {
                return EntityDTOConversion.toFacultyDTO(facultyEntity);
            } else {
                throw new CustomException("Faculty not found with ID: " + id);
            }
        }
    }

    @Override
    public List<FacultyDTO> getAllFaculties() {
        Session session = HibernateUtil.getSession();

        try {
            List<FacultyEntity> all = facultyDao.getAll(session);
            return EntityDTOConversion.toListFacultyDTOs(all);

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public EnrollmentDTO searchEnrollment(String studentId, SemesterId semesterId, CourseDTO courseDTO) throws Exception {
        Session session=HibernateUtil.getSession();

        SemesterEntity semesterEntity = semesterDao.searchSemester(semesterId,session);

        if (semesterEntity==null){
            session.close();
            throw new CustomException("Semester Not Found");

        }else {
            StudentEntity searchStudent = studentDao.search(studentId, session);

            if(searchStudent==null){
                session.close();
                throw new CustomException("Student Not Found");

            }else {
                EnrollmentId enrollmentId = new EnrollmentId();
                enrollmentId.setSemester(semesterEntity);
                enrollmentId.setStudent(searchStudent);
                enrollmentId.setCourse(EntityDTOConversion.toCourseEntity(courseDTO));

                EnrollmentEntity enrollmentEntity = enrollmentDao.searchEnrollment(enrollmentId, session);

                if (enrollmentEntity==null){
                    session.close();
                    throw new CustomException("Student is not enrolled in the course.");

                }else {
                    session.close();
                    return EntityDTOConversion.toEnrollmentDTO(enrollmentEntity);
                }
            }
        }
    }

    @Override
    public List<CourseDTO> loadCourse(FacultyDTO facultyDTO) throws Exception {
        Session session=HibernateUtil.getSession();

        FacultyEntity facultyEntity = EntityDTOConversion.toFacultyEntity(facultyDTO);

        List<DepartmentEntity> allWhereFaculty = departmentDao.getAllWhereFaculty(facultyEntity,session);

        List<CourseEntity> courseEntities=new ArrayList<>();

        for (DepartmentEntity department : allWhereFaculty) {
            courseEntities.addAll(courseDao.getAllWhereDepartment(department.getDepartmentId(),session));
        }

        List<CourseDTO> courseDTOList=new ArrayList<>();

        for (CourseEntity course : courseEntities) {
            courseDTOList.add(EntityDTOConversion.toCourseDTO(course));
        }
        return courseDTOList;
    }

    @Override
    public String updateEnrollment(EnrollmentDTO enrollmentDTO) {
        Session session=HibernateUtil.getSession();
        session.beginTransaction();

        EnrollmentEntity enrollmentEntity = EntityDTOConversion.toEnrollmentEntity(enrollmentDTO);

        try {
            enrollmentDao.update(enrollmentEntity, session);
            session.getTransaction().commit();
            return "Updated";

        } catch (Exception e) {
            session.getTransaction().rollback();
            return "Error "+e.getCause().getMessage();
            
        }finally{
            session.close();
        }

        

    }
}
