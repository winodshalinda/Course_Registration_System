package edu.ijse.crs.dto;

import java.time.LocalDate;

public class SemesterDTO {
    
    private int year;
    private String partOfSemester;
    private LocalDate starDate;
    private LocalDate endDate;
    private FacultyDTO faculty;

    

    public SemesterDTO() {
    }

    public SemesterDTO(int year, String partOfSemester, FacultyDTO faculty) {
        this.year = year;
        this.partOfSemester = partOfSemester;
        this.faculty = faculty;
    }

    public SemesterDTO(int year, String partOfSemester, LocalDate starDate, LocalDate endDate,
            FacultyDTO faculty) {
        this.year = year;
        this.partOfSemester = partOfSemester;
        this.starDate = starDate;
        this.endDate = endDate;
        this.faculty = faculty;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPartOfSemester() {
        return partOfSemester;
    }

    public void setPartOfSemester(String partOfSemester) {
        this.partOfSemester = partOfSemester;
    }

    public LocalDate getStarDate() {
        return starDate;
    }

    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public FacultyDTO getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return year+" "+partOfSemester;
    }
    
}
