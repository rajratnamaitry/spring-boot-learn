package com.kart.example.myKart.dao.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kart.example.myKart.entity.EmployeeEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeDaoJpaImp implements EmployeeDao {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoJpaImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<EmployeeEntity> findAll() {
        TypedQuery<EmployeeEntity> theQuery = entityManager.createQuery("from EmployeeEntity", EmployeeEntity.class);
        List<EmployeeEntity> employees = theQuery.getResultList();
        System.out.println("Employees: " + theQuery);
        return employees;
    }

    @Override
    public EmployeeEntity findById(int theId) {
        return entityManager.find(EmployeeEntity.class, theId);
    }

    @Override
    @Transactional
    public EmployeeEntity save(EmployeeEntity theEmployee) {
        EmployeeEntity dbEmployee = entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        EmployeeEntity theEmployee = entityManager.find(EmployeeEntity.class, theId);
        entityManager.remove(theEmployee);
    }

}
