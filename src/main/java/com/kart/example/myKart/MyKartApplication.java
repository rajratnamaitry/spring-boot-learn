package com.kart.example.myKart;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kart.example.myKart.dao.StudentDao;
import com.kart.example.myKart.entity.Student;

@SpringBootApplication
public class MyKartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyKartApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(StudentDao studentDAO) {
        return runner -> {
            System.out.println("Command runner");
            // int theId = this.createStudent(studentDAO);
            // this.readStudent(studentDAO, theId);
            // this.readAllStudents(studentDAO);
            // this.getStudentByLastName(studentDAO, "Duck");
            // this.updateStudent(studentDAO, 2);
            // this.deleteStudent(studentDAO, 3);
            // this.deleteAllStudents(studentDAO);
            this.createMultipleStudents(studentDAO);
        };
    };
    private void createMultipleStudents(StudentDao studentDAO) {
        System.out.println("Creating 3 student object...");
        Student tempStudent1 = new Student("John", "Doe", "john@doe.com");
        studentDAO.save(tempStudent1);
        Student tempStudent2 = new Student("Mickey", "Mouse", "mickey@mouse.com");
        studentDAO.save(tempStudent2);
        Student tempStudent3 = new Student("Daffy", "Duck", "daffy@duck.com");
        studentDAO.save(tempStudent3);
        this.readAllStudents(studentDAO);
    }
    private void deleteAllStudents(StudentDao studentDAO) {
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted row count: " + numRowsDeleted);
    }
    private void deleteStudent(StudentDao studentDAO, int i) {
        studentDAO.delete(i);
        System.out.println("Deleted student id: " + i);
    }
    private void updateStudent(StudentDao studentDAO, int i) {
        Student student = studentDAO.findById(i);
        System.out.println("Student found: " + student);
        student.setFirstName("Micky");
        student.setLastName("mouse");
        student.setEmail(student.getFirstName().toLowerCase() + "@" + student.getLastName().toLowerCase() + ".com");
        studentDAO.update(student);
        System.out.println("Updated student: " + student);
    }

    private void getStudentByLastName(StudentDao studentDAO, String theLastName) {
        Student student = studentDAO.findByLastName(theLastName);
        System.out.println("Student found: " + student);
    }

    private void readAllStudents(StudentDao studentDAO) {
        List<Student> students = studentDAO.getAllStudent();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void readStudent(StudentDao studentDAO, int theId) {
		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);
		// display student
		System.out.println("Found the student: " + myStudent);
	}
    private int createStudent(StudentDao studentDAO) {
        Student tempStudent = new Student("John", "Doe", "john@doe.com");
        studentDAO.save(tempStudent);
        int theId= tempStudent.getId();
        System.out.println("Saved student id: " + theId);
        return theId;
    }
}
