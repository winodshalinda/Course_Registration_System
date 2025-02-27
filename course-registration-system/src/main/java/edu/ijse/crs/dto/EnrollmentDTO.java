package edu.ijse.crs.dto;

import edu.ijse.crs.entity.EnrollmentEntity.EnrollmentStatus;

public class EnrollmentDTO {

    private StudentDTO studentDTO;
    private CourseDTO courseDTO;
    private SemesterDTO semesterDTO;
    private int grade;
    private EnrollmentStatus status;

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public SemesterDTO getSemesterDTO() {
        return semesterDTO;
    }

    public void setSemesterDTO(SemesterDTO semesterDTO) {
        this.semesterDTO = semesterDTO;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public EnrollmentDTO(StudentDTO studentDTO, CourseDTO courseDTO, SemesterDTO semesterDTO, int grade,
            EnrollmentStatus status) {
        this.studentDTO = studentDTO;
        this.courseDTO = courseDTO;
        this.semesterDTO = semesterDTO;
        this.grade = grade;
        this.status = status;
    }

    @Override
    public String toString() {
        return "EnrollmentDTO [studentDTO=" + studentDTO + ", courseDTO=" + courseDTO + ", semesterDTO=" + semesterDTO
                + ", grade=" + grade + ", status=" + status + "]";
    }

    public String toShow() {
        return studentDTO.toShow() + "\n\n" +
                "Course= " + courseDTO.toShow() + "\n" +
                "Semester= " + semesterDTO.toString() + "\n" +
                "Grade= " + grade + "\n" +
                "Status= " + status + "\n";
    }
}
