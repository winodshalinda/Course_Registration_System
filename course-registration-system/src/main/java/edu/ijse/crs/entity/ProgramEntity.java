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
<<<<<<< HEAD
    private String progarmTitle;
=======
    private String programTitle;
>>>>>>> main
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
<<<<<<< HEAD

    public ProgramEntity() {
    }

=======
    
    public ProgramEntity() {
    }

    public ProgramEntity(String programId, String programTitle, int totalSemester, FacultyEntity faculty) {
        this.programId = programId;
        this.programTitle = programTitle;
        this.totalSemester = totalSemester;
        this.faculty = faculty;
    }

>>>>>>> main
    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

<<<<<<< HEAD
    public String getProgarmTitle() {
        return progarmTitle;
    }

    public void setProgarmTitle(String progarmTitle) {
        this.progarmTitle = progarmTitle;
=======
    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
>>>>>>> main
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

<<<<<<< HEAD
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

=======
    
>>>>>>> main
}
