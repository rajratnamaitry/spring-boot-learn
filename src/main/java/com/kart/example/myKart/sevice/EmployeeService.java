package com.kart.example.myKart.sevice;

import java.util.List;

import com.kart.example.myKart.entity.EmployeeEntity;

public interface EmployeeService {

    List<EmployeeEntity> findAll();
    EmployeeEntity findById(int theId);
    EmployeeEntity save(EmployeeEntity theEmployee);
    void deleteById(int theId);
}
