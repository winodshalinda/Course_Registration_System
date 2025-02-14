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
    private String progarmTitle;
    @Column(nullable = false)
    private int totalSemester;
    @ManyToOne
    private FacultyEntity faculty;

    @OneToMany(mappedBy = "program")
    private List<ProgramDetailsEntity> programDetails;
    @OneToMany(mappedBy = "program")
    private List<StudentEntity> student;
    @OneToMany(mappedBy = "program")
    private List<ProgramSemesterEntity> semester;

    public ProgramEntity() {
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgarmTitle() {
        return progarmTitle;
    }

    public void setProgarmTitle(String progarmTitle) {
        this.progarmTitle = progarmTitle;
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

    public List<ProgramDetailsEntity> getProgramDetails() {
        return programDetails;
    }

    public void setProgramDetails(List<ProgramDetailsEntity> programDetails) {
        this.programDetails = programDetails;
    }

    public List<StudentEntity> getStudent() {
        return student;
    }

    public void setStudent(List<StudentEntity> student) {
        this.student = student;
    }

    public List<ProgramSemesterEntity> getSemester() {
        return semester;
    }

    public void setSemester(List<ProgramSemesterEntity> semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "ProgramEntity [programId=" + programId + ", progarmTitle=" + progarmTitle + ", totalSemester="
                + totalSemester + ", faculty=" + faculty + ", programDetails=" + programDetails + ", student=" + student
                + ", semester=" + semester + "]";
    }

}
