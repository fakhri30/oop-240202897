package com.upb.agripos;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.dao.JdbcProductDAO;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.PosView;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main JavaFX Application for Agri-POS
 * Integrates all components from Bab 1-13
 */
public class AppJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Bab 1 - Print identity
        printIdentity();

        // Initialize layers following DIP (Dependency Inversion Principle)
        ProductDAO productDAO = new JdbcProductDAO();
        ProductService productService = new ProductService(productDAO);
        CartService cartService = new CartService();
        PosController controller = new PosController(productService, cartService);

        // Initialize and show view
        PosView view = new PosView(controller, primaryStage);
        view.show();
    }

    private void printIdentity() {
        // Bab 1 requirement
        String name = "Your Name";  // Replace with actual name
        String nim = "12345678";     // Replace with actual NIM
        System.out.println("Hello World, I am " + name + "-" + nim);
        System.out.println("=== Agri-POS Application Started ===");
    }

    @Override
    public void stop() {
        System.out.println("=== Agri-POS Application Stopped ===");
    }

    public static void main(String[] args) {
        launch(args);
    }
}