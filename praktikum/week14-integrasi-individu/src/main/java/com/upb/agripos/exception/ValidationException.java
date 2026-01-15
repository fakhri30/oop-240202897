package com.upb.agripos.exception;

/**
 * Exception for validation errors (Bab 9)
 * Thrown when input validation fails
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
    
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}