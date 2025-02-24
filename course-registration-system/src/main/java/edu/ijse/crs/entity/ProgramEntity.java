package edu.ijse.crs.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "program")
public class ProgramEntity {
    @Id
    private String programId;
    @Column(nullable = false)
    private String programTitle;
    @Column(nullable = false)
    private int totalSemester;
    @ManyToOne
    private FacultyEntity faculty;

    @OneToMany(mappedBy = "program")
    private List<ProgramDetailsEntity> programDetails;
    @OneToMany(mappedBy = "program")
    private List<StudentEntity> student;
    
    public ProgramEntity() {
    }

    public ProgramEntity(String programId, String programTitle, int totalSemester, FacultyEntity faculty) {
        this.programId = programId;
        this.programTitle = programTitle;
        this.totalSemester = totalSemester;
        this.faculty = faculty;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public int getTotalSemester() {
        return totalSemester;
    }

    public void setTotalSemester(int totalSemester) {
        this.totalSemester = totalSemester;
    }

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    
}
