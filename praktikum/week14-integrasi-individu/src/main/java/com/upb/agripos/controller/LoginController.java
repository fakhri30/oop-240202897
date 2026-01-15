package com.upb.agripos.controller;

import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.User;
import com.upb.agripos.service.AuthService;

import java.sql.SQLException;

/**
 * Controller layer for Login operations
 * Mediates between LoginView and AuthService
 */
public class LoginController {
    private final AuthService authService;
    private User currentUser;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Authenticate user
     * @param username the username
     * @param password the password
     * @return authenticated User object
     * @throws ValidationException if authentication fails
     * @throws SQLException if database error occurs
     */
    public User authenticate(String username, String password) throws ValidationException, SQLException {
        this.currentUser = authService.login(username, password);
        return this.currentUser;
    }

    /**
     * Get currently logged in user
     * @return current User object or null if no user is logged in
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Clear current user (logout)
     */
    public void logout() {
        this.currentUser = null;
    }

    /**
     * Check if user is logged in
     * @return true if a user is logged in
     */
    public boolean isLoggedIn() {
        return this.currentUser != null;
    }
}
