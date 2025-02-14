package edu.ijse.crs.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import edu.ijse.crs.entity.embeddableId.PrerequisiteId;

@Entity
@Table(name = "prerequisites")
public class PrerequisitesEntity {
    @EmbeddedId
    private PrerequisiteId id;

    @ManyToOne
    @MapsId("course")
    private CourseEntity course;
    @ManyToOne
    @MapsId("prerequisitesCourse")
    private CourseEntity prerequisitesCourse;

    public PrerequisitesEntity() {
    }

    public PrerequisiteId getId() {
        return id;
    }

    public void setId(PrerequisiteId id) {
        this.id = id;
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
    public String toString() {
        return "PrerequisitesEntity [id=" + id + ", course=" + course + ", prerequisitesCourse=" + prerequisitesCourse
                + "]";
    }

}
