package edu.ijse.crs.entity.embeddableId;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import edu.ijse.crs.entity.CourseEntity;
import edu.ijse.crs.entity.StudentEntity;

@Embeddable
public class EnrollmentId implements Serializable {
    @ManyToOne
    private StudentEntity student;
    @ManyToOne
    private CourseEntity course;
    private int enrolledSemester;

    public EnrollmentId() {
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

    public int getEnrolledSemester() {
        return enrolledSemester;
    }

    public void setEnrolledSemester(int enrolledSemester) {
        this.enrolledSemester = enrolledSemester;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((student == null) ? 0 : student.hashCode());
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + enrolledSemester;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EnrollmentId other = (EnrollmentId) obj;
        if (student == null) {
            if (other.student != null)
                return false;
        } else if (!student.equals(other.student))
            return false;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (enrolledSemester != other.enrolledSemester)
            return false;
        return true;
    }
    
}
