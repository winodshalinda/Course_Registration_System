package edu.ijse.crs.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    private String departmentId;
    @Column(nullable = false)
    private String departmentName;
    @ManyToOne
    private FacultyEntity faculty;

    @OneToMany(mappedBy="department")
    private List<CourseEntity> course;

    public DepartmentEntity() {
    }

    public DepartmentEntity(String departmentId, String departmentName, FacultyEntity faculty) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.faculty = faculty;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    public List<CourseEntity> getCourse() {
        return course;
    }

    public void setCourse(List<CourseEntity> course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "DepartmentEntity [departmentId=" + departmentId + ", departmentName=" + departmentName + ", faculty="
                + faculty + ", course=" + course + "]";
    }

}
