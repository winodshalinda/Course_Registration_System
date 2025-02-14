package edu.ijse.crs.entity;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import edu.ijse.crs.entity.embeddableId.ProgramSemesterId;

@Entity
@Table(name = "program_semester")
public class ProgramSemesterEntity {
    @EmbeddedId
    private ProgramSemesterId embeddedId;

    @ManyToOne
    @MapsId("program")
    private ProgramEntity program;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProgramSemesterEntity() {
    }

    public ProgramSemesterId getEmbeddedId() {
        return embeddedId;
    }

    public void setEmbeddedId(ProgramSemesterId embeddedId) {
        this.embeddedId = embeddedId;
    }

    public ProgramEntity getProgram() {
        return program;
    }

    public void setProgram(ProgramEntity program) {
        this.program = program;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ProgramSemesterEntity [embeddedId=" + embeddedId + ", program=" + program + ", startDate=" + startDate
                + ", endDate=" + endDate + "]";
    }

}
