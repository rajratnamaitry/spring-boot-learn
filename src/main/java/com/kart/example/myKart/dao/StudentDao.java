package com.kart.example.myKart.dao;
import java.util.List;

import com.kart.example.myKart.entity.Student;

public interface StudentDao {
    void save(Student theStudent);
    void update (Student theStudent);
    void delete(int theId);
    int deleteAll();
    Student findById(int theId);
    Student findByLastName(String theLastName);
    List<Student> getAllStudent();
}
