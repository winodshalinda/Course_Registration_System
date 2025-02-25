package edu.ijse.crs.dto;

public class CourseDTO {

    private String courseId;
    private String courseTitle;
    private int enrollmentCapacity;
    private int creditHours;
    private DepartmentDTO departmentDTO;

    public CourseDTO() {
    }

    public CourseDTO(String courseId, String courseTitle, int enrollmentCapacity,
            int creditHours, DepartmentDTO departmentDTO) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.enrollmentCapacity = enrollmentCapacity;
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

    public String toShow() {
        return "Course ID: " + courseId + 
        "\nCourse Title: " + courseTitle +
        "\nEnrollment Capacity: "+ enrollmentCapacity +
        "\nCredit Hours: " + creditHours+"\n";
    }

    @Override
    public String toString() {
        return courseId +" ("+courseTitle+")"+" ("+creditHours+")";
    }
    
    
}
