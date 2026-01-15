package com.upb.agripos.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model class representing a shopping cart
 * Uses Collections (List and Map) as per Bab 7
 */
public class Cart {
    private List<CartItem> items;
    private Map<String, Integer> productQuantityMap;

    public Cart() {
        this.items = new ArrayList<>();
        this.productQuantityMap = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        String code = product.getCode();
        
        if (productQuantityMap.containsKey(code)) {
            // Update existing item
            int newQuantity = productQuantityMap.get(code) + quantity;
            productQuantityMap.put(code, newQuantity);
            
            // Update in list
            for (CartItem item : items) {
                if (item.getProduct().getCode().equals(code)) {
                    item.setQuantity(newQuantity);
                    break;
                }
            }
        } else {
            // Add new item
            CartItem newItem = new CartItem(product, quantity);
            items.add(newItem);
            productQuantityMap.put(code, quantity);
        }
    }

    public void removeItem(String productCode) {
        items.removeIf(item -> item.getProduct().getCode().equals(productCode));
        productQuantityMap.remove(productCode);
    }

    public void clear() {
        items.clear();
        productQuantityMap.clear();
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    public int getTotalItems() {
        return productQuantityMap.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}