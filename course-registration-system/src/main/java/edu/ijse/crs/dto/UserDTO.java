package edu.ijse.crs.dto;

import edu.ijse.crs.entity.UserEntity.Role;

public class UserDTO {
    private String userName;
    private String password;
    private Role role;
    private String studentOrFacultyId;

    public UserDTO() {
    }

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserDTO(String userName, String password, Role role, String studentOrFacultyId) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.studentOrFacultyId = studentOrFacultyId;
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
        return "UserDTO [userName=" + userName + ", password=" + password + ", role=" + role + ", studentOrFacultyId="
                + studentOrFacultyId + "]";
    }
}
