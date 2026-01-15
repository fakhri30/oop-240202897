package com.upb.agripos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Konfigurasi koneksi database PostgreSQL
 * Sesuai dengan implementasi Week 11
 */
public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/agripos_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "fakhrigg";

    /**
     * Mendapatkan koneksi ke database
     * @return Connection object
     * @throws SQLException jika koneksi gagal
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver tidak ditemukan", e);
        }
    }

    /**
     * Test koneksi database
     */
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("✓ Koneksi database berhasil!");
            }
        } catch (SQLException e) {
            System.err.println("✗ Koneksi database gagal: " + e.getMessage());
        }
    }
}