package com.juaracoding;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class studentDAO extends databaseConnection {

    //CREATE STUDENT
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, email, age, major, gpa) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setInt(3, student.getAge());
            stmt.setString(4, student.getMajor());
            stmt.setDouble(5, student.getGpa());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    //READ STUDENT
    public List<Student> getAllStudent(){
        List<Student> students =new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = getConnection();
            Statement stmt =conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("major"),
                        rs.getDouble("gpa")
                );
                students.add(student);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    //UPDATE STUDENT
    public void updateStudent(Student student){
        String sql = "UPDATE students SET name = ?, email = ?, age = ?, major = ?, gpa = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setInt(3, student.getAge());
            stmt.setString(4, student.getMajor());
            stmt.setDouble(5, student.getGpa());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    //DELETE STUDENT
    public void deleteStudent(int id){
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //SEARCH STUDENT BY NAME or MAJOR
    public List<Student> searchStudentByNameOrMajor (String Term){
        String sql = "SELECT * FROM students WHERE name LIKE ? OR major LIKE ?";
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String pattern = "%" + Term + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("major"),
                        rs.getDouble("gpa")
                );
                students.add(student);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    //AVERAGE GPA ALL STUDENTS
    public double getAllAvgStudent(){
        String query = "SELECT AVG(gpa) AS average_gpa FROM students";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getDouble("average_gpa");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    //TOTAL ALL STUDENT
    public int getStudentCount(){
        String query = "SELECT COUNT(*) AS student_count FROM students";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("student_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
