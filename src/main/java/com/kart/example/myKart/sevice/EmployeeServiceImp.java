package com.kart.example.myKart.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kart.example.myKart.dao.employee.EmployeeDao;
import com.kart.example.myKart.entity.EmployeeEntity;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeDao employeeDoa;

    @Autowired
    public EmployeeServiceImp(EmployeeDao theEmployeeDAO) {
        this.employeeDoa = theEmployeeDAO;
    }

    @Override
    public List<EmployeeEntity> findAll() {
        return this.employeeDoa.findAll();
    }

    @Override
    public EmployeeEntity findById(int theId) {
        return this.employeeDoa.findById(theId);
    }

    @Override
    @Transactional
    public EmployeeEntity save(EmployeeEntity theEmployee) {
        return this.employeeDoa.save(theEmployee);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        this.employeeDoa.deleteById(theId);
    }

}
