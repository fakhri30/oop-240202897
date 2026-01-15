package com.upb.agripos.view;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * JavaFX View for Agri-POS application (Bab 12-13)
 */
public class PosView {
    private final PosController controller;
    private final Stage primaryStage;
    
    // Product table
    private TableView<Product> productTable;
    private ObservableList<Product> productData;
    
    // Cart table
    private TableView<CartItem> cartTable;
    private ObservableList<CartItem> cartData;
    
    // Input fields
    private TextField txtCode, txtName, txtPrice, txtStock, txtQuantity;
    private Label lblTotal, lblItemCount;

    public PosView(PosController controller, Stage primaryStage) {
        this.controller = controller;
        this.primaryStage = primaryStage;
    }

    public void show() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Top: Title
        Label title = new Label("Agri-POS System");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        root.setTop(title);
        BorderPane.setMargin(title, new Insets(0, 0, 10, 0));

        // Center: Split view
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(createProductPanel(), createCartPanel());
        splitPane.setDividerPositions(0.6);
        root.setCenter(splitPane);

        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Agri-POS - Point of Sale System");
        primaryStage.show();

        loadProducts();
    }

    private VBox createProductPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));

        Label header = new Label("Product Management");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Input form
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        txtCode = new TextField();
        txtName = new TextField();
        txtPrice = new TextField();
        txtStock = new TextField();

        form.add(new Label("Code:"), 0, 0);
        form.add(txtCode, 1, 0);
        form.add(new Label("Name:"), 0, 1);
        form.add(txtName, 1, 1);
        form.add(new Label("Price:"), 0, 2);
        form.add(txtPrice, 1, 2);
        form.add(new Label("Stock:"), 0, 3);
        form.add(txtStock, 1, 3);

        // Buttons
        HBox buttonBox = new HBox(10);
        Button btnAdd = new Button("Add Product");
        Button btnDelete = new Button("Delete Product");
        Button btnRefresh = new Button("Refresh");

        btnAdd.setOnAction(e -> handleAddProduct());
        btnDelete.setOnAction(e -> handleDeleteProduct());
        btnRefresh.setOnAction(e -> loadProducts());

        buttonBox.getChildren().addAll(btnAdd, btnDelete, btnRefresh);

        // Table
        productTable = createProductTable();

        panel.getChildren().addAll(header, form, buttonBox, productTable);
        VBox.setVgrow(productTable, Priority.ALWAYS);

        return panel;
    }

    private VBox createCartPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));

        Label header = new Label("Shopping Cart");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Quantity input
        HBox quantityBox = new HBox(10);
        quantityBox.getChildren().addAll(
            new Label("Quantity:"),
            txtQuantity = new TextField("1")
        );
        txtQuantity.setPrefWidth(80);

        // Buttons
        HBox buttonBox = new HBox(10);
        Button btnAddToCart = new Button("Add to Cart");
        Button btnRemoveFromCart = new Button("Remove from Cart");
        Button btnClearCart = new Button("Clear Cart");

        btnAddToCart.setOnAction(e -> handleAddToCart());
        btnRemoveFromCart.setOnAction(e -> handleRemoveFromCart());
        btnClearCart.setOnAction(e -> handleClearCart());

        buttonBox.getChildren().addAll(btnAddToCart, btnRemoveFromCart, btnClearCart);

        // Cart table
        cartTable = createCartTable();

        // Summary
        HBox summaryBox = new HBox(20);
        lblItemCount = new Label("Items: 0");
        lblTotal = new Label("Total: Rp 0.00");
        lblTotal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        summaryBox.getChildren().addAll(lblItemCount, lblTotal);

        panel.getChildren().addAll(header, quantityBox, buttonBox, cartTable, summaryBox);
        VBox.setVgrow(cartTable, Priority.ALWAYS);

        return panel;
    }

    private TableView<Product> createProductTable() {
        TableView<Product> table = new TableView<>();
        productData = FXCollections.observableArrayList();

        TableColumn<Product, String> colCode = new TableColumn<>("Code");
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Product, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> colStock = new TableColumn<>("Stock");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table.getColumns().addAll(colCode, colName, colPrice, colStock);
        table.setItems(productData);

        return table;
    }

    private TableView<CartItem> createCartTable() {
        TableView<CartItem> table = new TableView<>();
        cartData = FXCollections.observableArrayList();

        TableColumn<CartItem, String> colName = new TableColumn<>("Product");
        colName.setCellValueFactory(data -> 
            new javafx.beans.property.SimpleStringProperty(
                data.getValue().getProduct().getName()
            )
        );

        TableColumn<CartItem, Double> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(data -> 
            new javafx.beans.property.SimpleDoubleProperty(
                data.getValue().getProduct().getPrice()
            ).asObject()
        );

        TableColumn<CartItem, Integer> colQty = new TableColumn<>("Qty");
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<CartItem, Double> colSubtotal = new TableColumn<>("Subtotal");
        colSubtotal.setCellValueFactory(data -> 
            new javafx.beans.property.SimpleDoubleProperty(
                data.getValue().getSubtotal()
            ).asObject()
        );

        table.getColumns().addAll(colName, colPrice, colQty, colSubtotal);
        table.setItems(cartData);

        return table;
    }

    private void handleAddProduct() {
        try {
            String code = txtCode.getText().trim();
            String name = txtName.getText().trim();
            double price = Double.parseDouble(txtPrice.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());

            controller.addProduct(code, name, price, stock);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Product added successfully");
            clearProductForm();
            loadProducts();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid price or stock format");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void handleDeleteProduct() {
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a product to delete");
            return;
        }

        try {
            controller.deleteProduct(selected.getCode());
            showAlert(Alert.AlertType.INFORMATION, "Success", "Product deleted successfully");
            loadProducts();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void handleAddToCart() {
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a product");
            return;
        }

        try {
            int quantity = Integer.parseInt(txtQuantity.getText().trim());
            controller.addToCart(selected, quantity);
            updateCartView();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Added to cart");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid quantity");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void handleRemoveFromCart() {
        CartItem selected = cartTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select an item to remove");
            return;
        }

        controller.removeFromCart(selected.getProduct().getCode());
        updateCartView();
    }

    private void handleClearCart() {
        controller.clearCart();
        updateCartView();
        showAlert(Alert.AlertType.INFORMATION, "Success", "Cart cleared");
    }

    private void loadProducts() {
        try {
            productData.clear();
            productData.addAll(controller.loadAllProducts());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load products: " + e.getMessage());
        }
    }

    private void updateCartView() {
        cartData.clear();
        cartData.addAll(controller.getCartItems());
        
        lblItemCount.setText("Items: " + controller.getCartItemCount());
        lblTotal.setText(String.format("Total: Rp %.2f", controller.getCartTotal()));
    }

    private void clearProductForm() {
        txtCode.clear();
        txtName.clear();
        txtPrice.clear();
        txtStock.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}