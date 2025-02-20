package edu.ijse.crs.service.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;

import edu.ijse.crs.dao.DaoFactory;
import edu.ijse.crs.dao.DaoFactory.DaoTypes;
import edu.ijse.crs.dao.custom.DepartmentDao;
import edu.ijse.crs.dto.DepartmentDTO;
import edu.ijse.crs.dto.FacultyDTO;
import edu.ijse.crs.entity.DepartmentEntity;
import edu.ijse.crs.exception.CustomException;
import edu.ijse.crs.service.custom.DepartmentService;
import edu.ijse.crs.util.EntityDTOConversion;
import edu.ijse.crs.util.HibernateUtil;

public class DepartmentServiceImpl implements DepartmentService{
    DepartmentDao departmentDao=(DepartmentDao) DaoFactory.getInstance().getDao(DaoTypes.DEPARTMENT);

    @Override
    public String saveDepartment(DepartmentDTO departmentDTO) throws Exception {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            Boolean save = departmentDao.save(EntityDTOConversion.toDepartmentEntity(departmentDTO), session);
            if (!save) {
                session.getTransaction().rollback();
                return "Department Save Failed";
            } else {
                session.getTransaction().commit();
                return "Department Saved";
            }
        }catch(PersistenceException e){
            throw e;
        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Department Save Failed";
        } finally {
            session.close();
        }
    }

    @Override
    public String updateDepartment(DepartmentDTO departmentDTO) throws Exception {
        Session session=HibernateUtil.getSession();
        session.beginTransaction();
        try {
            departmentDao.update(EntityDTOConversion.toDepartmentEntity(departmentDTO), session);
            session.getTransaction().commit();
            return ""+departmentDTO.getDepartmentId()+" Department Update Success";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw new CustomException("Department DAO Error");
        }finally{
            session.close();
        }
    }

    @Override
    public String deleteDepartment(DepartmentDTO departmentDTO) throws Exception {
        Session session=HibernateUtil.getSession();
        session.beginTransaction();
        try {
            departmentDao.delete(EntityDTOConversion.toDepartmentEntity(departmentDTO), session);
            session.getTransaction().commit();
            return ""+departmentDTO.getDepartmentId()+" Department Delete Success";
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new CustomException("Department DAO Error");
        }finally{
            session.close();
        }
    }

    @Override
    public DepartmentDTO searchDepartment(String id, String facultyId) throws Exception {
       Session session=HibernateUtil.getSession();

        DepartmentEntity search = departmentDao.search(id, session);

        if(search==null){
            throw new CustomException("Department Not Found");
        }

        DepartmentDTO departmentDTO = EntityDTOConversion.toDepartmentDTO(search);

        if(!departmentDTO.getFacultyDTO().getFacultyId().equals(facultyId)){
            throw new CustomException("Department Not Allowed This Faculty");
        }
        
        session.close();
        return departmentDTO;
    }

    @Override
    public List<DepartmentDTO> loadTable(FacultyDTO facultyDTO) throws Exception {
         Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<DepartmentEntity> all = departmentDao.getAllWhereFaculty(
            EntityDTOConversion.toFacultyEntity(facultyDTO), session);

        session.getTransaction().commit();

        List<DepartmentDTO> departmentDTOs = new ArrayList<>();
        
        for (DepartmentEntity departmentEntity : all) {
            departmentDTOs.add(EntityDTOConversion.toDepartmentDTO(departmentEntity));
        }

        session.close();
        return departmentDTOs;
    }
}
