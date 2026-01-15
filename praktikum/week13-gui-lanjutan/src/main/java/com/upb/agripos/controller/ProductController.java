package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


public class ProductController {
    private final ProductService productService;
    private final ObservableList<Product> productList;

    public ProductController() {
        this.productService = new ProductService();
        this.productList = FXCollections.observableArrayList();
    }

    
    public ObservableList<Product> load() {
        try {
            List<Product> products = productService.findAll();
            productList.clear();
            productList.addAll(products);
            System.out.println("✓ Data produk berhasil dimuat: " + products.size() + " item");
        } catch (Exception e) {
            System.err.println("✗ Error loading data: " + e.getMessage());
            e.printStackTrace();
        }
        return productList;
    }

    
    public boolean add(Product product) {
        try {
            boolean success = productService.insert(product);
            if (success) {
                productList.add(product);
                System.out.println("✓ Produk berhasil ditambahkan: " + product.getCode());
            }
            return success;
        } catch (IllegalArgumentException e) {
            System.err.println("✗ Validasi gagal: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("✗ Error adding product: " + e.getMessage());
            return false;
        }
    }

   
    public boolean delete(String code) {
        try {
            boolean success = productService.delete(code);
            if (success) {
                productList.removeIf(p -> p.getCode().equals(code));
                System.out.println("✓ Produk berhasil dihapus: " + code);
            }
            return success;
        } catch (IllegalArgumentException e) {
            System.err.println("✗ Validasi gagal: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("✗ Error deleting product: " + e.getMessage());
            return false;
        }
    }

    
    public boolean update(Product product) {
        try {
            boolean success = productService.update(product);
            if (success) {
                // Update item di ObservableList
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getCode().equals(product.getCode())) {
                        productList.set(i, product);
                        break;
                    }
                }
                System.out.println("✓ Produk berhasil diupdate: " + product.getCode());
            }
            return success;
        } catch (Exception e) {
            System.err.println("✗ Error updating product: " + e.getMessage());
            return false;
        }
    }

    
    public ObservableList<Product> getProductList() {
        return productList;
    }
}