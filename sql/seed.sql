-- Seed data untuk Agri-POS System (Week 14 - Integrasi Individu)

-- Insert sample users (Admin and Cashier)
INSERT INTO users (username, password, full_name, role) VALUES
('admin', 'password', 'Administrator', 'ADMIN'),
('kasir', 'password', 'Cashier User', 'KASIR');

-- Insert sample products
INSERT INTO products (code, name, price, stock) VALUES
('PRD001', 'Benih Padi Premium', 50000.00, 100),
('PRD002', 'Pupuk Urea 50kg', 75000.00, 50),
('PRD003', 'Pestisida Organik 1L', 45000.00, 30),
('PRD004', 'Alat Pertanian - Cangkul', 85000.00, 15),
('PRD005', 'Alat Pertanian - Sabit', 35000.00, 25),
('PRD006', 'Benih Jagung Hibrida', 60000.00, 80),
('PRD007', 'Pupuk NPK Balanced', 95000.00, 40),
('PRD008', 'Fungisida Sistemik 500ml', 65000.00, 20);
