package edu.ijse.crs.dto;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProgramDetailsDTO {

    private int semester;
    private CourseDTO course;
    private ProgramDTO program;

    private final Map<Integer, StringProperty> semesterCourses = new HashMap<>();

    public ProgramDetailsDTO() {
    }

    public ProgramDetailsDTO(int semester, CourseDTO course, ProgramDTO program) {
        this.semester = semester;
        this.course = course;
        this.program = program;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public ProgramDTO getProgram() {
        return program;
    }

    public void setProgram(ProgramDTO program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "ProgramDetailsDTO [semester=" + semester + ", course=" + course + ", program=" + program + "]";
    }

    public void setCourseForSemester(int semester, String course) {
        semesterCourses.put(semester, new SimpleStringProperty(course));
    }

    public StringProperty getCourseForSemester(int semester) {
        return semesterCourses.getOrDefault(semester, new SimpleStringProperty(""));
    }

}
