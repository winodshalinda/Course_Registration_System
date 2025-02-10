package edu.ijse.crs.dao;

import edu.ijse.crs.dao.custom.impl.FacultyDaoImpl;
import edu.ijse.crs.dao.custom.impl.UserDaoImpl;

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
                return new UserDaoImpl();
            case FACULTY:
                return new FacultyDaoImpl();
            default:
                return null;
        }
    }

    public enum DaoTypes {
        USER, FACULTY
    }
}
