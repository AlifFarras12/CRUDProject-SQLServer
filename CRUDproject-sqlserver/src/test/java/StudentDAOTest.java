package com.juaracoding;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class StudentDAOTest {

    private studentDAO studentDAO;
    private Student testStudent;

    @BeforeClass
    public void setUp() {
        studentDAO = new studentDAO();
    }

    @AfterClass
    public void tearDown() {
        //clean up test data
        if(testStudent != null){
            studentDAO.deleteStudent(testStudent.getId());
        }
    }

    @Test
    public void testAddStudent() throws SQLException {
        Student student = new Student(0, "Alif Farras", "AlifFarras@example.com", 22, "Computer Science", 3.75);
        studentDAO.addStudent(student);

        //verifythe student was added
        List<Student> students = studentDAO.getAllStudent();
        Assert.assertTrue(students.stream().anyMatch(s -> s.getName().equals("Alif Farras")));

        //clean up
        studentDAO.deleteStudent(student.getId());
    }

    @Test
    public void testUpdateStudent() throws SQLException {
        Student student = new Student(1, "Alif Farras", "AlifFarras@example.com", 22, "Computer Science", 3.75);
        studentDAO.addStudent(student);
        student.setName("Jane Doe");
        studentDAO.updateStudent(student);
        List<Student> students = studentDAO.getAllStudent();
        Assert.assertTrue(students.stream().anyMatch(s -> s.getName().equals("Alif Farras")));
    }

    @Test
    public void testDeleteStudent() throws SQLException {
        Student student = new Student(1, "Alif Farras", "AlifFarras@example.com", 22, "Computer Science", 3.75);
        studentDAO.addStudent(student);
        studentDAO.deleteStudent(1);
        List<Student> students = studentDAO.getAllStudent();
        Assert.assertFalse(students.stream().anyMatch(s -> s.getName().equals("Alif Farras")));
    }

    @Test
    public void testSearchStudent() throws SQLException {
        studentDAO.addStudent(new Student(0, "Alif Farras", "AlifFarras@example.com", 22, "Computer Science", 3.75));
        List<Student> students = studentDAO.searchStudentByNameOrMajor("Computer Science");
        Assert.assertTrue(students.size() > 0);
    }

    @Test
    public void testAverageGPA() throws SQLException {
        double avgGPA = studentDAO.getAllAvgStudent();
        Assert.assertTrue(avgGPA > 0);
    }

    @Test
    public void testStudentCount() throws SQLException {
        int count = studentDAO.getStudentCount();
        Assert.assertTrue(count >= 0);
    }
}

