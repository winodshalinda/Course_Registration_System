package edu.ijse.crs.service;

import edu.ijse.crs.service.custom.impl.FacultyServiceImpl;
import edu.ijse.crs.service.custom.impl.ProgramServiceImpl;
import edu.ijse.crs.service.custom.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public enum ServiceTypes {
        USER, FACULTY, STUDENT, PROGRAM
    }

    public SuperService getService(ServiceTypes serviceTypes) {
        switch (serviceTypes) {
            case USER:
                return new UserServiceImpl();
            case FACULTY:
                return new FacultyServiceImpl();
            case PROGRAM:
                return new ProgramServiceImpl();
            // TODO
            default:
                return null;
        }
    }
}
