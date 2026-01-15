package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

/**
 * Implementasi ProductDAO menggunakan JDBC
 * Menangani semua operasi CRUD ke database PostgreSQL
 */
public class ProductDAOImpl implements ProductDAO {

    private final Connection connection;

    /**
     * Constructor menerima Connection dari luar (Dependency Injection)
     * @param connection koneksi database yang sudah terbuka
     */
    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Menambahkan product baru ke database
     */
    @Override
    public void insert(Product p) throws Exception {
        String sql = "INSERT INTO products(code, name, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getCode());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            
            int rowsAffected = ps.executeUpdate();
            System.out.println("INSERT: " + rowsAffected + " row(s) inserted.");
        }
    }

    /**
     * Mencari product berdasarkan kode
     */
    @Override
    public Product findByCode(String code) throws Exception {
        String sql = "SELECT * FROM products WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                    );
                }
            }
        }
        return null; // Product tidak ditemukan
    }

    /**
     * Mengambil semua product dari database
     */
    @Override
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY code";
        
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
                list.add(product);
            }
        }
        
        System.out.println("FIND ALL: " + list.size() + " product(s) found.");
        return list;
    }

    /**
     * Mengupdate data product yang sudah ada
     */
    @Override
    public void update(Product p) throws Exception {
        String sql = "UPDATE products SET name=?, price=?, stock=? WHERE code=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getCode());
            
            int rowsAffected = ps.executeUpdate();
            System.out.println("UPDATE: " + rowsAffected + " row(s) updated.");
        }
    }

    /**
     * Menghapus product berdasarkan kode
     */
    @Override
    public void delete(String code) throws Exception {
        String sql = "DELETE FROM products WHERE code=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            
            int rowsAffected = ps.executeUpdate();
            System.out.println("DELETE: " + rowsAffected + " row(s) deleted.");
        }
    }
}