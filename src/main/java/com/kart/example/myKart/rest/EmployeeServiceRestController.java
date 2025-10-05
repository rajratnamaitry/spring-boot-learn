package com.kart.example.myKart.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kart.example.myKart.entity.EmployeeEntity;
import com.kart.example.myKart.sevice.EmployeeService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v2")
public class EmployeeServiceRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeServiceRestController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }
    
    @GetMapping("/employees")
    public List<EmployeeEntity> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public EmployeeEntity getEmployee(@PathVariable int id) {
        EmployeeEntity theEmployee = employeeService.findById(id);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        return theEmployee;
    }

    @PostMapping("/employee")
    public EmployeeEntity addEmployee (@RequestBody EmployeeEntity theEmployee) {
        theEmployee.setId(0);
        EmployeeEntity dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    
    @PutMapping("/employee")
    public EmployeeEntity updateEmployee (@RequestBody EmployeeEntity theEmployee) {
        EmployeeEntity dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }  

}
