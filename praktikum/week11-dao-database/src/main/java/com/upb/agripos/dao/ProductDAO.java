package com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.model.Product;

/**
 * Interface DAO untuk operasi CRUD Product
 * Memisahkan kontrak dari implementasi (Dependency Inversion Principle)
 */
public interface ProductDAO {
    
    /**
     * Menambahkan product baru ke database
     * @param product objek Product yang akan disimpan
     * @throws Exception jika terjadi error database
     */
    void insert(Product product) throws Exception;
    
    /**
     * Mencari product berdasarkan kode
     * @param code kode product
     * @return objek Product jika ditemukan, null jika tidak
     * @throws Exception jika terjadi error database
     */
    Product findByCode(String code) throws Exception;
    
    /**
     * Mengambil semua product dari database
     * @return List berisi semua product
     * @throws Exception jika terjadi error database
     */
    List<Product> findAll() throws Exception;
    
    /**
     * Mengupdate data product yang sudah ada
     * @param product objek Product dengan data baru
     * @throws Exception jika terjadi error database
     */
    void update(Product product) throws Exception;
    
    /**
     * Menghapus product berdasarkan kode
     * @param code kode product yang akan dihapus
     * @throws Exception jika terjadi error database
     */
    void delete(String code) throws Exception;
}