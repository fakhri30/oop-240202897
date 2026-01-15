package com.upb.agripos.exception;

/**
 * Exception when product is not found in database
 * Thrown when querying for non-existent product
 */
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
    
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}