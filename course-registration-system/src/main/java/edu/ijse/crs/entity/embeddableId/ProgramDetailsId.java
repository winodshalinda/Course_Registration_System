package edu.ijse.crs.entity.embeddableId;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import edu.ijse.crs.entity.CourseEntity;
import edu.ijse.crs.entity.ProgramEntity;

@Embeddable
public class ProgramDetailsId implements Serializable {
    private int semester;
    @ManyToOne
    private CourseEntity course;
    @ManyToOne
    private ProgramEntity program;

    public ProgramDetailsId() {
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + semester;
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((program == null) ? 0 : program.hashCode());
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
        ProgramDetailsId other = (ProgramDetailsId) obj;
        if (semester != other.semester)
            return false;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (program == null) {
            if (other.program != null)
                return false;
        } else if (!program.equals(other.program))
            return false;
        return true;
    }
}
