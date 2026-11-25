package com.healthcare.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Central JDBC connection helper. Override credentials with environment variables. */
public final class DBConnection {
    private static final String URL = System.getenv().getOrDefault(
        "HEALTHCARE_DB_URL", "jdbc:mysql://localhost:3306/healthcare?useSSL=false&serverTimezone=UTC");
    private static final String USER = System.getenv().getOrDefault("HEALTHCARE_DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("HEALTHCARE_DB_PASSWORD", "root");

    private DBConnection() { }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
