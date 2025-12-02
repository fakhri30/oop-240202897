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
## Hasil Secrenshoot
[<img width="1172" height="941" alt="oopactivtydiagram drawio" src="https://github.com/user-attachments/assets/5e33839a-5167-421a-b120-595b234264c3" />
]
[<img width="654" height="1031" alt="oop usecase drawio" src="https://github.com/user-attachments/assets/582b126a-efcf-4165-a8d3-6ca7772ab41c" />
]
[<img width="2320" height="1590" alt="oop sequence drawio" src="https://github.com/user-attachments/assets/b62f8cfb-0e34-440a-bc97-3577828e63ae" />
]
[<img width="2171" height="1430" alt="ClassDiagram drawio" src="https://github.com/user-attachments/assets/9368855d-0824-49b8-bfc8-7c47b5e9ae60" />
]
##** Analisis**

Kode menerapkan Dependency Inversion Principle dengan membuat abstraksi PaymentMethod.
Sistem mudah diperluas (Open/Closed Principle) karena untuk menambah metode pembayaran baru hanya perlu menambah class implementasi.
Diagram UML membantu melihat hubungan antar komponen secara lebih jelas sebelum implementasi.
Kendala yang muncul: menjaga konsistensi penamaan antar diagram; berhasil diatasi dengan revisi bertahap pada iterasi commit.

## **Kesimpulan**

Dengan menerapkan UML dan SOLID, desain arsitektur sistem menjadi lebih modular, mudah dikembangkan, dan maintainable.
Diagram UML membantu melihat struktur sistem sebelum implementasi sehingga meminimalkan kesalahan desain.
Penerapan SOLID terbukti mempermudah modifikasi pada fitur pembayaran dan modul lainnya.

##** Quiz**

1. **Jelaskan perbedaan aggregation dan composition serta berikan contoh penerapannya pada desain Anda.**
Jawaban:

-Aggregation adalah hubungan “whole–part” yang longgar; objek part tetap bisa ada tanpa whole.
-Contoh: Order memiliki banyak Product.

-Composition adalah hubungan “whole–part” yang kuat; objek part tidak ada tanpa whole.
-Contoh: Receipt memiliki ReceiptItem yang hanya hidup selama struk ada.

2. Bagaimana prinsip Open/Closed dapat memastikan sistem mudah dikembangkan?
Jawaban:
OCP menyatakan bahwa class harus open for extension tetapi closed for modification.
Artinya fitur baru dapat ditambahkan tanpa mengubah kode lama, sehingga mencegah bug dan meningkatkan stabilitas.

3. Mengapa Dependency Inversion Principle (DIP) meningkatkan testability? Berikan contoh penerapannya.
Jawaban:
DIP membuat modul tingkat tinggi bergantung pada abstraksi, bukan implementasi konkret.
Ini memungkinkan penggunaan mock object untuk pengujian.
Contoh: PaymentService menggunakan interface PaymentMethod, sehingga mudah diuji dengan mock tanpa sistem pembayaran asli.




