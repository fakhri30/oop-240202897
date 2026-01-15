package com.upb.agripos.dao;

import java.sql.SQLException;
import java.util.List;

import com.upb.agripos.model.User;

/**
 * DAO interface for User operations
 * Implements Data Access Layer abstraction (Bab 11)
 */
public interface UserDAO {
    void insert(User user) throws SQLException;
    
    void update(User user) throws SQLException;
    
    void delete(int id) throws SQLException;
    
    User findByUsername(String username) throws SQLException;
    
    User findById(int id) throws SQLException;
    
    List<User> findAll() throws SQLException;
}
