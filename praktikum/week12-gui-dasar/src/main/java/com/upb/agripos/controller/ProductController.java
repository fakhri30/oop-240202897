package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ProductController {
    private ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    // Handler untuk tombol Tambah Produk (UC-01, AD-01, SD-01)
    public void add(TextField txtCode, TextField txtName, 
                    TextField txtPrice, TextField txtStock, 
                    ListView<String> listView) {
        try {
            // Validasi input kosong
            if (txtCode.getText().trim().isEmpty() || 
                txtName.getText().trim().isEmpty() ||
                txtPrice.getText().trim().isEmpty() || 
                txtStock.getText().trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Input Tidak Lengkap", 
                         "Semua field harus diisi!");
                return;
            }

            // Parsing data
            String code = txtCode.getText().trim();
            String name = txtName.getText().trim();
            double price = Double.parseDouble(txtPrice.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());

            // Buat object Product
            Product product = new Product(code, name, price, stock);

            // Panggil Service untuk insert (mengikuti SD-01)
            productService.insert(product);

            // Update UI - tambahkan ke ListView
            listView.getItems().add(product.toString());

            // Clear form
            clearForm(txtCode, txtName, txtPrice, txtStock);

            // Tampilkan pesan sukses
            showAlert(Alert.AlertType.INFORMATION, "Sukses", 
                     "Produk berhasil ditambahkan!");

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", 
                     "Harga dan Stok harus berupa angka!");
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Validasi Error", e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", 
                     "Terjadi kesalahan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Load semua produk dari database
    public void loadProducts(ListView<String> listView) {
        try {
            listView.getItems().clear();
            for (Product p : productService.getAllProducts()) {
                listView.getItems().add(p.toString());
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", 
                     "Gagal memuat data: " + e.getMessage());
        }
    }

    // Helper method untuk clear form
    private void clearForm(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    // Helper method untuk menampilkan alert
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}