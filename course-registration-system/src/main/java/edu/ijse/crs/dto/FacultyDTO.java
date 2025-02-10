package edu.ijse.crs.dto;

public class FacultyDTO {
    private String facultyId;
    private String facultyName;
    private String password;
    private String rePassword;
    
    public FacultyDTO(String facultyId, String facultyName, String password, String rePassword) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.password = password;
        this.rePassword = rePassword;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRePassword() {
        return rePassword;
    }
    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
    @Override
    public String toString() {
        return "FacultyDTO [facultyId=" + facultyId + ", facultyName=" + facultyName + "]";
    }

}
