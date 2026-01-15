package com.upb.agripos.model;

/**
 * Enum representing user roles in the system
 */
public enum UserRole {
    ADMIN("Administrator - Full system access"),
    KASIR("Cashier - Point of Sale operations");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
