package com.upb.agripos;

import com.upb.agripos.controller.LoginController;
import com.upb.agripos.controller.PosController;
import com.upb.agripos.dao.DemoUserDAO;
import com.upb.agripos.dao.JdbcProductDAO;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.User;
import com.upb.agripos.service.AuthService;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.LoginView;
import com.upb.agripos.view.PosView;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main JavaFX Application for Agri-POS
 * Integrates all components from Bab 1-14 (including authentication)
 */
public class AppJavaFX extends Application {
    private Stage primaryStage;
    private LoginController loginController;
    private PosController posController;

    @Override
    public void start(Stage primaryStage) {
        // Bab 1 - Print identity
        printIdentity();

        this.primaryStage = primaryStage;

        // Initialize authentication layer
        // Using DemoUserDAO by default for testing
        // Switch to JdbcUserDAO when database is properly set up
        AuthService authService = new AuthService(new DemoUserDAO());
        System.out.println("Using DEMO authentication mode");
        
        loginController = new LoginController(authService);

        // Initialize POS layers following DIP (Dependency Inversion Principle)
        ProductDAO productDAO = new JdbcProductDAO();
        ProductService productService = new ProductService(productDAO);
        CartService cartService = new CartService();
        posController = new PosController(productService, cartService);

        // Show login view first
        showLoginView();
    }

    private void printIdentity() {
        // Bab 1 requirement
        String name = "Your Name";  // Replace with actual name
        String nim = "12345678";     // Replace with actual NIM
        System.out.println("Hello World, I am " + name + "-" + nim);
        System.out.println("=== Agri-POS Application Started ===");
    }

    private void showLoginView() {
        LoginView loginView = new LoginView(
            loginController,
            primaryStage,
            this::showPosView
        );
        loginView.show();
    }

    private void showPosView() {
        try {
            User currentUser = loginController.getCurrentUser();
            if (currentUser == null) {
                System.err.println("ERROR: No user logged in!");
                return;
            }
            
            System.out.println("User logged in: " + currentUser.getFullName() + " (" + currentUser.getRole() + ")");
            System.out.println("Showing POS view...");

            // Initialize and show POS view
            PosView view = new PosView(posController, primaryStage);
            view.show();
            System.out.println("POS view displayed successfully");
        } catch (Exception e) {
            System.err.println("ERROR in showPosView: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.out.println("=== Agri-POS Application Stopped ===");
    }

    public static void main(String[] args) {
        launch(args);
    }
}