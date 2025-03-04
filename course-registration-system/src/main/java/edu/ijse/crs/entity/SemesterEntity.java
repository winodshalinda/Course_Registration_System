package edu.ijse.crs.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.ijse.crs.entity.embeddableId.SemesterId;

@Entity
@Table(name = "semester")
public class SemesterEntity {

    @EmbeddedId
    private SemesterId embeddedId;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @MapsId("faculty")
    private FacultyEntity faculty;

    @OneToMany(mappedBy = "semester")
    private List<EnrollmentEntity> enrollment;

    public SemesterEntity() {
    }

    public SemesterId getEmbeddedId() {
        return embeddedId;
    }

    public void setEmbeddedId(SemesterId embeddedId) {
        this.embeddedId = embeddedId;
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

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    public SemesterEntity(int year, LocalDate startDate, LocalDate endDate, String partOfSemester,
            FacultyEntity faculty) {
                
        this.embeddedId = new SemesterId();
        this.embeddedId.setYear(year);
        this.embeddedId.setPartOfSemester(partOfSemester);
        this.embeddedId.setFaculty(faculty);

        this.startDate = startDate;
        this.endDate = endDate;
        this.faculty = faculty;
    }
}
