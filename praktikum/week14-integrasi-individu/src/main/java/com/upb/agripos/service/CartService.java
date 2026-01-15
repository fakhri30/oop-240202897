package com.upb.agripos.service;

import com.upb.agripos.exception.InsufficientStockException;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.Cart;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;

import java.util.List;

/**
 * Service layer for Cart business logic
 * Manages shopping cart operations (Bab 7)
 */
public class CartService {
    private final Cart cart;

    public CartService() {
        this.cart = new Cart();
    }

    public void addToCart(Product product, int quantity) throws ValidationException, InsufficientStockException {
        if (quantity <= 0) {
            throw new ValidationException("Quantity must be greater than 0");
        }
        
        if (quantity > product.getStock()) {
            throw new InsufficientStockException(
                "Insufficient stock for " + product.getName() + 
                ". Available: " + product.getStock() + ", Requested: " + quantity
            );
        }
        
        cart.addItem(product, quantity);
    }

    public void removeFromCart(String productCode) {
        cart.removeItem(productCode);
    }

    public void clearCart() {
        cart.clear();
    }

    public List<CartItem> getCartItems() {
        return cart.getItems();
    }

    public double calculateTotal() {
        return cart.getTotalPrice();
    }

    public int getTotalItems() {
        return cart.getTotalItems();
    }

    public boolean isCartEmpty() {
        return cart.isEmpty();
    }

    public Cart getCart() {
        return cart;
    }
}