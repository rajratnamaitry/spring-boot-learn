package com.kart.example.myKart.dao.student;
// import javax.swing.text.html.parser.Entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kart.example.myKart.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentRepo implements StudentDao {
    // Define entity manager
    private EntityManager entityManager;

    // Set up constructor injection
    @Autowired
    public StudentRepo(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        System.out.println("Student saved: " + theStudent);
        this.entityManager.persist(theStudent);
    }

    @Override
    public Student findById(int theId) {
        return this.entityManager.find(Student.class, theId);
    }
    
    @Override
    public List<Student> getAllStudent() {
        TypedQuery<Student> theQuery = this.entityManager.createQuery("from Student order by firstName", Student.class);
        return theQuery.getResultList();
    }

    @Override
    public Student findByLastName(String theLastName) {
        TypedQuery<Student> theQuery = this.entityManager.createQuery("from Student where lastName=:theData", Student.class);
        theQuery.setParameter("theData", theLastName);
        List<Student> students = theQuery.getResultList();
        if (students.size() == 0) {
            return null;
        }
        return students.get(0);
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        this.entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(int theId) {
        Student student = this.entityManager.find(Student.class, theId);
        this.entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = this.entityManager.createQuery("delete from Student").executeUpdate();
        return numRowsDeleted;
    }
}
