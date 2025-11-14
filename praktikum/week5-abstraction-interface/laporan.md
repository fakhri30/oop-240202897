# Laporan Praktikum Minggu 5 
Topik: Abstraction-Interface

## Identitas
- Nama  : Fakhri Fahmi Ramadan
- NIM   : 240202897
- Kelas : 3IKRB

---

## Tujuan
- Mahasiswa mampu **menjelaskan perbedaan abstract class dan interface**.
- Mahasiswa mampu **mendesain abstract class dengan method abstrak** sesuai kebutuhan kasus.
- Mahasiswa mampu **membuat interface dan mengimplementasikannya pada class**.
- Mahasiswa mampu **menerapkan multiple inheritance melalui interface** pada rancangan kelas.
- Mahasiswa mampu **mendokumentasikan kode** (komentar kelas/method, README singkat pada folder minggu).


---

## Dasar Teori
**Abstraksi** adalah proses menyederhanakan kompleksitas dengan menampilkan elemen penting dan menyembunyikan detail implementasi.
- **Abstract class**: tidak dapat diinstansiasi, dapat memiliki method abstrak (tanpa badan) dan non-abstrak. Dapat menyimpan state (field).
- **Interface**: kumpulan kontrak (method tanpa implementasi konkret). Sejak Java 8 mendukung default method. Mendukung **multiple inheritance** (class dapat mengimplementasikan banyak interface).
- Gunakan **abstract class** bila ada _shared state_ dan perilaku dasar; gunakan **interface** untuk mendefinisikan kemampuan/kontrak lintas hierarki.

Dalam konteks Agri-POS, **Pembayaran** dapat dimodelkan sebagai abstract class dengan method abstrak `prosesPembayaran()` dan `biaya()`. Implementasi konkritnya: `Cash` dan `EWallet`. Kemudian, interface seperti `Validatable` (mis. verifikasi OTP) dan `Receiptable` (mencetak bukti) dapat diimplementasikan oleh jenis pembayaran yang relevan.

---

## Langkah Praktikum
1. **Abstract Class – Pembayaran**
   - Buat `Pembayaran` (abstract) dengan field `invoiceNo`, `total` dan method:
     - `double biaya()` (abstrak) → biaya tambahan (fee).
     - `boolean prosesPembayaran()` (abstrak) → mengembalikan status berhasil/gagal.
     - `double totalBayar()` (konkrit) → `return total + biaya();`.

2. **Subclass Konkret**
   - `Cash` → biaya = 0, proses = selalu berhasil jika `tunai >= totalBayar()`.
   - `EWallet` → biaya = 1.5% dari `total`; proses = membutuhkan validasi.

3. **Interface**
   - `Validatable` → `boolean validasi();` (contoh: OTP).
   - `Receiptable` → `String cetakStruk();`

4. **Multiple Inheritance via Interface**
   - `EWallet` mengimplementasikan **dua interface**: `Validatable`, `Receiptable`.
   - `Cash` setidaknya mengimplementasikan `Receiptable`.

5. **Main Class**
    - Buat `MainAbstraction.java` untuk mendemonstrasikan pemakaian `Pembayaran` (polimorfik).
    - Tampilkan hasil proses dan struk. Di akhir, panggil `CreditBy.print("[NIM]", "[Nama]")`.

6. **Commit dan Push**
   - Commit dengan pesan: `week5-abstraction-interface`.

---

## Kode Program
 
```java package main.java.com.upb.agripos;

import main.java.com.upb.agripos.util.CreditBy;
import main.java.com.upb.agripos.model.kontrak.Receiptable;
import main.java.com.upb.agripos.model.pembayaran.Cash;
import main.java.com.upb.agripos.model.pembayaran.EWallet;
import main.java.com.upb.agripos.model.pembayaran.Pembayaran;
import main.java.com.upb.agripos.model.pembayaran.TransferBank;

public class MainAbstraction {
    public static void main(String[] args) {



        System.out.println("=== WEEK 5: ABSTRACTION INTERFACE ===");
        System.out.println("\n----------------------------------------------------------------------------------------------\n");
        
        // --- 1. CASH (Tunai Cukup) ---
        Pembayaran cash = new Cash("INV-C01", 100000, 120000);
        System.out.println("=== PEMBAYARAN CASH ===");
        cash.prosesPembayaran(); // Memproses
        System.out.println(((Receiptable) cash).cetakStruk()); // Mencetak Struk
        System.out.println("-----------------------------------------------------------------------------------------------");
        // --- 2. E-WALLET (Validasi Berhasil) ---
        Pembayaran ew = new EWallet("INV-E01", 150000, "Fakhri@ewallet", "100801");
        System.out.println("=== PEMBAYARAN E-WALLET ===");
        ew.prosesPembayaran(); // Memanggil Validasi
        System.out.println(((Receiptable) ew).cetakStruk()); // Mencetak Struk
        System.out.println("-----------------------------------------------------------------------------------------------");
        // --- 3. TRANSFER BANK (Validasi Berhasil) ---
        // Biaya: Rp3.500,00
        Pembayaran transfer = new TransferBank("INV-T01", 75000, "999"); 
        System.out.println("=== PEMBAYARAN TRANSFER BANK ===");
        transfer.prosesPembayaran(); // Memanggil Validasi
        System.out.println(((Receiptable) transfer).cetakStruk());
        System.out.println("-----------------------------------------------------------------------------------------------");
        // --- 4. TRANSFER BANK (Validasi GAGAL) ---
        Pembayaran transferGagal = new TransferBank("INV-T02", 5000, "BRI-9999"); 
        System.out.println("=== TRANSFER BANK (GAGAL) ===");
        transferGagal.prosesPembayaran(); 
        System.out.println(((Receiptable) transferGagal).cetakStruk());

        
        // --- 3 - Panggilan CreditBy
        CreditBy.print("240202897", "Fakhri Fahmi Ramadan");
    }
}
```
---

## Hasil Eksekusi
<img width="980" height="437" alt="Screenshot 2025-11-14 020557" src="https://github.com/user-attachments/assets/bb31b984-fd85-48e9-aaf6-d52011b98483" />

---

## Analisis

1. Program dimulai dari `MainAbstraction.java`.
2. Tiga jenis pembayaran dibuat:
   - `Cash` → pembayaran tunai tanpa biaya tambahan.  
   - `EWallet` → pembayaran digital dengan biaya 1,5% dan validasi OTP.  
   - `TransferBank` → pembayaran melalui bank dengan biaya tetap Rp3.500 dan validasi kode bank.
3. Semua objek bertipe induk `Pembayaran`, menunjukkan **polimorfisme**.
4. Setiap objek memanggil method `prosesPembayaran()` dan `cetakStruk()` (dari interface `Receiptable`).
5. Output menampilkan hasil transaksi dan status berhasil/gagal.
6. Di akhir program, dipanggil `CreditBy.print()` untuk menampilkan identitas mahasiswa.

---

##  Perbedaan dengan Minggu Sebelumnya (Polymorphism)

| Aspek | Minggu Sebelumnya (Polymorphism) | Minggu Ini (Abstraction & Interface) |
|--------|-----------------------------------|--------------------------------------|
| **Konsep utama** | Fokus pada perilaku berbeda antar subclass melalui overriding. | Fokus pada mendesain *blueprint* dan *kontrak* perilaku. |
| **Struktur kode** | Hanya menggunakan class biasa. | Menggunakan `abstract class` dan `interface`. |
| **Tujuan** | Menunjukkan objek berbeda bisa dipanggil dengan referensi sama. | Memisahkan definisi (abstraksi) dan implementasi agar lebih fleksibel. |
| **Pewarisan** | Tunggal. | Dapat multiple via interface. |
| **Contoh di program** | Semua method diimplementasikan langsung di subclass. | `Pembayaran` hanya mendefinisikan struktur, implementasi di subclass (`Cash`, `EWallet`, `TransferBank`). |

Singkatnya:  
> Polymorphism → menekankan *bagaimana objek berperilaku berbeda.*  
> Abstraction → menekankan *bagaimana desain dibuat fleksibel dan mudah dikembangkan.*

---

##  Kendala dan Solusi

| Kendala | Penyebab | Solusi |
|----------|-----------|--------|
| **Error “cannot be resolved to a type”** | Struktur `package` tidak sesuai dengan folder. | Pastikan direktori sesuai `com/upb/agripos/...` dan `package` di atas file benar. |
| **“Activating task providers java” di VS Code** | Java Extension Pack belum aktif atau workspace belum terdeteksi. | Jalankan `Java: Clean Java Language Server Workspace` dan pastikan JDK terinstall. |
| **Variabel tidak dikenal (`transferBerhhasil`)** | Salah penamaan variabel. | Koreksi nama variabel agar konsisten (`transfer`). |
| **Validasi gagal padahal ingin berhasil** | Kode bank/OTP tidak sesuai logika validasi. | Ubah input agar memenuhi syarat validasi. |

---

##  Checklist Keberhasilan

- [x] `Pembayaran` memiliki method abstrak dan konkrit.  
- [x] `Cash`, `EWallet`, `TransferBank` mengimplementasikan method abstrak dengan benar.  
- [x] Interface `Validatable` dan `Receiptable` diimplementasikan sesuai kebutuhan.  
- [x] Multiple inheritance via interface diterapkan.  
- [x] Output menampilkan status pembayaran dan struk.  
- [x] CreditBy mencantumkan identitas mahasiswa.  
- [x] Screenshot hasil program disertakan di folder `screenshots/hasil.png`.

---

##  Kesimpulan

Pada praktikum minggu ini, konsep **Abstraction & Interface** berhasil diterapkan untuk membuat sistem pembayaran yang terstruktur dan fleksibel.  
Penggunaan *abstract class* membantu membentuk kerangka umum (`Pembayaran`), sementara *interface* memberikan kemampuan tambahan yang bisa diterapkan lintas kelas (`Validatable`, `Receiptable`).  
Dengan pendekatan ini, sistem menjadi lebih mudah dikembangkan di masa depan tanpa mengubah struktur utama.

---

##  Referensi

- Liang, Y. D. *Introduction to Java Programming* (Bab 14)  
- Horstmann, C. S. *Core Java Volume I – Fundamentals* (Bab 6)  
- Oracle Docs: *Abstract Classes and Interfaces*

---

 ##  Quiz

1. **Jelaskan perbedaan konsep dan penggunaan abstract class dan interface.**  
   **Jawaban:**  
   - **Abstract class** digunakan untuk mendefinisikan kerangka dasar dari suatu kelompok objek yang memiliki kesamaan perilaku dan atribut.  
     - Dapat memiliki *field* (state), *method abstrak*, dan *method konkrit*.  
     - Digunakan ketika beberapa subclass memiliki logika dasar yang sama.  
   - **Interface** digunakan untuk mendefinisikan kontrak perilaku tanpa mengatur bagaimana implementasinya dilakukan.  
     - Tidak menyimpan data (sebelum Java 8).  
     - Class dapat mengimplementasikan lebih dari satu interface.  

    **Kesimpulan:**  
   Gunakan **abstract class** ketika butuh dasar umum dengan perilaku/atribut yang dapat diwarisi,  
   dan gunakan **interface** ketika hanya ingin mendefinisikan perilaku wajib tanpa hubungan hierarki langsung.

---

2. **Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java?**  
   **Jawaban:**  
   Karena **interface tidak membawa state atau implementasi konkret** yang bisa menimbulkan konflik pewarisan.  
   Jika Java mengizinkan pewarisan ganda antar class, bisa muncul **ambiguity problem** (dua superclass memiliki method sama dengan implementasi berbeda).  

   Dengan interface:
   - Semua method bersifat abstrak atau default tanpa state,  
   - Tidak ada konflik data antar interface,  
   - Pewarisan ganda menjadi **aman dan terkontrol**.  

    **Kesimpulan:**  
   Java hanya mengizinkan pewarisan ganda melalui interface, bukan class, agar terhindar dari konflik implementasi dan menjaga konsistensi desain.

---

3. **Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.**  
   **Jawaban:**  
   - **Abstract Class:** `Pembayaran`  
     - Karena semua jenis pembayaran (`Cash`, `EWallet`, `TransferBank`) memiliki struktur dan data dasar yang sama (`invoiceNo`, `total`, `biaya()`, `prosesPembayaran()`), tetapi tiap jenis memiliki implementasi berbeda.  
     - `Pembayaran` cocok menjadi *abstract class* sebagai blueprint dasar.  
   - **Interface:** `Validatable` dan `Receiptable`  
     - `Validatable` mendefinisikan kontrak untuk validasi seperti OTP atau kode bank. Tidak semua pembayaran memerlukannya.  
     - `Receiptable` mendefinisikan kontrak untuk mencetak struk, yang bisa diterapkan pada semua metode pembayaran.  
     - Interface memungkinkan *multiple inheritance* tanpa konflik.

    **Kesimpulan:**  
   - `Pembayaran` → *abstract class* (struktur umum).  
   - `Validatable`, `Receiptable` → *interface* (kemampuan tambahan lintas kelas).

