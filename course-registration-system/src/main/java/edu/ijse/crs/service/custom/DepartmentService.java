package edu.ijse.crs.service.custom;

import java.util.List;

import edu.ijse.crs.dto.DepartmentDTO;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.service.SuperService;

public interface DepartmentService extends SuperService {
    String saveDepartment(DepartmentDTO departmentDTO) throws Exception;
    String updateDepartment(DepartmentDTO departmentDTO) throws Exception;
    String deleteDepartment(DepartmentDTO departmentDTO) throws Exception;
    DepartmentDTO searchDepartment(String id,String facultyId) throws Exception;
    List<DepartmentDTO> loadTable(FacultyDTO facultyDTO) throws Exception;
}
