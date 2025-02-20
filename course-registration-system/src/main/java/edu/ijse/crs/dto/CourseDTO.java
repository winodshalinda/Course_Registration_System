package edu.ijse.crs.dto;

public class CourseDTO {

    private String courseId;
    private String courseTitle;
    private int enrollmentCapacity;
    private int availableEnrollment;
    private int creditHours;
    private DepartmentDTO departmentDTO;

    public CourseDTO() {
    }

    public CourseDTO(String courseId, String courseTitle, int enrollmentCapacity, int availableEnrollment,
            int creditHours, DepartmentDTO departmentDTO) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.enrollmentCapacity = enrollmentCapacity;
        this.availableEnrollment = availableEnrollment;
        this.creditHours = creditHours;
        this.departmentDTO = departmentDTO;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getEnrollmentCapacity() {
        return enrollmentCapacity;
    }

    public void setEnrollmentCapacity(int enrollmentCapacity) {
        this.enrollmentCapacity = enrollmentCapacity;
    }

    public int getAvailableEnrollment() {
        return availableEnrollment;
    }

    public void setAvailableEnrollment(int availableEnrollment) {
        this.availableEnrollment = availableEnrollment;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }
}
