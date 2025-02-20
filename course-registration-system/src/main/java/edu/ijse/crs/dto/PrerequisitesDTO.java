package edu.ijse.crs.dto;

public class PrerequisitesDTO {
    private CourseDTO courseDTO;
    private CourseDTO prerequisitesDTO;

    public PrerequisitesDTO(CourseDTO courseDTO, CourseDTO prerequisitesDTO) {
        this.courseDTO = courseDTO;
        this.prerequisitesDTO = prerequisitesDTO;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public CourseDTO getPrerequisitesDTO() {
        return prerequisitesDTO;
    }

    public void setPrerequisitesDTO(CourseDTO prerequisitesDTO) {
        this.prerequisitesDTO = prerequisitesDTO;
    }

}
