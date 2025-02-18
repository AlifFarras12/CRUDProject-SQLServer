package com.juaracoding;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentCLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final studentDAO studentDAO = new studentDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Students");
            System.out.println("6. View Average GPA");
            System.out.println("7. View Student Count");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        searchStudents();
                        break;
                    case 6:
                        viewAverageGpa();
                        break;
                    case 7:
                        viewStudentCount();
                        break;
                    case 8:
                        System.exit(0);
                    default:
                        System.out.println("Invalid option");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addStudent() throws SQLException {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter major: ");
        String major = scanner.nextLine();
        System.out.print("Enter GPA: ");
        double gpa = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Student student = new Student(0, name, email, age, major, gpa);
        studentDAO.addStudent(student);
        System.out.println("Student added successfully");
    }

    private static void viewAllStudents() throws SQLException {
        List<Student> students = studentDAO.getAllStudent();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void updateStudent() throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new major: ");
        String major = scanner.nextLine();
        System.out.print("Enter new GPA: ");
        double gpa = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Student student = new Student(id, name, email, age, major, gpa);
        studentDAO.updateStudent(student);
        System.out.println("Student updated successfully");
    }

    private static void deleteStudent() throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        studentDAO.deleteStudent(id);
        System.out.println("Student deleted successfully");
    }

    private static void searchStudents() throws SQLException {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();
        List<Student> students = studentDAO.searchStudentByNameOrMajor(keyword);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void viewAverageGpa() throws SQLException {
        double averageGpa = studentDAO.getAllAvgStudent();
        System.out.println("Average GPA: " + averageGpa);
    }

    private static void viewStudentCount() throws SQLException {
        int count = studentDAO.getStudentCount();
        System.out.println("Total students: " + count);
    }
}