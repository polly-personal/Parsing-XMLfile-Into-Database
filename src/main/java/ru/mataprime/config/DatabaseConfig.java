package ru.mataprime.config;

import java.sql.*;

public class DatabaseConfig {
    private final String url = "jdbc:postgresql://127.0.0.1:5432/books";
    private final String user = "admin";
    private final String password = "pass";

    public void connectToDriverClassName() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
