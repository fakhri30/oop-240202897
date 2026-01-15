package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO Interface for Product (DIP - Dependency Inversion Principle)
 */
public interface ProductDAO {
    void insert(Product product) throws SQLException;
    void update(Product product) throws SQLException;
    void delete(String code) throws SQLException;
    Product findByCode(String code) throws SQLException;
    List<Product> findAll() throws SQLException;
}