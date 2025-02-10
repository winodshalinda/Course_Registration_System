package edu.ijse.crs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Faculty")
public class FacultyEntity {
    @Id
    private String facultyId;
    @Column(nullable = false)
    private String facultyName;
    @OneToOne(mappedBy = "faculty")
    private UserEntity userEntity;
    public FacultyEntity() {
    }

    public FacultyEntity(String facultyId, String facultyName) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public String toString() {
        return "FacultyEntity [facultyId=" + facultyId + ", facultyName=" + facultyName + "]";
    }
}
