# Laporan Praktikum Minggu 6
Topik: Desain Arsitektur Sistem dengan UML dan Prinsip SOLID

## Identitas
- Nama  : [Fakhri Fahmi Ramadan]
- NIM   : [240202897]
- Kelas : [3IKRB]

---

## Tujuan
- Mahasiswa memahami cara memetakan kebutuhan sistem ke dalam diagram UML.
- Mahasiswa mampu menggambar minimal empat diagram UML: Use Case, Activity, Sequence, dan Class Diagram.
- Mahasiswa dapat menjelaskan serta menerapkan prinsip SOLID dalam desain arsitektur perangkat lunak.
- Mahasiswa mampu membuat struktur dokumentasi dan repository yang sesuai standar.

---

## Dasar Teori
1. **UML (Unified Modeling Language)** adalah bahasa standar untuk memodelkan sistem perangkat lunak dalam bentuk diagram.
2. **Use Case Diagram** digunakan untuk menggambarkan interaksi antara aktor dan sistem.
3. **Activity Diagram** menjelaskan alur proses bisnis atau logika aktivitas.
4. **Sequence Diagram** memperlihatkan interaksi pesan antar objek seiring waktu.
5. **Class Diagram** menjelaskan struktur kelas beserta atribut, method, dan relasinya.
6. **SOLID Principles** merupakan lima aturan desain agar kode mudah dipelihara, diperluas, dan diuji.

---

## Langkah Praktikum
1. Menganalisis kebutuhan sistem Agri-POS berdasarkan Functional dan Non-Functional Requirements.
2. Menentukan aktor dan menyusun Use Case Diagram (versi awal).
3. Membuat Activity Diagram proses Checkout beserta skenario sukses dan gagal.
4. Menggambar Sequence Diagram untuk proses pembayaran (cash dan e-wallet).
5. Membuat Class Diagram lengkap dengan atribut, method, visibility, dan relasi.
6. Memetakan penerapan prinsip SOLID pada desain (SRP, OCP, DIP, LSP, ISP).
7. Melakukan commit iteratif menggunakan format:
8. 8. Menyimpan file diagram ke folder `docs/` dan file sumber ke folder `src/uml/`.

---

## Kode Program (Contoh Penerapan SOLID)
Contoh kode untuk prinsip DIP + OCP pada modul pembayaran:

```java
// Interface PaymentMethod (DIP, OCP)
public interface PaymentMethod {
 boolean pay(double amount);
}

// Implementasi CashPayment
public class CashPayment implements PaymentMethod {
 @Override
 public boolean pay(double amount) {
     System.out.println("Pembayaran tunai berhasil: " + amount);
     return true;
 }
}

// Implementasi EWalletPayment
public class EWalletPayment implements PaymentMethod {
 private double saldo;

 public EWalletPayment(double saldo) {
     this.saldo = saldo;
 }

 @Override
 public boolean pay(double amount) {
     if (saldo < amount) {
         System.out.println("Saldo tidak cukup!");
         return false;
     }
     saldo -= amount;
     System.out.println("Pembayaran e-wallet berhasil: " + amount);
     return true;
 }
}

// PaymentService menggunakan DIP
public class PaymentService {
 private PaymentMethod method;

 public PaymentService(PaymentMethod method) {
     this.method = method;
 }

 public void process(double amount) {
     method.pay(amount);
 }
}
---





