package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        // GANTI dengan Nama dan NIM Anda
        System.out.println("Hello, I am [Fakhri Fahmi Ramadan]-[240202897] (Week7)");
        System.out.println("===========================================\n");

        // Membuat produk-produk
        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);
        Product p3 = new Product("P03", "Bibit Jagung", 25000);
        Product p4 = new Product("P04", "Pestisida", 45000);

        // ========================================
        // DEMO 1: Keranjang dengan ArrayList
        // ========================================
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║   DEMO 1: ARRAYLIST CART          ║");
        System.out.println("╚═══════════════════════════════════╝\n");
        
        ShoppingCart cart = new ShoppingCart();
        
        // Menambahkan produk
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);
        cart.printCart();

        // Menghapus produk
        cart.removeProduct(p1);
        cart.printCart();

        // Menambah produk lagi
        cart.addProduct(p4);
        cart.printCart();

        // ========================================
        // DEMO 2: Keranjang dengan HashMap
        // ========================================
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║   DEMO 2: HASHMAP CART            ║");
        System.out.println("╚═══════════════════════════════════╝\n");
        
        ShoppingCartMap cartMap = new ShoppingCartMap();
        
        // Menambahkan produk (bisa duplikat, qty akan bertambah)
        cartMap.addProduct(p1);
        cartMap.addProduct(p2);
        cartMap.addProduct(p1); // Menambah qty p1
        cartMap.addProduct(p1); // Menambah qty p1 lagi
        cartMap.addProduct(p3);
        cartMap.printCart();

        // Menghapus produk (qty berkurang)
        cartMap.removeProduct(p1); // Qty p1 berkurang
        cartMap.printCart();

        // Menghapus lagi
        cartMap.removeProduct(p1); // Qty p1 berkurang lagi
        cartMap.printCart();

        // Menambah produk baru
        cartMap.addProduct(p4);
        cartMap.addProduct(p4);
        cartMap.printCart();

        // ========================================
        // PERBANDINGAN
        // ========================================
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║         PERBANDINGAN              ║");
        System.out.println("╚═══════════════════════════════════╝");
        System.out.println("ArrayList Cart:");
        System.out.println("- Jumlah item: " + cart.getItemCount());
        System.out.println("- Total: Rp " + cart.getTotal());
        System.out.println("\nHashMap Cart:");
        System.out.println("- Jumlah item: " + cartMap.getTotalItems());
        System.out.println("- Total: Rp " + cartMap.getTotal());
        System.out.println("\n===========================================");
        System.out.println("Program selesai!");
    }
}