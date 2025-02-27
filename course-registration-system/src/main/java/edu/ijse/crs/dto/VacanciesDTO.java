package edu.ijse.crs.dto;

public class VacanciesDTO {
    private CourseDTO courseDTO;
    private Long available;

    public VacanciesDTO(CourseDTO courseDTO, Long available) {
        this.courseDTO = courseDTO;
        this.available = available;
    }

    public CourseDTO getCourseId() {
        return courseDTO;
    }

    public void setCourseId(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "VacanciesDTO [courseId=" + courseDTO + ", available=" + available + "]";
    }
}
