package edu.ijse.crs.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    private String studentId;
    private String studentName;
    private LocalDate dob;
    @ManyToOne
    private ProgramEntity program;
    @Column(nullable = false)
    private int year;
    private String email;
    private String address;

    @OneToOne(mappedBy = "student")
    private UserEntity user;

    public StudentEntity() {
    }

    public StudentEntity(String studentId, String studentName, LocalDate dob, ProgramEntity program, int year,
            String email, String address) {
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

    public ProgramEntity getProgram() {
        return program;
    }

    public void setProgram(ProgramEntity program) {
        this.program = program;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "StudentEntity [studentId=" + studentId + ", studentName=" + studentName + ", dob=" + dob + ", program="
                + program + ", year=" + year + ", email=" + email + ", address=" + address + "]";
    }
    
}
