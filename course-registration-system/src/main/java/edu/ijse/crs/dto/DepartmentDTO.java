package edu.ijse.crs.dto;

public class DepartmentDTO {
    private String departmentId;
    private String departmentName;
    private FacultyDTO facultyDTO;

    public DepartmentDTO() {
    }
    
    public DepartmentDTO(String departmentId, String departmentName, FacultyDTO facultyDTO) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.facultyDTO = facultyDTO;
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

    public FacultyDTO getFacultyDTO() {
        return facultyDTO;
    }

    public void setFacultyDTO(FacultyDTO facultyDTO) {
        this.facultyDTO = facultyDTO;
    }

    @Override
    public String toString() {
        return "Department ID:\t" + departmentId + "\nDepartment Name:\t" + departmentName;
    }

}
