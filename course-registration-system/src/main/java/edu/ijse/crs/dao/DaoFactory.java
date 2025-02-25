package edu.ijse.crs.dao;

import edu.ijse.crs.dao.custom.impl.CourseDaoImpl;
import edu.ijse.crs.dao.custom.impl.DepartmentDaoImpl;
import edu.ijse.crs.dao.custom.impl.EnrollmentDaoImpl;
import edu.ijse.crs.dao.custom.impl.FacultyDaoImpl;
import edu.ijse.crs.dao.custom.impl.PrerequisitesDaoImpl;
import edu.ijse.crs.dao.custom.impl.ProgramDaoImpl;
import edu.ijse.crs.dao.custom.impl.ProgramDetailsDaoImpl;
import edu.ijse.crs.dao.custom.impl.SemesterDaoImpl;
import edu.ijse.crs.dao.custom.impl.StudentDaoImpl;
import edu.ijse.crs.dao.custom.impl.UserDaoImpl;
import edu.ijse.crs.entity.CourseEntity;
import edu.ijse.crs.entity.DepartmentEntity;
import edu.ijse.crs.entity.EnrollmentEntity;
import edu.ijse.crs.entity.FacultyEntity;
import edu.ijse.crs.entity.PrerequisitesEntity;
import edu.ijse.crs.entity.ProgramDetailsEntity;
import edu.ijse.crs.entity.ProgramEntity;
import edu.ijse.crs.entity.SemesterEntity;
import edu.ijse.crs.entity.StudentEntity;
import edu.ijse.crs.entity.UserEntity;

public class DaoFactory {
    private static DaoFactory daoFactory;

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDao getDao(DaoTypes daoTypes) {
        switch (daoTypes) {
            case USER:
                return new UserDaoImpl(UserEntity.class);
            case FACULTY:
                return new FacultyDaoImpl(FacultyEntity.class);
            case COURSE:
                return new CourseDaoImpl(CourseEntity.class);
            case DEPARTMENT:
                return new DepartmentDaoImpl(DepartmentEntity.class);
            case ENROLLMENT:
                return new EnrollmentDaoImpl(EnrollmentEntity.class);
            case PREREQUISITES:
                return new PrerequisitesDaoImpl(PrerequisitesEntity.class);
            case PROGRAM:
                return new ProgramDaoImpl(ProgramEntity.class);
            case PROGRAMDEATAILS:
                return new ProgramDetailsDaoImpl(ProgramDetailsEntity.class);
            case SEMESTER:
                return new SemesterDaoImpl(SemesterEntity.class);
            case STUDENT:
                return new StudentDaoImpl(StudentEntity.class);
            default:
                return null;
        }
    }

    public enum DaoTypes {
        USER, FACULTY, COURSE, DEPARTMENT, ENROLLMENT, PREREQUISITES, PROGRAM, PROGRAMDEATAILS, SEMESTER, STUDENT
    }
}
