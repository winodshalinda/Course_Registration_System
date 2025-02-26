package edu.ijse.crs.entity.embeddableId;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import edu.ijse.crs.entity.FacultyEntity;

@Embeddable
public class SemesterId implements Serializable {

    private int year;

    private String partOfSemester;

    @ManyToOne
    private FacultyEntity faculty;

    public SemesterId() {
    }

    public String getPartOfSemester() {
        return partOfSemester;
    }

    public void setPartOfSemester(String partOfSemester) {
        this.partOfSemester = partOfSemester;
    }

    public FacultyEntity getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((partOfSemester == null) ? 0 : partOfSemester.hashCode());
        result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
        result = prime * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SemesterId other = (SemesterId) obj;
        if (partOfSemester != other.partOfSemester)
            return false;
        if (faculty == null) {
            if (other.faculty != null)
                return false;
        } else if (!faculty.equals(other.faculty))
            return false;
        if (year != other.year)
            return false;
        return true;
    }

}
