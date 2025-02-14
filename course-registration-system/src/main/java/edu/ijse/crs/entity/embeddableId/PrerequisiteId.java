package edu.ijse.crs.entity.embeddableId;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import edu.ijse.crs.entity.CourseEntity;

@Embeddable
public class PrerequisiteId implements Serializable {
    @ManyToOne
    private CourseEntity course;
    @ManyToOne
    private CourseEntity prerequisitesCourse;

    public PrerequisiteId() {
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public CourseEntity getPrerequisitesCourse() {
        return prerequisitesCourse;
    }

    public void setPrerequisitesCourse(CourseEntity prerequisitesCourse) {
        this.prerequisitesCourse = prerequisitesCourse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((prerequisitesCourse == null) ? 0 : prerequisitesCourse.hashCode());
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
        PrerequisiteId other = (PrerequisiteId) obj;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (prerequisitesCourse == null) {
            if (other.prerequisitesCourse != null)
                return false;
        } else if (!prerequisitesCourse.equals(other.prerequisitesCourse))
            return false;
        return true;
    }
    
}
