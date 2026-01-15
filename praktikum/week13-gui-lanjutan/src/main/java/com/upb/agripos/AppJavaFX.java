package com.upb.agripos;

import com.upb.agripos.config.DatabaseConfig;
import com.upb.agripos.view.ProductTableView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Application JavaFX untuk Agri-POS
 * Week 13 - GUI Lanjutan dengan TableView dan Lambda Expression
 */
public class AppJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Test koneksi database
        System.out.println("=== AGRI-POS SYSTEM ===");
        System.out.println("Testing database connection...");
        DatabaseConfig.testConnection();
        System.out.println();
        
        // Launch GUI
        ProductTableView view = new ProductTableView();
        view.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
