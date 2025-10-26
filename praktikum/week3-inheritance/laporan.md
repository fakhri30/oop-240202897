# Laporan Praktikum Minggu 3
Topik: Inheritance (Kategori Produk)

## Identitas
- Nama  : Fakhri Fahmi Ramadan
- NIM   : 240202897
- Kelas : 3IKRB

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep inheritance (pewarisan class)** dalam OOP.  
- Mahasiswa mampu **membuat superclass dan subclass** untuk produk pertanian.  
- Mahasiswa mampu **mendemonstrasikan hierarki class** melalui contoh kode.  
- Mahasiswa mampu **menggunakan `super` untuk memanggil konstruktor dan method parent class**.  
- Mahasiswa mampu **membuat laporan praktikum** yang menjelaskan perbedaan penggunaan inheritance dibanding class tunggal.  

---

## Dasar Teori
Inheritance adalah mekanisme dalam OOP yang memungkinkan suatu class mewarisi atribut dan method dari class lain.  
- **Superclass**: class induk yang mendefinisikan atribut umum.  
- **Subclass**: class turunan yang mewarisi atribut/method superclass, dan dapat menambahkan atribut/method baru.  
- `super` digunakan untuk memanggil konstruktor atau method superclass.  

---

## Langkah Praktikum
1. **Membuat Superclass Produk**  
   - Gunakan class `Produk` dari Bab 2 sebagai superclass.  

2. **Membuat Subclass**  
   - `Benih.java` → atribut tambahan: varietas.  
   - `Pupuk.java` → atribut tambahan: jenis pupuk (Urea, NPK, dll).  
   - `AlatPertanian.java` → atribut tambahan: material (baja, kayu, plastik).  

3. **Membuat Main Class**  
   - Instansiasi minimal satu objek dari tiap subclass.  
   - Tampilkan data produk dengan memanfaatkan inheritance.  

4. **Menambahkan CreditBy**  
   - Panggil class `CreditBy` untuk menampilkan identitas mahasiswa.  

5. **Commit dan Push**  
   - Commit dengan pesan: `week3-inheritance`.  

---

## Kode Program
```java
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja");

        System.out.println("------------------------------------");
        System.out.println(b.deskripsi());

        System.out.println("------------------------------------");
        System.out.println(p.deskripsi());
        
        System.out.println("------------------------------------");
        System.out.println(a.deskripsi());

        CreditBy.print("240202897", "Fakhri Fahmi Ramadan");
```
---

## Hasil Eksekusi
![Screenshot hasil]<img width="946" height="354" alt="Screenshot 2025-10-26 013309" src="https://github.com/user-attachments/assets/894a3488-8c04-4350-b54a-e5a48b7630f2" />

---

## Analisis
- Ketiga subclass (`Benih`, `Pupuk`, dan `AlatPertanian`) **mewarisi atribut dan method** dari superclass `Produk`.  
- Setiap subclass menambahkan **atribut unik** yang relevan dengan jenis produknya.  
- Method `deskripsi()` menampilkan informasi lengkap dengan memanfaatkan pewarisan.  
- Dibanding minggu sebelumnya (tanpa inheritance), kode lebih **efisien**, **terstruktur**, dan **mudah dikembangkan**. 
---

## Kesimpulan
- Inheritance membuat program lebih terorganisir dan menghindari duplikasi kode.  
- Subclass dapat memperluas fungsionalitas superclass tanpa perlu menulis ulang atribut dan method dasar.  
- Praktikum ini memperkuat pemahaman konsep **reusability dan extensibility** dalam OOP.  
---

## Quiz
1. **Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?**  
   **Jawaban:Keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan sangat signifikan dalam pengembangan perangkat lunak yang efisien dan terstruktu**  

2. **Bagaimana cara subclass memanggil konstruktor superclass?**  
   **Jawaban:Subclass dapat memanggil konstruktor superclass dengan menggunakan fungsi *super()*. Cara Memanggil Konstruktor Superclass

Gunakan super().__init__(...) di dalam konstruktor subclass untuk memanggil konstruktor dari superclass.
** 

3. **Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.**  

   **Jawaban:Contoh Subclass Tambahan dalam POS Pertanian
    SubclassDeskripsi Hasil Panen Produk seperti beras, jagung, kedelai, sayuran, buah-buahan yang dijual langsung. Pakan Ternak Produk seperti konsentrat, dedak, jagung giling, atau           rumput fermentasi. Obat Hewan Produk untuk kesehatan ternak seperti vitamin, vaksin, dan antibiotik. Media Tanam Produk seperti tanah humus, cocopeat, sekam bakar, dan kompos.              Perlengkapan Peternakan Seperti kandang, tempat minum, alat pemerah susu, dan timbangan hewan. Produk Olahan Hasil olahan pertanian seperti keripik singkong, jus buah, atau pupuk cair      organik. Bibit Ternak Anak ayam, kambing, sapi, atau ikan yang dijual sebagai bibit ternak** 
