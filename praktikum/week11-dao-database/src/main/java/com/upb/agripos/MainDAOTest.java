package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

/**
 * Main class untuk testing implementasi DAO
 * Mendemonstrasikan semua operasi CRUD
 */
public class MainDAOTest {
    
    // Konfigurasi database
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/agripos";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "fakhrigg";
    
    public static void main(String[] args) {
        Connection conn = null;
        
        try {
            // 1. Membuka koneksi database
            System.out.println("=== Connecting to Database ===");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected successfully!\n");
            
            // 2. Membuat instance DAO
            ProductDAO dao = new ProductDAOImpl(conn);
            
            // 3. Testing INSERT
            System.out.println("=== Testing INSERT ===");
            Product p1 = new Product("P001", "Pupuk Organik", 25000, 10);
            Product p2 = new Product("P002", "Pestisida Alami", 35000, 15);
            Product p3 = new Product("P003", "Bibit Padi Unggul", 50000, 20);
            
            dao.insert(p1);
            dao.insert(p2);
            dao.insert(p3);
            System.out.println();
            
            // 4. Testing FIND BY CODE
            System.out.println("=== Testing FIND BY CODE ===");
            Product found = dao.findByCode("P001");
            if (found != null) {
                System.out.println("Product found: " + found);
            } else {
                System.out.println("Product not found");
            }
            System.out.println();
            
            // 5. Testing FIND ALL
            System.out.println("=== Testing FIND ALL ===");
            List<Product> allProducts = dao.findAll();
            for (Product p : allProducts) {
                System.out.println(p);
            }
            System.out.println();
            
            // 6. Testing UPDATE
            System.out.println("=== Testing UPDATE ===");
            Product updatedProduct = new Product("P001", "Pupuk Organik Premium", 30000, 8);
            dao.update(updatedProduct);
            
            // Verifikasi update
            Product afterUpdate = dao.findByCode("P001");
            System.out.println("After update: " + afterUpdate);
            System.out.println();
            
            // 7. Testing DELETE
            System.out.println("=== Testing DELETE ===");
            dao.delete("P003");
            
            // Verifikasi delete
            System.out.println("Remaining products:");
            List<Product> remaining = dao.findAll();
            for (Product p : remaining) {
                System.out.println(p);
            }
            System.out.println();
            
            System.out.println("=== All CRUD operations completed successfully! ===");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 8. Menutup koneksi
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("\nDatabase connection closed.");
                }
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }

            System.out.println(
                "Fakhri Fahmi Ramadan - 240202897" 
            );
           
        }
    }
} 