package com.kart.example.myKart.dao.employee;

import java.util.List;

import com.kart.example.myKart.entity.EmployeeEntity;

public interface EmployeeDao {
    List<EmployeeEntity> findAll();
    EmployeeEntity findById(int theId);
    EmployeeEntity save(EmployeeEntity theEmployee);
    void deleteById(int theId);
}
