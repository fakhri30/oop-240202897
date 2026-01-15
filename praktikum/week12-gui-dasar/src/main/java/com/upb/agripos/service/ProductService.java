package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    // Insert product dengan validasi
    public boolean insert(Product product) {
        // Validasi input
        if (product.getCode() == null || product.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari 0");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        }

        // Cek duplikasi kode
        if (productDAO.findByCode(product.getCode()) != null) {
            throw new IllegalArgumentException("Kode produk sudah ada");
        }

        productDAO.insert(product);
        return true;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    // Get product by code
    public Product getProductByCode(String code) {
        return productDAO.findByCode(code);
    }

    // Update product
    public void update(Product product) {
        if (product.getCode() == null || product.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }
        productDAO.update(product);
    }

    // Delete product
    public void delete(String code) {
        productDAO.delete(code);
    }
}