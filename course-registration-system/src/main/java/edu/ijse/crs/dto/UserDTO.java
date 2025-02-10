package edu.ijse.crs.dto;

import edu.ijse.crs.entity.UserEntity.Role;

public class UserDTO {
    private String userName;
    private String password;
    private Role role;
    private FacultyDTO facultyDTO;

    public UserDTO() {
    }

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserDTO(String userName, String password, Role role, FacultyDTO facultyDTO) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.facultyDTO = facultyDTO;
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

    public FacultyDTO getFacultyDTO() {
        return facultyDTO;
    }

    public void setFacultyDTO(FacultyDTO facultyDTO) {
        this.facultyDTO = facultyDTO;
    }

    @Override
    public String toString() {
        return "UserDTO [userName=" + userName + ", role=" + role + ", facultyDTO=" + facultyDTO + "]";
    }
}
