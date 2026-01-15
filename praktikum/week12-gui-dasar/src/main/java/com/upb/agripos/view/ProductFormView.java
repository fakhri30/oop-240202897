package com.upb.agripos.view;

import com.upb.agripos.controller.ProductController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProductFormView {
    private ProductController controller;
    
    // UI Components
    private TextField txtCode;
    private TextField txtName;
    private TextField txtPrice;
    private TextField txtStock;
    private Button btnAdd;
    private Button btnRefresh;
    private ListView<String> listView;

    public ProductFormView() {
        controller = new ProductController();
    }

    public void start(Stage primaryStage) {
        // Create main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        // Header
        Label lblTitle = new Label("Agri-POS - Form Input Produk");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        HBox header = new HBox(lblTitle);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(0, 0, 20, 0));

        // Form Input
        GridPane formGrid = createFormGrid();

        // List View untuk menampilkan produk
        VBox listBox = createListBox();

        // Combine form and list
        VBox centerContent = new VBox(15);
        centerContent.getChildren().addAll(header, formGrid, listBox);

        root.setCenter(centerContent);

        // Setup event handlers
        setupEventHandlers();

        // Load existing products
        controller.loadProducts(listView);

        // Create scene
        Scene scene = new Scene(root, 700, 600);
        primaryStage.setTitle("Agri-POS - Kelola Produk");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createFormGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 5;");

        // Labels
        Label lblCode = new Label("Kode Produk:");
        Label lblName = new Label("Nama Produk:");
        Label lblPrice = new Label("Harga:");
        Label lblStock = new Label("Stok:");

        // TextFields
        txtCode = new TextField();
        txtCode.setPromptText("Contoh: P001");
        
        txtName = new TextField();
        txtName.setPromptText("Contoh: Pupuk Organik");
        
        txtPrice = new TextField();
        txtPrice.setPromptText("Contoh: 50000");
        
        txtStock = new TextField();
        txtStock.setPromptText("Contoh: 100");

        // Buttons
        btnAdd = new Button("Tambah Produk");
        btnAdd.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnAdd.setPrefWidth(150);

        btnRefresh = new Button("Refresh List");
        btnRefresh.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnRefresh.setPrefWidth(150);

        // Add to grid
        grid.add(lblCode, 0, 0);
        grid.add(txtCode, 1, 0);
        grid.add(lblName, 0, 1);
        grid.add(txtName, 1, 1);
        grid.add(lblPrice, 0, 2);
        grid.add(txtPrice, 1, 2);
        grid.add(lblStock, 0, 3);
        grid.add(txtStock, 1, 3);
        
        HBox buttonBox = new HBox(10, btnAdd, btnRefresh);
        grid.add(buttonBox, 0, 4, 2, 1);

        return grid;
    }

    private VBox createListBox() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));

        Label lblList = new Label("Daftar Produk:");
        lblList.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        listView = new ListView<>();
        listView.setPrefHeight(200);
        listView.setStyle("-fx-font-family: 'Courier New';");

        box.getChildren().addAll(lblList, listView);
        return box;
    }

    private void setupEventHandlers() {
        // Event handler untuk tombol Tambah (UC-01, AD-01, SD-01)
        btnAdd.setOnAction(event -> {
            controller.add(txtCode, txtName, txtPrice, txtStock, listView);
        });

        // Event handler untuk tombol Refresh
        btnRefresh.setOnAction(event -> {
            controller.loadProducts(listView);
        });
    }
}