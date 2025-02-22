package edu.ijse.crs.dto;

import edu.ijse.crs.entity.UserEntity.Role;

public class UserDTO {
    private String userName;
    private String password;
    private Role role;
    private FacultyDTO facultyDTO;
    private StudentDTO studentDTO;

    public UserDTO() {
    }

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserDTO(String userName, String password, Role role, FacultyDTO facultyDTO, StudentDTO studentDTO) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.facultyDTO = facultyDTO;
        this.studentDTO = studentDTO;
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

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }
}
