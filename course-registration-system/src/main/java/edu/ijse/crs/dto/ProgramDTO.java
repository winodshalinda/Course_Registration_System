package edu.ijse.crs.dto;

public class ProgramDTO {

    private String programId;
    private String programTitle;
    private int totalSemester;
    private FacultyDTO facultyDTO;

    public ProgramDTO() {
    }

    public ProgramDTO(String programId, String programTitle, int totalSemester, FacultyDTO facultyDTO) {
        this.programId = programId;
        this.programTitle = programTitle;
        this.totalSemester = totalSemester;
        this.facultyDTO = facultyDTO;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public int getTotalSemester() {
        return totalSemester;
    }

    public void setTotalSemester(int totalSemester) {
        this.totalSemester = totalSemester;
    }

    public FacultyDTO getFacultyDTO() {
        return facultyDTO;
    }

    public void setFacultyDTO(FacultyDTO facultyDTO) {
        this.facultyDTO = facultyDTO;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public String toShow() {
        return "Program Id: " + programId + "\nProgram Title: " + programTitle + "\nTotal Semester: " + totalSemester;
    }

    @Override
    public String toString() {
        return programTitle + " (" + programId + ")";
    }

}
