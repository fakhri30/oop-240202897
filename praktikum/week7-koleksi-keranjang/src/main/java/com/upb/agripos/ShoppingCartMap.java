package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartMap {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p) { 
        items.put(p, items.getOrDefault(p, 0) + 1);
        System.out.println("✓ Produk ditambahkan: " + p.getName() + 
                         " (Qty: " + items.get(p) + ")");
    }

    public void removeProduct(Product p) {
        if (!items.containsKey(p)) {
            System.out.println("✗ Produk tidak ditemukan: " + p.getName());
            return;
        }
        
        int qty = items.get(p);
        if (qty > 1) {
            items.put(p, qty - 1);
            System.out.println("✓ Qty dikurangi: " + p.getName() + 
                             " (Sisa: " + items.get(p) + ")");
        } else {
            items.remove(p);
            System.out.println("✓ Produk dihapus: " + p.getName());
        }
    }

    public void removeProductAll(Product p) {
        if (items.remove(p) != null) {
            System.out.println("✓ Semua " + p.getName() + " dihapus dari keranjang");
        } else {
            System.out.println("✗ Produk tidak ditemukan: " + p.getName());
        }
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ISI KERANJANG BELANJA (HashMap - dengan Qty)");
        System.out.println("=".repeat(50));
        
        if (items.isEmpty()) {
            System.out.println("Keranjang masih kosong.");
        } else {
            int no = 1;
            for (Map.Entry<Product, Integer> e : items.entrySet()) {
                Product p = e.getKey();
                int qty = e.getValue();
                double subtotal = p.getPrice() * qty;
                
                System.out.printf("%d. %-10s %-20s x%-3d Rp %,.2f\n", 
                    no++, p.getCode(), p.getName(), qty, subtotal);
            }
            System.out.println("-".repeat(50));
            System.out.printf("TOTAL: Rp %,.2f\n", getTotal());
        }
        System.out.println("=".repeat(50) + "\n");
    }

    public int getTotalItems() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void clear() {
        items.clear();
        System.out.println("✓ Keranjang telah dikosongkan");
    }
}
