package edu.ijse.crs.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import edu.ijse.crs.entity.embeddableId.EnrollmentId;
@Entity
@Table(name = "enrollment")
public class EnrollmentEntity {
    @EmbeddedId
    private EnrollmentId id;
    @ManyToOne
    @MapsId("student")
    private StudentEntity student;
    @ManyToOne
    @MapsId("course")
    private CourseEntity course;
    private int grade;
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    public EnrollmentEntity() {
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
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

    @Override
    public String toString() {
        return "EnrollmentEntity [student=" + student + ", course=" + course + ", grade=" + grade + ", status=" + status + "]";
    }

    public enum EnrollmentStatus {
        COMPLETED, ENROLLED
    }
}
