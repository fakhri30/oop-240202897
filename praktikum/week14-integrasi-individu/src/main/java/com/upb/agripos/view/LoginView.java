package com.upb.agripos.view;

import com.upb.agripos.controller.LoginController;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX View for User Login (Bab 12-13)
 * Provides login interface for Admin and Cashier users
 */
public class LoginView {
    private final LoginController controller;
    private final Stage primaryStage;
    private final Runnable onLoginSuccess;

    // UI Components
    private TextField txtUsername;
    private PasswordField txtPassword;
    private Label lblError;
    private Button btnLogin;

    public LoginView(LoginController controller, Stage primaryStage, Runnable onLoginSuccess) {
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.onLoginSuccess = onLoginSuccess;
    }

    public void show() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Center: Login panel
        VBox centerPanel = createLoginPanel();
        root.setCenter(centerPanel);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Agri-POS - Login");
        primaryStage.show();
    }

    private VBox createLoginPanel() {
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(50));
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width: 1;");

        // Title
        Label title = new Label("Agri-POS Login");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        panel.getChildren().add(title);

        // Subtitle
        Label subtitle = new Label("Enter your credentials");
        subtitle.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666;");
        panel.getChildren().add(subtitle);

        // Error label
        lblError = new Label();
        lblError.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 12px;");
        lblError.setWrapText(true);
        panel.getChildren().add(lblError);

        // Username field
        Label lblUsername = new Label("Username:");
        lblUsername.setStyle("-fx-font-size: 14px;");
        txtUsername = new TextField();
        txtUsername.setPromptText("Enter your username");
        txtUsername.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
        panel.getChildren().addAll(lblUsername, txtUsername);

        // Password field
        Label lblPassword = new Label("Password:");
        lblPassword.setStyle("-fx-font-size: 14px;");
        txtPassword = new PasswordField();
        txtPassword.setPromptText("Enter your password");
        txtPassword.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
        panel.getChildren().addAll(lblPassword, txtPassword);

        // Login button
        btnLogin = new Button("LOGIN");
        btnLogin.setStyle("-fx-font-size: 14px; -fx-padding: 10px 50px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-cursor: hand;");
        btnLogin.setPrefWidth(Double.MAX_VALUE);
        btnLogin.setOnAction(e -> handleLogin());
        panel.getChildren().add(btnLogin);

        // Info label
        Label infoLabel = new Label("Demo Credentials:\nAdmin: admin / password\nCashier: kasir / password");
        infoLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #0066cc; -fx-padding: 10px;");
        infoLabel.setWrapText(true);
        panel.getChildren().add(infoLabel);

        return panel;
    }

    private void handleLogin() {
        clearError();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter username and password");
            return;
        }

        try {
            btnLogin.setDisable(true);
            User user = controller.authenticate(username, password);
            System.out.println("Login successful: " + user.getUsername() + " - " + user.getRole());
            showSuccess("Login successful! Welcome, " + user.getFullName());
            
            // Switch view after a short delay on JavaFX thread
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    javafx.application.Platform.runLater(() -> {
                        System.out.println("Calling onLoginSuccess callback...");
                        onLoginSuccess.run();
                    });
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        } catch (ValidationException ex) {
            System.out.println("Login failed: " + ex.getMessage());
            showError("Login failed: " + ex.getMessage());
            btnLogin.setDisable(false);
        } catch (Exception ex) {
            System.out.println("Error occurred: " + ex.getMessage());
            ex.printStackTrace();
            showError("An error occurred: " + ex.getMessage());
            btnLogin.setDisable(false);
        }
    }

    private void showError(String message) {
        lblError.setText(message);
        lblError.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 12px;");
    }

    private void showSuccess(String message) {
        lblError.setText(message);
        lblError.setStyle("-fx-text-fill: #00cc00; -fx-font-size: 12px;");
    }

    private void clearError() {
        lblError.setText("");
    }

    public User getCurrentUser() {
        return controller.getCurrentUser();
    }
}
