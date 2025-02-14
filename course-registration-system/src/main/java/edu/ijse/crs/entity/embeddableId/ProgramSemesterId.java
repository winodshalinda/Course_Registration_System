package edu.ijse.crs.entity.embeddableId;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import edu.ijse.crs.entity.ProgramEntity;

@Embeddable
public class ProgramSemesterId implements Serializable{
    private int semester;
    @ManyToOne
    private ProgramEntity program;
    private int year;

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + semester;
        result = prime * result + ((program == null) ? 0 : program.hashCode());
        result = prime * result + year;
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
        ProgramSemesterId other = (ProgramSemesterId) obj;
        if (semester != other.semester)
            return false;
        if (program == null) {
            if (other.program != null)
                return false;
        } else if (!program.equals(other.program))
            return false;
        if (year != other.year)
            return false;
        return true;
    }

    public ProgramSemesterId() {
    }
}
