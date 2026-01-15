package com.upb.agripos.exception;

/**
 * Exception for insufficient stock scenarios
 * Thrown when requested quantity exceeds available stock
 */
public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
    
    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
    }
}