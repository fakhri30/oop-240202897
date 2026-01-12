<<<<<<< HEAD
package com.upb.agripos;

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) { 
        items.add(p);
        System.out.println("✓ Produk ditambahkan: " + p.getName());
    }
    
    public void removeProduct(Product p) { 
        if (items.remove(p)) {
            System.out.println("✓ Produk dihapus: " + p.getName());
        } else {
            System.out.println("✗ Produk tidak ditemukan di keranjang");
        }
    }

    public double getTotal() {
        double sum = 0;
        for (Product p : items) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void printCart() {
        System.out.println("\n=================================");
        System.out.println("       ISI KERANJANG BELANJA     ");
        System.out.println("=================================");
        
        if (items.isEmpty()) {
            System.out.println("Keranjang kosong");
        } else {
            int no = 1;
            for (Product p : items) {
                System.out.printf("%d. %s %s = Rp %.2f%n", 
                    no++, p.getCode(), p.getName(), p.getPrice());
            }
            System.out.println("---------------------------------");
            System.out.printf("TOTAL: Rp %.2f%n", getTotal());
        }
        System.out.println("=================================\n");
    }
    
    public int getItemCount() {
        return items.size();
    }
=======
package com.upb.agripos;

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) { 
        items.add(p);
        System.out.println("✓ Produk ditambahkan: " + p.getName());
    }

    public void removeProduct(Product p) { 
        if (items.remove(p)) {
            System.out.println("✓ Produk dihapus: " + p.getName());
        } else {
            System.out.println("✗ Produk tidak ditemukan: " + p.getName());
        }
    }

    public double getTotal() {
        double sum = 0;
        for (Product p : items) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void printCart() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ISI KERANJANG BELANJA (ArrayList)");
        System.out.println("=".repeat(50));
        
        if (items.isEmpty()) {
            System.out.println("Keranjang masih kosong.");
        } else {
            int no = 1;
            for (Product p : items) {
                System.out.printf("%d. %-10s %-20s Rp %,.2f\n", 
                    no++, p.getCode(), p.getName(), p.getPrice());
            }
            System.out.println("-".repeat(50));
            System.out.printf("TOTAL: Rp %,.2f\n", getTotal());
        }
        System.out.println("=".repeat(50) + "\n");
    }

    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
        System.out.println("✓ Keranjang telah dikosongkan");
    }
>>>>>>> 3050c71770b96b451df6c63f96dec7cb1263e3f6
}