package com.upb.agripos.service;

import com.upb.agripos.dao.UserDAO;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.User;

import java.sql.SQLException;

/**
 * Service layer for User authentication
 * Handles login validation and user verification (Bab 9)
 */
public class AuthService {
    private final UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Authenticate user with username and password
     * @param username the username to authenticate
     * @param password the password to verify
     * @return User object if authentication is successful
     * @throws ValidationException if username or password is invalid
     * @throws SQLException if database error occurs
     */
    public User login(String username, String password) throws ValidationException, SQLException {
        // Validate input
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("Password cannot be empty");
        }

        // Find user by username
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new ValidationException("Invalid username or password");
        }

        // Verify password
        if (!user.getPassword().equals(password)) {
            throw new ValidationException("Invalid username or password");
        }

        return user;
    }

    /**
     * Register a new user (admin only operation)
     * @param user the user to register
     * @throws ValidationException if user data is invalid
     * @throws SQLException if database error occurs
     */
    public void register(User user) throws ValidationException, SQLException {
        // Validate input
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new ValidationException("Password cannot be empty");
        }
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            throw new ValidationException("Full name cannot be empty");
        }

        // Check if username already exists
        User existing = userDAO.findByUsername(user.getUsername());
        if (existing != null) {
            throw new ValidationException("Username already exists");
        }

        userDAO.insert(user);
    }

    /**
     * Get user by ID
     * @param id the user ID
     * @return User object
     * @throws SQLException if database error occurs
     */
    public User getUserById(int id) throws SQLException {
        return userDAO.findById(id);
    }
}
