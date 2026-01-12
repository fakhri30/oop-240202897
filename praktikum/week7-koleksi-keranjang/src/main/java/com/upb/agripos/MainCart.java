<<<<<<< HEAD
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
=======
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
>>>>>>> 3050c71770b96b451df6c63f96dec7cb1263e3f6
}