package com.upb.agripos.dao;

import com.upb.agripos.model.User;
import com.upb.agripos.model.UserRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo/Mock DAO for testing without database
 * Contains hardcoded demo users for login testing
 */
public class DemoUserDAO implements UserDAO {
    private static final List<User> demoUsers = new ArrayList<>();

    static {
        // Initialize demo users
        demoUsers.add(new User(1, "admin", "password", "Administrator", UserRole.ADMIN));
        demoUsers.add(new User(2, "kasir", "password", "Cashier User", UserRole.KASIR));
        demoUsers.add(new User(3, "admin2", "admin123", "Admin 2", UserRole.ADMIN));
        demoUsers.add(new User(4, "kasir2", "kasir123", "Kasir 2", UserRole.KASIR));
    }

    @Override
    public void insert(User user) {
        user.setId(demoUsers.size() + 1);
        demoUsers.add(user);
        System.out.println("Demo: User added - " + user.getUsername());
    }

    @Override
    public void update(User user) {
        for (int i = 0; i < demoUsers.size(); i++) {
            if (demoUsers.get(i).getId() == user.getId()) {
                demoUsers.set(i, user);
                System.out.println("Demo: User updated - " + user.getUsername());
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        demoUsers.removeIf(user -> user.getId() == id);
        System.out.println("Demo: User deleted - ID " + id);
    }

    @Override
    public User findByUsername(String username) {
        System.out.println("Demo: Finding user by username - " + username);
        return demoUsers.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findById(int id) {
        return demoUsers.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(demoUsers);
    }
}
