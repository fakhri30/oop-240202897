package com.upb.agripos.model;

/**
 * Model class untuk merepresentasikan entitas Product
 * Week 11 - DAO dan CRUD Database
 */
public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;

    /**
     * Constructor dengan semua parameter
     */
    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getter methods
    public String getCode() { 
        return code; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public double getPrice() { 
        return price; 
    }
    
    public int getStock() { 
        return stock; 
    }

    // Setter methods (tidak ada setter untuk code karena primary key)
    public void setName(String name) { 
        this.name = name; 
    }
    
    public void setPrice(double price) { 
        this.price = price; 
    }
    
    public void setStock(int stock) { 
        this.stock = stock; 
    }

    /**
     * Override toString untuk kemudahan debugging dan display
     */
    @Override
    public String toString() {
        return String.format("Product[code=%s, name=%s, price=%.2f, stock=%d]", 
            code, name, price, stock);
    }
}