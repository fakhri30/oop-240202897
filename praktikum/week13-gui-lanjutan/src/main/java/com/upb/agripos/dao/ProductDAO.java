package com.upb.agripos.dao;

import com.upb.agripos.config.DatabaseConfig;
import com.upb.agripos.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO untuk operasi CRUD Product
 * Mengimplementasikan desain dari Bab 6 dan Week 11
 */
public class ProductDAO {

    /**
     * Mengambil semua data produk dari database
     * UC-02: Lihat Daftar Produk
     */
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT code, name, price, stock FROM products ORDER BY code";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            System.err.println("Error findAll: " + e.getMessage());
            e.printStackTrace();
        }

        return products;
    }

    /**
     * Mencari produk berdasarkan kode
     */
    public Product findByCode(String code) {
        String sql = "SELECT code, name, price, stock FROM products WHERE code = ?";
        Product product = null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new Product(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error findByCode: " + e.getMessage());
        }

        return product;
    }

    /**
     * Menambah produk baru ke database
     * UC-04: Tambah Produk
     */
    public boolean insert(Product product) {
        String sql = "INSERT INTO products (code, name, price, stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getCode());
            pstmt.setString(2, product.getName());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error insert: " + e.getMessage());
            return false;
        }
    }

    /**
     * Menghapus produk berdasarkan kode
     * UC-03: Hapus Produk
     */
    public boolean delete(String code) {
        String sql = "DELETE FROM products WHERE code = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, code);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error delete: " + e.getMessage());
            return false;
        }
    }

    /**
     * Mengupdate data produk
     */
    public boolean update(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ? WHERE code = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getStock());
            pstmt.setString(4, product.getCode());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error update: " + e.getMessage());
            return false;
        }
    }
}