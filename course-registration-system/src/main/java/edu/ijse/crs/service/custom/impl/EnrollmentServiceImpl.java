package edu.ijse.crs.service.custom.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import edu.ijse.crs.dao.DaoFactory;
import edu.ijse.crs.dao.DaoFactory.DaoTypes;
import edu.ijse.crs.dao.custom.CourseDao;
import edu.ijse.crs.dao.custom.EnrollmentDao;
import edu.ijse.crs.dao.custom.ProgramDetailsDao;
import edu.ijse.crs.dao.custom.SemesterDao;
import edu.ijse.crs.dto.CourseDTO;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.dto.ProgramDetailsDTO;
import edu.ijse.crs.dto.SemesterDTO;
import edu.ijse.crs.dto.StudentDTO;
import edu.ijse.crs.entity.CourseEntity;
import edu.ijse.crs.entity.EnrollmentEntity;
import edu.ijse.crs.entity.ProgramDetailsEntity;
import edu.ijse.crs.entity.ProgramEntity;
import edu.ijse.crs.entity.SemesterEntity;
import edu.ijse.crs.entity.embeddableId.SemesterId;
import edu.ijse.crs.exception.CustomException;
import edu.ijse.crs.service.custom.EnrollmentService;
import edu.ijse.crs.util.EntityDTOConversion;
import edu.ijse.crs.util.HibernateUtil;

public class EnrollmentServiceImpl implements EnrollmentService {

    EnrollmentDao enrollmentDao = (EnrollmentDao) DaoFactory.getInstance().getDao(DaoTypes.ENROLLMENT);
    SemesterDao semesterDao = (SemesterDao) DaoFactory.getInstance().getDao(DaoTypes.SEMESTER);
    ProgramDetailsDao detailsDao = (ProgramDetailsDao) DaoFactory.getInstance().getDao(DaoTypes.PROGRAMDEATAILS);
    CourseDao courseDao = (CourseDao) DaoFactory.getInstance().getDao(DaoTypes.COURSE);

    @Override
    public String enrollCourse(StudentDTO studentDTO, CourseDTO courseDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enrollCourse'");
    }

    List<EnrollmentEntity> getStudentEnrollmet(StudentDTO studentDTO) {
        Session session = HibernateUtil.getSession();

        List<EnrollmentEntity> allWhereStudent = enrollmentDao
                .getAllWhereStudent(EntityDTOConversion.toStudentEntity(studentDTO), session);

        session.close();

        return allWhereStudent;
    }

    SemesterEntity getCurrentSemester(StudentDTO studentDTO) throws Exception {
        Session session = HibernateUtil.getSession();

        FacultyDTO facultyDTO = studentDTO.getProgram().getFacultyDTO();
        List<SemesterEntity> allWhereFaculty = semesterDao
                .getAllWhereFaculty(EntityDTOConversion.toFacultyEntity(facultyDTO), session);

        for (SemesterEntity semesterEntity : allWhereFaculty) {

            if (studentDTO.getYear() <= semesterEntity.getEmbeddedId().getYear()) {

                if (semesterEntity.getStartDate().isBefore(LocalDate.now())
                        && semesterEntity.getEndDate().isAfter(LocalDate.now())) {

                    session.close();
                    return semesterEntity;

                }
            }
        }
        session.close();
        return null;
    }

    @Override
    public SemesterDTO canEnroll(StudentDTO studentDTO) {
        Session session = HibernateUtil.getSession();

        SemesterEntity semester;
        try {
            semester = getCurrentSemester(studentDTO);
            System.out.println(semester);

            if (semester.getStartDate().plusDays(7).isAfter(LocalDate.now())
                    && semester.getStartDate().isBefore(LocalDate.now())) {

                return EntityDTOConversion.toSemesterDTO(semester);

            } else {

                SemesterId s = new SemesterId();
                String part = semester.getEmbeddedId().getPartOfSemester();
                int year = semester.getEmbeddedId().getYear();

                if (semester.getEmbeddedId().getPartOfSemester().equals("FIRST")) {
                    part = "SECOND";
                } else {
                    year += 1;
                }
                s.setFaculty(semester.getFaculty());
                s.setPartOfSemester(part);
                s.setYear(year);
                SemesterEntity searchSemester = semesterDao.searchSemester(s, session);

                return EntityDTOConversion.toSemesterDTO(searchSemester);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

    }

    @Override
    public List<ProgramDetailsDTO> getStudentProgramCourse(StudentDTO studentDTO) throws Exception {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        ProgramEntity programEntity = EntityDTOConversion.toProgramEntity(studentDTO.getProgram());

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
    public CourseDTO searchCourse(String id, List<ProgramDetailsDTO> detailsDTOs) throws Exception {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();
        CourseEntity search = courseDao.search(id, session);
        session.getTransaction().commit();

        CourseDTO courseDTO = EntityDTOConversion.toCourseDTO(search);

        session.close();

        if (search == null) {
            return null;
        }

        // cheack course is in student program
        boolean isAvailableInStudentProgram = detailsDTOs.stream()
                .anyMatch(programDetailsDTO -> programDetailsDTO.getCourse().getCourseId().equals(courseDTO.getCourseId()));

        if (!isAvailableInStudentProgram) {
            throw new CustomException("Course Not Available For Your Progrm");
        }

        return courseDTO;
    }

}
