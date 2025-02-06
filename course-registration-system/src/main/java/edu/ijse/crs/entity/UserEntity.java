package edu.ijse.crs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    private String userName;
    @Column(nullable=false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String studentOrFacultyId;
    
    public UserEntity() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStudentOrFacultyId() {
        return studentOrFacultyId;
    }

    public void setStudentOrFacultyId(String studentOrFacultyId) {
        this.studentOrFacultyId = studentOrFacultyId;
    }

    @Override
    public String toString() {
        return "UserEntity [userName=" + userName + ", password=" + password + ", role=" + role
                + ", studentOrFacultyId=" + studentOrFacultyId + "]";
    }

   public enum Role {
        ADMIN, STUDENT, FACULTY
    }
}
