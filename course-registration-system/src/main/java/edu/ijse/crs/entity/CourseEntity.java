package edu.ijse.crs.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @Column(length = 10)
    private String courseId;
    private String courseTitle;
    private int enrollmentCapacity;
    private int creditHours;
    @ManyToOne
    private DepartmentEntity department;

    @OneToMany(mappedBy = "course")
    private List<PrerequisitesEntity> prerequisites;
    @OneToMany(mappedBy = "course")
    private List<ProgramDetailsEntity> programDetails;
    @OneToMany(mappedBy = "course")
    private List<EnrollmentEntity> enrollment;

    public CourseEntity() {
    }

    public CourseEntity(String courseId, String courseTitle, int enrollmentCapacity, int creditHours,
            DepartmentEntity department) {

        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.enrollmentCapacity = enrollmentCapacity;
        this.creditHours = creditHours;
        this.department = department;
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

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public List<ProgramDetailsEntity> getProgramDetails() {
        return programDetails;
    }

    public void setProgramDetails(List<ProgramDetailsEntity> programDetails) {
        this.programDetails = programDetails;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public List<PrerequisitesEntity> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<PrerequisitesEntity> prerequisites) {
        this.prerequisites = prerequisites;
    }

}
