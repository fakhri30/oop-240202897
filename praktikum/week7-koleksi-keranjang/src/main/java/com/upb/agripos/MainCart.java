package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        
        System.out.println("Hello, I am [Fakhri Fahmi Ramadan]-[240202897] (Week7)");
        System.out.println("Sistem Keranjang Belanja Agri-POS\n");

        // Membuat produk-produk
        Product p1 = new Product("P01", "Beras Premium", 50000);
        Product p2 = new Product("P02", "Pupuk Organik", 30000);
        Product p3 = new Product("P03", "Bibit Jagung", 15000);
        Product p4 = new Product("P04", "Pestisida", 45000);

        System.out.println(">>> DEMO 1: SHOPPING CART (ArrayList) <<<");
        demoShoppingCart(p1, p2, p3, p4);

        System.out.println("\n" + "=".repeat(70) + "\n");

        System.out.println(">>> DEMO 2: SHOPPING CART MAP (HashMap dengan Quantity) <<<");
        demoShoppingCartMap(p1, p2, p3, p4);
    }

    // Demo menggunakan ArrayList
    private static void demoShoppingCart(Product p1, Product p2, Product p3, Product p4) {
        ShoppingCart cart = new ShoppingCart();

        // Menambah produk
        System.out.println("\n--- MENAMBAH PRODUK ---");
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);
        cart.addProduct(p1); // Duplikat diizinkan

        // Menampilkan isi keranjang
        cart.printCart();

        // Menghapus produk
        System.out.println("--- MENGHAPUS PRODUK ---");
        cart.removeProduct(p1);
        cart.printCart();

        // Menambah lagi
        System.out.println("--- MENAMBAH PRODUK LAGI ---");
        cart.addProduct(p4);
        cart.printCart();

        System.out.println("Total item dalam keranjang: " + cart.getItemCount());
    }

    // Demo menggunakan HashMap
    private static void demoShoppingCartMap(Product p1, Product p2, Product p3, Product p4) {
        ShoppingCartMap cartMap = new ShoppingCartMap();

        // Menambah produk
        System.out.println("\n--- MENAMBAH PRODUK ---");
        cartMap.addProduct(p1);
        cartMap.addProduct(p2);
        cartMap.addProduct(p3);
        cartMap.addProduct(p1); // Otomatis qty bertambah
        cartMap.addProduct(p1); // Qty bertambah lagi
        cartMap.addProduct(p2); // Qty p2 bertambah

        // Menampilkan isi keranjang
        cartMap.printCart();

        // Menghapus 1 qty produk
        System.out.println("--- MENGHAPUS 1 QTY PRODUK ---");
        cartMap.removeProduct(p1);
        cartMap.printCart();

        // Menambah produk baru
        System.out.println("--- MENAMBAH PRODUK BARU ---");
        cartMap.addProduct(p4);
        cartMap.addProduct(p4);
        cartMap.printCart();

        // Menghapus semua qty produk tertentu
        System.out.println("--- MENGHAPUS SEMUA QTY PRODUK BERAS ---");
        cartMap.removeProductAll(p1);
        cartMap.printCart();

        System.out.println("Total item dalam keranjang: " + cartMap.getTotalItems());
    }
}