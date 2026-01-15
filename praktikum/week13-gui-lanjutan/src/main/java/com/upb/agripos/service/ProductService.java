package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

import java.util.List;

/**
 * Service layer untuk Product
 * Mengimplementasikan prinsip DIP dari SOLID (Bab 6)
 * Controller/View tidak langsung akses DAO, tapi lewat Service
 */
public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    /**
     * Mendapatkan semua produk
     * Digunakan untuk UC-02: Lihat Daftar Produk
     */
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    /**
     * Mencari produk berdasarkan kode
     */
    public Product findByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }
        return productDAO.findByCode(code);
    }

    /**
     * Menambah produk baru
     * UC-04: Tambah Produk
     * Validasi dilakukan di service layer
     */
    public boolean insert(Product product) {
        // Validasi input
        if (product == null) {
            throw new IllegalArgumentException("Product tidak boleh null");
        }
        if (product.getCode() == null || product.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Harga tidak boleh negatif");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        }

        // Cek apakah kode sudah ada
        Product existing = productDAO.findByCode(product.getCode());
        if (existing != null) {
            throw new IllegalArgumentException("Kode produk sudah ada: " + product.getCode());
        }

        return productDAO.insert(product);
    }

    /**
     * Menghapus produk
     * UC-03: Hapus Produk
     */
    public boolean delete(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }

        // Cek apakah produk ada
        Product existing = productDAO.findByCode(code);
        if (existing == null) {
            throw new IllegalArgumentException("Produk tidak ditemukan: " + code);
        }

        return productDAO.delete(code);
    }

    /**
     * Mengupdate produk
     */
    public boolean update(Product product) {
        // Validasi input
        if (product == null) {
            throw new IllegalArgumentException("Product tidak boleh null");
        }
        if (product.getCode() == null || product.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }

        // Cek apakah produk ada
        Product existing = productDAO.findByCode(product.getCode());
        if (existing == null) {
            throw new IllegalArgumentException("Produk tidak ditemukan: " + product.getCode());
        }

        return productDAO.update(product);
    }
}