package edu.ijse.crs.service.custom.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.DaoFactory;
import edu.ijse.crs.dao.DaoFactory.DaoTypes;
import edu.ijse.crs.dao.custom.ProgramDao;
import edu.ijse.crs.dao.custom.StudentDao;
import edu.ijse.crs.dao.custom.UserDao;
import edu.ijse.crs.dto.ProgramDTO;
import edu.ijse.crs.dto.StudentDTO;
import edu.ijse.crs.entity.ProgramEntity;
import edu.ijse.crs.entity.StudentEntity;
import edu.ijse.crs.entity.UserEntity;
import edu.ijse.crs.entity.UserEntity.Role;
import edu.ijse.crs.service.custom.StudentService;
import edu.ijse.crs.util.EntityDTOConversion;
import edu.ijse.crs.util.HibernateUtil;

public class StudentServiceImpl implements StudentService {

    StudentDao studentDao = (StudentDao) DaoFactory.getInstance().getDao(DaoTypes.STUDENT);
    ProgramDao programDao = (ProgramDao) DaoFactory.getInstance().getDao(DaoTypes.PROGRAM);
    UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoTypes.USER);

    @Override
    public String saveStudent(StudentDTO studentDTO) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        StudentEntity studentEntity = EntityDTOConversion.toStudentEntity(studentDTO);
        try {

            Boolean isStudentSaved = studentDao.save(studentEntity, session);

            if (isStudentSaved) {
                UserEntity userEntity = new UserEntity(
                        studentDTO.getStudentId(),
                        studentDTO.getPassword(),
                        Role.STUDENT, null,
                        studentEntity);

                Boolean isUserSaved = userDao.save(userEntity, session);

                if (isUserSaved) {
                    session.getTransaction().commit();
                    return "Student Save Successfully";

                } else {
                    session.getTransaction().rollback();
                    return "User Save Failed";
                }

            } else {

                session.getTransaction().rollback();
                return "Studen Save Failed";
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Student Save Error";
        } finally {
            session.close();
        }

    }

    @Override
    public List<StudentDTO> loadTable() throws Exception {
        Session session = HibernateUtil.getSession();

        List<StudentEntity> all = studentDao.getAll(session);

        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (StudentEntity studentEntity : all) {
            studentDTOs.add(EntityDTOConversion.toStudentDTO(studentEntity));
        }
        return studentDTOs;
    }

    @Override
    public List<ProgramDTO> loadChoiseBox() throws Exception {
        Session session = HibernateUtil.getSession();

        List<ProgramEntity> all = programDao.getAll(session);

        List<ProgramDTO> programDTOs = new ArrayList<>();
        for (ProgramEntity programEntity : all) {
            programDTOs.add(EntityDTOConversion.toProgramDTO(programEntity));
        }

        session.close();
        return programDTOs;
    }

    @Override
    public StudentDTO searchStudent(String id) throws Exception {
        Session session = HibernateUtil.getSession();

        StudentEntity search = studentDao.search(id, session);

        session.close();
        return EntityDTOConversion.toStudentDTO(search);
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        StudentEntity studentEntity = EntityDTOConversion.toStudentEntity(studentDTO);

        try {
            studentDao.update(studentEntity, session);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) throws Exception {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        StudentEntity studentEntity = EntityDTOConversion.toStudentEntity(studentDTO);

        Boolean isStudentUserDelete = userDao.deleteByStudentId(studentEntity, session);

        if (!isStudentUserDelete) {
            session.getTransaction().rollback();
            session.close();
            return false;

        } else {
            try {
                studentDao.delete(studentEntity, session);
                session.getTransaction().commit();
                return true;

            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
                
            } finally {
                session.close();
            }
        }

    }

}
