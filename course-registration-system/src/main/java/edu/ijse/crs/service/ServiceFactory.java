package edu.ijse.crs.service;

import edu.ijse.crs.service.custom.impl.CourseServiceImpl;
import edu.ijse.crs.service.custom.impl.DepartmentServiceImpl;
import edu.ijse.crs.service.custom.impl.EnrollmentServiceImpl;
import edu.ijse.crs.service.custom.impl.FacultyServiceImpl;
import edu.ijse.crs.service.custom.impl.ProgramDetailsServiceImpl;
import edu.ijse.crs.service.custom.impl.ProgramServiceImpl;
import edu.ijse.crs.service.custom.impl.SemesterServiceImpl;
import edu.ijse.crs.service.custom.impl.StudentServiceImpl;
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
        USER, FACULTY, STUDENT, PROGRAM, DEPARTMENT, COURSE, PROGRAMDETAILS, SEMESTER, ENROLLMENT
    }

    public SuperService getService(ServiceTypes serviceTypes) {
        switch (serviceTypes) {
            case USER:
                return new UserServiceImpl();
            case FACULTY:
                return new FacultyServiceImpl();
            case PROGRAM:
                return new ProgramServiceImpl();
            case DEPARTMENT:
                return new DepartmentServiceImpl();
            case COURSE:
                return new CourseServiceImpl();
            case STUDENT:
                return new StudentServiceImpl();
            case PROGRAMDETAILS:
                return new ProgramDetailsServiceImpl();
            case SEMESTER:
                return new SemesterServiceImpl();
            case ENROLLMENT:
                return new EnrollmentServiceImpl();
            default:
                return null;
        }
    }
}
