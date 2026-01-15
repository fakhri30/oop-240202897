-- SQL Schema untuk Week 11 - DAO dan CRUD Database
-- Database: agripos

-- 1. Membuat database (jalankan sebagai superuser)
-- CREATE DATABASE agripos;

-- 2. Koneksi ke database agripos
-- \c agripos

-- 3. Drop table jika sudah ada (untuk testing ulang)
DROP TABLE IF EXISTS products;

-- 4. Membuat tabel products
CREATE TABLE products (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION NOT NULL CHECK (price >= 0),
    stock INT NOT NULL CHECK (stock >= 0)
);

-- 5. Menambahkan index untuk performa query
CREATE INDEX idx_product_name ON products(name);

-- 6. Insert sample data (opsional, untuk testing awal)
INSERT INTO products (code, name, price, stock) VALUES
    ('P001', 'Pupuk Organik', 25000, 10),
    ('P002', 'Pestisida Alami', 35000, 15),
    ('P003', 'Bibit Padi Unggul', 50000, 20),
    ('P004', 'Herbisida Organik', 40000, 12),
    ('P005', 'Pupuk NPK', 45000, 25);

-- 7. Verifikasi data
SELECT * FROM products ORDER BY code;

-- 8. Query untuk melihat struktur tabel
\d products

-- 9. Query untuk menghitung total produk
SELECT COUNT(*) as total_products FROM products;

-- 10. Query untuk menghitung total nilai stok
SELECT SUM(price * stock) as total_inventory_value FROM products;

-- Catatan:
-- Pastikan PostgreSQL sudah terinstall dan berjalan
-- Username default: postgres
-- Password: sesuaikan dengan instalasi Anda (contoh: 1234)
-- Port default: 5432