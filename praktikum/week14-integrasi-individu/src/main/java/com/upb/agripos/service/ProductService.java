package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.exception.ProductNotFoundException;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Service layer for Product business logic
 * Implements validation and error handling (Bab 9)
 */
public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void addProduct(Product product) throws ValidationException, SQLException {
        validateProduct(product);
        
        // Check if product already exists
        Product existing = productDAO.findByCode(product.getCode());
        if (existing != null) {
            throw new ValidationException("Product with code " + product.getCode() + " already exists");
        }
        
        productDAO.insert(product);
    }

    public void updateProduct(Product product) throws ValidationException, SQLException, ProductNotFoundException {
        validateProduct(product);
        
        Product existing = productDAO.findByCode(product.getCode());
        if (existing == null) {
            throw new ProductNotFoundException("Product with code " + product.getCode() + " not found");
        }
        
        productDAO.update(product);
    }

    public void deleteProduct(String code) throws ValidationException, SQLException, ProductNotFoundException {
        if (code == null || code.trim().isEmpty()) {
            throw new ValidationException("Product code cannot be empty");
        }
        
        Product existing = productDAO.findByCode(code);
        if (existing == null) {
            throw new ProductNotFoundException("Product with code " + code + " not found");
        }
        
        productDAO.delete(code);
    }

    public Product getProductByCode(String code) throws SQLException, ProductNotFoundException {
        Product product = productDAO.findByCode(code);
        if (product == null) {
            throw new ProductNotFoundException("Product with code " + code + " not found");
        }
        return product;
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.findAll();
    }

    private void validateProduct(Product product) throws ValidationException {
        if (product.getCode() == null || product.getCode().trim().isEmpty()) {
            throw new ValidationException("Product code cannot be empty");
        }
        
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new ValidationException("Product name cannot be empty");
        }
        
        if (product.getPrice() < 0) {
            throw new ValidationException("Product price cannot be negative");
        }
        
        if (product.getStock() < 0) {
            throw new ValidationException("Product stock cannot be negative");
        }
    }
}