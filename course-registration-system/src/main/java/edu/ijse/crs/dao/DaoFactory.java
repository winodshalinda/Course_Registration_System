package edu.ijse.crs.dao;

import edu.ijse.crs.dao.custom.impl.CourseDaoImpl;
import edu.ijse.crs.dao.custom.impl.FacultyDaoImpl;
import edu.ijse.crs.dao.custom.impl.UserDaoImpl;
import edu.ijse.crs.entity.CourseEntity;
import edu.ijse.crs.entity.FacultyEntity;
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
            case USER: return new UserDaoImpl(UserEntity.class);
            case FACULTY: return new FacultyDaoImpl(FacultyEntity.class);
            case COURSE: return new CourseDaoImpl(CourseEntity.class);
            case DEPARTMENT: return null; //TODO
            default:
                return null;
        }
    }

    public enum DaoTypes {
        USER, FACULTY, COURSE, DEPARTMENT
    }
}
