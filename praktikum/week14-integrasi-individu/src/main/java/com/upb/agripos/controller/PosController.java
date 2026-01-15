package com.upb.agripos.controller;

import com.upb.agripos.exception.InsufficientStockException;
import com.upb.agripos.exception.ProductNotFoundException;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;

import java.sql.SQLException;
import java.util.List;

/**
 * Controller layer - mediates between View and Services
 * Implements DIP (Dependency Inversion Principle)
 */
public class PosController {
    private final ProductService productService;
    private final CartService cartService;

    public PosController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    // Product operations
    public void addProduct(String code, String name, double price, int stock) 
            throws ValidationException, SQLException {
        Product product = new Product(code, name, price, stock);
        productService.addProduct(product);
    }

    public void deleteProduct(String code) 
            throws ValidationException, SQLException, ProductNotFoundException {
        productService.deleteProduct(code);
    }

    public List<Product> loadAllProducts() throws SQLException {
        return productService.getAllProducts();
    }

    public Product getProductByCode(String code) throws SQLException, ProductNotFoundException {
        return productService.getProductByCode(code);
    }

    // Cart operations
    public void addToCart(Product product, int quantity) 
            throws ValidationException, InsufficientStockException {
        cartService.addToCart(product, quantity);
    }

    public void removeFromCart(String productCode) {
        cartService.removeFromCart(productCode);
    }

    public void clearCart() {
        cartService.clearCart();
    }

    public List<CartItem> getCartItems() {
        return cartService.getCartItems();
    }

    public double getCartTotal() {
        return cartService.calculateTotal();
    }

    public int getCartItemCount() {
        return cartService.getTotalItems();
    }

    public boolean isCartEmpty() {
        return cartService.isCartEmpty();
    }
}