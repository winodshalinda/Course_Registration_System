package edu.ijse.crs.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    private String courseId;
    private String courseTitle;
    private int enrollmentCapacity;
    private int avelableEnrollment;
    private int credit_hours;
    @ManyToOne
    private DepartmentEntity department;

    @OneToMany(mappedBy = "course")
    private List<PrerequisitesEntity> prerequisites;
    @OneToMany(mappedBy = "course")
    private List<ProgramDetailsEntity> programDetails;

    public CourseEntity() {
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

    public int getCredit_hours() {
        return credit_hours;
    }

    public void setCredit_hours(int credit_hours) {
        this.credit_hours = credit_hours;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public List<PrerequisitesEntity> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<PrerequisitesEntity> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<ProgramDetailsEntity> getProgramDetails() {
        return programDetails;
    }

    public void setProgramDetails(List<ProgramDetailsEntity> programDetails) {
        this.programDetails = programDetails;
    }

    @Override
    public String toString() {
        return "CourseEntity [courseId=" + courseId + ", courseTitle=" + courseTitle + ", enrollmentCapacity="
                + enrollmentCapacity + ", credit_hours=" + credit_hours + ", department=" + department
                + ", prerequisites=" + prerequisites + ", programDetails=" + programDetails + "]";
    }

    public int getAvelableEnrollment() {
        return avelableEnrollment;
    }

    public void setAvelableEnrollment(int avelableEnrollment) {
        this.avelableEnrollment = avelableEnrollment;
    }

}
