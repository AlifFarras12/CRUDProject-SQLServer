package com.juaracoding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/university_db";
    private static final String USER = "juaracodingdb";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
