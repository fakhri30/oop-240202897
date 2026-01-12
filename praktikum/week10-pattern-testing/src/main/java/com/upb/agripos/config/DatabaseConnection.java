package com.upb.agripos.config;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    // Constructor PRIVATE (ciri Singleton)
    private DatabaseConnection() {
        System.out.println("DatabaseConnection dibuat");
    }

    // Method akses global
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
