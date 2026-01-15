package com.upb.agripos.test;

import com.upb.agripos.exception.InsufficientStockException;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for CartService (Bab 10)
 * Tests cart functionality and calculations
 */
public class CartServiceTest {
    
    private CartService cartService;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        cartService = new CartService();
        product1 = new Product("P001", "Apple", 5000, 100);
        product2 = new Product("P002", "Orange", 3000, 50);
    }

    @Test
    public void testAddToCart_Success() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 5);
        
        assertEquals(1, cartService.getCartItems().size());
        assertEquals(5, cartService.getTotalItems());
        assertEquals(25000, cartService.calculateTotal(), 0.01);
    }

    @Test
    public void testAddToCart_MultipleProducts() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 3);
        cartService.addToCart(product2, 2);
        
        assertEquals(2, cartService.getCartItems().size());
        assertEquals(5, cartService.getTotalItems());
        assertEquals(21000, cartService.calculateTotal(), 0.01); // 15000 + 6000
    }

    @Test
    public void testAddToCart_SameProductTwice() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 3);
        cartService.addToCart(product1, 2);
        
        assertEquals(1, cartService.getCartItems().size());
        assertEquals(5, cartService.getTotalItems());
        assertEquals(25000, cartService.calculateTotal(), 0.01);
    }

    @Test
    public void testAddToCart_InvalidQuantity() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            cartService.addToCart(product1, 0);
        });
        
        assertTrue(exception.getMessage().contains("must be greater than 0"));
    }

    @Test
    public void testAddToCart_InsufficientStock() {
        InsufficientStockException exception = assertThrows(InsufficientStockException.class, () -> {
            cartService.addToCart(product1, 200); // Stock only 100
        });
        
        assertTrue(exception.getMessage().contains("Insufficient stock"));
    }

    @Test
    public void testRemoveFromCart() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 3);
        cartService.addToCart(product2, 2);
        
        cartService.removeFromCart("P001");
        
        assertEquals(1, cartService.getCartItems().size());
        assertEquals(2, cartService.getTotalItems());
        assertEquals(6000, cartService.calculateTotal(), 0.01);
    }

    @Test
    public void testClearCart() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 3);
        cartService.addToCart(product2, 2);
        
        cartService.clearCart();
        
        assertTrue(cartService.isCartEmpty());
        assertEquals(0, cartService.getTotalItems());
        assertEquals(0, cartService.calculateTotal(), 0.01);
    }

    @Test
    public void testCalculateTotal_MultipleItems() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 10); // 50000
        cartService.addToCart(product2, 5);  // 15000
        
        double expectedTotal = 65000;
        assertEquals(expectedTotal, cartService.calculateTotal(), 0.01);
    }

    @Test
    public void testGetCartItems_ReturnsCorrectList() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 2);
        cartService.addToCart(product2, 3);
        
        List<CartItem> items = cartService.getCartItems();
        
        assertNotNull(items);
        assertEquals(2, items.size());
        
        // Verify first item
        CartItem item1 = items.stream()
            .filter(i -> i.getProduct().getCode().equals("P001"))
            .findFirst()
            .orElse(null);
        assertNotNull(item1);
        assertEquals(2, item1.getQuantity());
        assertEquals(10000, item1.getSubtotal(), 0.01);
    }

    @Test
    public void testIsCartEmpty() throws ValidationException, InsufficientStockException {
        assertTrue(cartService.isCartEmpty());
        
        cartService.addToCart(product1, 1);
        assertFalse(cartService.isCartEmpty());
        
        cartService.clearCart();
        assertTrue(cartService.isCartEmpty());
    }
}
