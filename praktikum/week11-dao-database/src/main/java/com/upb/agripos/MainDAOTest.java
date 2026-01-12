package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class MainDAOTest {

    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos",
                "postgres",
                "1234"
            );

            ProductDAO dao = new ProductDAOImpl(conn);

            System.out.println("=== INSERT ===");
            dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));

            System.out.println("=== UPDATE ===");
            dao.update(new Product("P01", "Pupuk Organik Premium", 30000, 8));

            System.out.println("=== FIND BY CODE ===");
            Product p = dao.findByCode("P01");
            System.out.println(p.getCode() + " - " + p.getName());

            System.out.println("=== FIND ALL ===");
            List<Product> products = dao.findAll();
            for (Product prod : products) {
                System.out.println(prod.getCode() + " | " + prod.getName());
            }

            System.out.println("=== DELETE ===");
            dao.delete("P01");

            conn.close();
            System.out.println("CRUD selesai tanpa error ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
