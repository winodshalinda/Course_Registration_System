package edu.ijse.crs.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "faculty")
public class FacultyEntity {
    @Id
    private String facultyId;
    @Column(nullable = false)
    private String facultyName;

    @OneToOne(mappedBy = "faculty")
    private UserEntity userEntity;
    @OneToMany(mappedBy = "faculty")
    private List<ProgramEntity> program;

    public FacultyEntity() {
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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
