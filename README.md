# Pemrograman Berbasis Desktop — Kumpulan Tugas (Restoran Sederhana)

Repository ini berisi tiga tugas praktikum untuk mata kuliah **Pemrograman Berbasis Desktop** dengan studi kasus aplikasi restoran makanan. Masing‑masing tugas memiliki tujuan pembelajaran yang meningkat dari program console sederhana ke aplikasi berorientasi objek (OOP) dengan penyimpanan file dan GUI/struktur manajemen.

---

# Ringkasan Tugas

### Tugas 1 — Aplikasi Console: Pemesanan Maks. 4 Item

**Tujuan:** membuat aplikasi Java console sederhana untuk menerima pesanan maksimum 4 menu.

**Fitur wajib:**

- Data menu (nama, harga, kategori) disimpan di array pada program.
- Menampilkan daftar menu sebelum pemesanan.
- Pelanggan dapat memilih maksimal 4 item (contoh input: `Nasi Padang = 2`).
- Hitung subtotal setiap item (harga × jumlah).
- Hitung total pesanan ditambah:

  - **Pajak 10%** dari total biaya pesanan.
  - **Biaya pelayanan Rp 20.000**.

- Diskon / penawaran:

  - **Diskon 10%** jika total biaya keseluruhan (sebelum pajak & service) > Rp 100.000.
  - **Promo Beli 1 Gratis 1** pada salah satu kategori **minuman** bila total pesanan (sebelum pajak & service) > Rp 50.000. (Terapkan pada item minuman termurah yang dipesan atau sesuai aturan yang ditetapkan.)

- Mencetak struk yang mencantumkan: item, jumlah, harga per item, total per item, subtotal, potongan diskon/penawaran jika ada, pajak (dengan besar pajak), biaya pelayanan, dan total akhir.

**Catatan implementasi:**

- Batasi input user maksimal 4 item; jika melebihi, informasikan dan hentikan input.
- Validasi input nama menu; jika tidak ditemukan, minta input ulang atau tampilkan pesan error.

---

### Tugas 2 — Aplikasi Console: Pemesanan Tanpa Batas + Manajemen Menu

**Tujuan:** memperluas aplikasi agar pesanan tidak terbatas hingga user mengetik `selesai`, serta menambahkan menu manajemen untuk pemilik.

**Fitur wajib (selain semua fitur Tugas 1):**

- Pemesanan tak terbatas: user memasukkan item hingga menulis `selesai`.
- Jika input di luar menu, program terus meminta input valid.
- **Menu Pengelolaan (Admin/Owner):**

  - Tambah menu baru (nama, kategori, harga).
  - Ubah harga (tampilkan daftar menu dengan nomor; user pilih nomor; sebelum eksekusi akan ada konfirmasi `Ya` / `Tidak`).
  - Hapus menu (tampilkan daftar; pilih nomor; konfirmasi `Ya` / `Tidak`).
  - Navigasi dapat kembali ke menu parent sebelumnya.

- Setelah operasi tambah/ubah/hapus berhasil, tampilkan daftar menu terbaru.

**Catatan implementasi:**

- Pisahkan opsi di menu utama: `1) Pesan  2) Kelola Menu  3) Keluar`.
- Implementasikan loop yang menangani validasi input untuk menghindari crash saat input tipe yang salah.

---

### Tugas 3 — Pemrograman Berbasis Objek: Manajemen Restoran + File I/O

**Tujuan:** membuat aplikasi OOP lengkap untuk manajemen restoran, menerapkan inheritance, encapsulation, exception handling, file I/O, dan struktur menu yang modular.

**Desain Kelas (kewajiban):**

1. `abstract class MenuItem` (atribut: `nama:String`, `harga:double`, `kategori:String`) dengan metode abstrak `void tampilMenu()`.
2. Subkelas `Makanan` dan `Minuman` yang mewarisi `MenuItem`. Tambahkan atribut spesifik jika perlu (misal `jenisMakanan`, `ukuran`, dsb.). Implementasikan `tampilMenu()` untuk menampilkan info spesifik.
3. Subkelas `Diskon` (turunan `MenuItem`) yang menyimpan `diskon: double` (misal 0.10 untuk 10%). Implementasikan `tampilMenu()` untuk menunjukkan promosi/diskon.
4. `class Menu` memiliki `ArrayList<MenuItem>` untuk menyimpan item, serta method untuk menambah, menghapus, mengubah, dan memuat/menyimpan dari file (`menu.txt`).
5. `class Pesanan` menyimpan daftar item pesanan (bisa `ArrayList<PesananItem>`), hitung subtotal, diskon, pajak, dsb.

**Fitur wajib (selain Tugas 1 & 2):**

- Operasi tambah/ubah/hapus menu via objek `Menu`.
- Simpan & muat `menu` dari file teks (misal `menu_restoran.txt`) saat program mulai/keluar.
- Simpan struk pesanan ke file teks (misal `struk_pesanan.txt`).
- Tangani exception seperti akses index di luar batas, parsing number, I/O exceptions.
- Menu utama harus menyediakan: Tambah Item, Tampilkan Menu, Terima Pesanan, Cetak Struk, Simpan/Muat Data, Keluar.
