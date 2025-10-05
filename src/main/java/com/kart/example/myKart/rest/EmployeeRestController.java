package com.kart.example.myKart.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kart.example.myKart.dao.employee.EmployeeDao;
import com.kart.example.myKart.entity.EmployeeEntity;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {

    private EmployeeDao employeeDao;

    public EmployeeRestController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    @GetMapping("/check")
    public String check() {
        return "API is working";
    }
    
    @GetMapping("/employees")
    public List<EmployeeEntity> findAll() {
        return employeeDao.findAll();
    }
    
}
