package com.upb.agripos.view;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * View untuk menampilkan TableView produk
 * Menggunakan lambda expression untuk event handling (Bab 7 & 13)
 */
public class ProductTableView {
    private final ProductController controller;
    private TableView<Product> tableView;
    private Stage stage;

    public ProductTableView() {
        this.controller = new ProductController();
    }

    /**
     * Menampilkan GUI
     */
    public void show(Stage primaryStage) {
        this.stage = primaryStage;
        
        // Setup TableView
        tableView = createTableView();
        
        // Setup Buttons dengan Lambda Expression
        HBox buttonBox = createButtonBox();
        
        // Layout utama
        BorderPane root = new BorderPane();
        root.setTop(createHeader());
        root.setCenter(tableView);
        root.setBottom(buttonBox);
        root.setPadding(new Insets(10));
        
        // Scene
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("Agri-POS - Manajemen Produk");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Load data awal (UC-02: Lihat Daftar Produk)
        loadData();
    }

    /**
     * Membuat header
     */
    private VBox createHeader() {
        Label title = new Label("DAFTAR PRODUK AGRI-POS");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
        VBox header = new VBox(title);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10));
        return header;
    }

    /**
     * Membuat TableView dengan kolom sesuai spesifikasi
     */
    private TableView<Product> createTableView() {
        TableView<Product> table = new TableView<>();
        
        // Kolom Kode
        TableColumn<Product, String> codeCol = new TableColumn<>("Kode");
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        codeCol.setPrefWidth(100);
        
        // Kolom Nama
        TableColumn<Product, String> nameCol = new TableColumn<>("Nama");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(300);
        
        // Kolom Harga
        TableColumn<Product, Double> priceCol = new TableColumn<>("Harga");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(150);
        
        // Format harga dengan Rp
        priceCol.setCellFactory(col -> new TableCell<Product, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("Rp %.2f", price));
                }
            }
        });
        
        // Kolom Stok
        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stok");
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        stockCol.setPrefWidth(100);
        
        table.getColumns().addAll(codeCol, nameCol, priceCol, stockCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        return table;
    }

    /**
     * Membuat button box dengan Lambda Expression
     */
    private HBox createButtonBox() {
        // Tombol Tambah Produk - Lambda Expression
        Button btnAdd = new Button("➕ Tambah Produk");
        btnAdd.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        btnAdd.setPrefWidth(150);
        btnAdd.setOnAction(e -> showAddDialog());
        
        // Tombol Hapus Produk - Lambda Expression (Sesuai Contoh di Modul)
        Button btnDelete = new Button("🗑️ Hapus Produk");
        btnDelete.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14px;");
        btnDelete.setPrefWidth(150);
        btnDelete.setOnAction(e -> {
            Product selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                // Konfirmasi hapus (sesuai Activity Diagram)
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Konfirmasi Hapus");
                alert.setHeaderText("Hapus Produk: " + selected.getName());
                alert.setContentText("Yakin ingin menghapus produk ini?");
                
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            controller.delete(selected.getCode());
                            loadData(); // reload dari DAO
                            showInfo("Produk berhasil dihapus!");
                        } catch (Exception ex) {
                            showError("Gagal menghapus: " + ex.getMessage());
                        }
                    }
                });
            } else {
                showWarning("Pilih produk yang akan dihapus!");
            }
        });
        
        // Tombol Refresh - Lambda Expression
        Button btnRefresh = new Button("🔄 Refresh");
        btnRefresh.setStyle("-fx-font-size: 14px;");
        btnRefresh.setPrefWidth(100);
        btnRefresh.setOnAction(e -> loadData());
        
        HBox buttonBox = new HBox(10, btnAdd, btnDelete, btnRefresh);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        
        return buttonBox;
    }

    /**
     * Load data dari database via Controller
     * Mengikuti alur: View → Controller → Service → DAO → DB
     */
    private void loadData() {
        tableView.setItems(controller.load());
    }

    /**
     * Dialog untuk tambah produk
     */
    private void showAddDialog() {
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Tambah Produk Baru");
        dialog.setHeaderText("Masukkan data produk");
        
        // Buttons
        ButtonType saveButtonType = new ButtonType("Simpan", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        
        // Form
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        
        TextField codeField = new TextField();
        codeField.setPromptText("P001");
        TextField nameField = new TextField();
        nameField.setPromptText("Nama Produk");
        TextField priceField = new TextField();
        priceField.setPromptText("10000");
        TextField stockField = new TextField();
        stockField.setPromptText("100");
        
        grid.add(new Label("Kode:"), 0, 0);
        grid.add(codeField, 1, 0);
        grid.add(new Label("Nama:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Harga:"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Stok:"), 0, 3);
        grid.add(stockField, 1, 3);
        
        dialog.getDialogPane().setContent(grid);
        
        // Convert result - Lambda Expression
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    return new Product(
                        codeField.getText().trim(),
                        nameField.getText().trim(),
                        Double.parseDouble(priceField.getText().trim()),
                        Integer.parseInt(stockField.getText().trim())
                    );
                } catch (NumberFormatException e) {
                    showError("Format angka tidak valid!");
                    return null;
                }
            }
            return null;
        });
        
        // Show dan process - Lambda Expression
        dialog.showAndWait().ifPresent(product -> {
            try {
                controller.add(product);
                loadData();
                showInfo("Produk berhasil ditambahkan!");
            } catch (IllegalArgumentException e) {
                showError(e.getMessage());
            } catch (Exception e) {
                showError("Gagal menambah produk: " + e.getMessage());
            }
        });
    }

    // Helper methods untuk alert
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Peringatan");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}