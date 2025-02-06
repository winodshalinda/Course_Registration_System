package edu.ijse.crs.service;

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
        USER
    }
    public SuperService getService(ServiceTypes serviceTypes) {
        switch (serviceTypes) {
            case USER:
                return new UserServiceImpl();
            default:
                return null;
        }
    }
}
