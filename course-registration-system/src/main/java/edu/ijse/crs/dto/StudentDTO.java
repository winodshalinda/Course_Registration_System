package edu.ijse.crs.dto;

import java.time.LocalDate;

public class StudentDTO {

    private String studentId;
    private String studentName;
    private LocalDate dob;
    private ProgramDTO program;
    private int year;
    private String email;
    private String address;
    private String password;
    private String rePassword;

    public StudentDTO() {
    }

    public StudentDTO(String studentId, String studentName, LocalDate dob, ProgramDTO program, int year, String email,
            String address, String password, String rePassword) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.dob = dob;
        this.program = program;
        this.year = year;
        this.email = email;
        this.address = address;
        this.password = password;
        this.rePassword = rePassword;
    }

    public StudentDTO(String studentId, String studentName, LocalDate dob, ProgramDTO program, int year, String email,
            String address) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.dob = dob;
        this.program = program;
        this.year = year;
        this.email = email;
        this.address = address;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public ProgramDTO getProgram() {
        return program;
    }

    public void setProgram(ProgramDTO program) {
        this.program = program;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "Student ID: " + studentId 
        + "\nStudent Name: " + studentName 
        +"\nDate Of Brith: " + dob 
        +"\nProgram Of Study: "+ program 
        +"\nYear: " + year 
        +"\nEmail: " + email 
        +"\nAddress:-\n" + address;
    }

}
