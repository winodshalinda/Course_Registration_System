package edu.ijse.crs.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import edu.ijse.crs.entity.embeddableId.ProgramDetailsId;

@Entity
@Table(name = "program_details")
public class ProgramDetailsEntity {

    @EmbeddedId
    private ProgramDetailsId id;

    private int semester;

    @ManyToOne
    @MapsId("course")
    private CourseEntity course;

    @ManyToOne
    @MapsId("program")
    private ProgramEntity program;

    public ProgramDetailsEntity() {
    }

    public ProgramDetailsEntity(int semester, CourseEntity course, ProgramEntity program) {
        this.id = new ProgramDetailsId();
        this.id.setCourse(course);
        this.id.setProgram(program);
        this.semester=semester;
    }

    public ProgramDetailsId getId() {
        return id;
    }

    public void setId(ProgramDetailsId id) {
        this.id = id;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public ProgramEntity getProgram() {
        return program;
    }

    public void setProgram(ProgramEntity program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "ProgramDetailsEntity [id=" + id + ", course=" + course + ", program=" + program + "]";
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

}
