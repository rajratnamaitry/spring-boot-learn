package com.kart.example.myKart.rest;

import org.springframework.web.bind.annotation.RestController;

import com.kart.example.myKart.entity.StudentPojo;

import jakarta.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class StudentRestController {

    List<StudentPojo> theStudents;

    @PostConstruct
    public void loadData() {
        theStudents = List.of(
            new StudentPojo("Poornima", "Patel"),
            new StudentPojo("Mario", "Rossi"),
            new StudentPojo("Mary", "Smith")
        );
    }

    @GetMapping("/students")
    public List<StudentPojo> getStudents() {
        return theStudents;
    }
    
    @GetMapping("/student/{studentId}")
    public StudentPojo getStudent(@PathVariable int studentId) {
        // check the studentId against the list size
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

}
