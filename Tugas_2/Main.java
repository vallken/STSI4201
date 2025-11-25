import java.util.Scanner;


public class Main {
    private static Menu[] daftarMenu = new Menu[50]; 
    private static int jumlahMenu = 0;
    private static Menu[] pesanan = new Menu[50];
    private static int[] jumlahPesanan = new int[50]; 
    private static int totalItemPesanan = 0;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        inisialisasiMenu();
        menuUtama();
    }
    
    private static void inisialisasiMenu() {
        daftarMenu[jumlahMenu++] = new Menu("Nasi Goreng Spesial", 25000, "makanan");
        daftarMenu[jumlahMenu++] = new Menu("Mie Goreng", 20000, "makanan");
        daftarMenu[jumlahMenu++] = new Menu("Ayam Bakar", 30000, "makanan");
        daftarMenu[jumlahMenu++] = new Menu("Sate Ayam", 28000, "makanan");
        daftarMenu[jumlahMenu++] = new Menu("Gado-Gado", 22000, "makanan");
        daftarMenu[jumlahMenu++] = new Menu("Nasi Uduk", 18000, "makanan");
        
        daftarMenu[jumlahMenu++] = new Menu("Es Teh Manis", 5000, "minuman");
        daftarMenu[jumlahMenu++] = new Menu("Es Jeruk", 7000, "minuman");
        daftarMenu[jumlahMenu++] = new Menu("Jus Alpukat", 12000, "minuman");
        daftarMenu[jumlahMenu++] = new Menu("Kopi Hitam", 10000, "minuman");
        daftarMenu[jumlahMenu++] = new Menu("Air Mineral", 3000, "minuman");
    }
    
    private static void menuUtama() {
        boolean running = true;
        
        while (running) {
            System.out.println("\n========================================");
            System.out.println("   SELAMAT DATANG DI RESTORAN KAMI");
            System.out.println("========================================");
            System.out.println("1. Menu Pelanggan (Pemesanan)");
            System.out.println("2. Menu Pemilik (Pengelolaan Menu)");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            
            String pilihan = scanner.nextLine().trim();
            
            if (pilihan.equals("1")) {
                menuPelanggan();
            } else if (pilihan.equals("2")) {
                menuPemilik();
            } else if (pilihan.equals("3")) {
                System.out.println("\nTerima kasih telah menggunakan aplikasi kami!");
                running = false;
            } else {
                System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        }
    }
    
    private static void tampilkanDaftarMenu() {
        System.out.println("\n========================================");
        System.out.println("           DAFTAR MENU");
        System.out.println("========================================");
        
        System.out.println("\n>>> MAKANAN <<<");
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equals("makanan")) {
                System.out.printf("%d. %-25s Rp %,.0f\n", 
                    i + 1, daftarMenu[i].getNama(), daftarMenu[i].getHarga());
            }
        }
        
        System.out.println("\n>>> MINUMAN <<<");
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equals("minuman")) {
                System.out.printf("%d. %-25s Rp %,.0f\n", 
                    i + 1, daftarMenu[i].getNama(), daftarMenu[i].getHarga());
            }
        }
    }
    
    private static void menuPelanggan() {
        resetPesanan();
        boolean selesaiMemesan = false;
        
        while (!selesaiMemesan) {
            tampilkanDaftarMenu();
            System.out.println("\nMasukkan nomor menu yang ingin dipesan");
            System.out.println("(ketik 'selesai' untuk menyelesaikan pesanan)");
            System.out.print("Pilihan: ");
            
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("selesai")) {
                if (totalItemPesanan == 0) {
                    System.out.println("Anda belum memesan apapun!");
                } else {
                    selesaiMemesan = true;
                    prosesCheckout();
                }
            } else {
                menerimaPesanan(input);
            }
        }
    }
    
    private static void menerimaPesanan(String input) {
        try {
            int nomorMenu = Integer.parseInt(input);
            
            if (nomorMenu < 1 || nomorMenu > jumlahMenu) {
                System.out.println("Nomor menu tidak valid! Silakan coba lagi.");
                return;
            }
            
            Menu menuDipilih = daftarMenu[nomorMenu - 1];
            System.out.print("Jumlah porsi: ");
            
            String jumlahStr = scanner.nextLine().trim();
            int jumlah = Integer.parseInt(jumlahStr);
            
            if (jumlah <= 0) {
                System.out.println("Jumlah harus lebih dari 0!");
                return;
            }

            boolean sudahAda = false;
            
            for (int i = 0; i < totalItemPesanan; i++) {
                if (pesanan[i] == menuDipilih) {
                    jumlahPesanan[i] += jumlah;
                    sudahAda = true;
                    break;
                }
            }
            
            if (!sudahAda) {
                pesanan[totalItemPesanan] = menuDipilih;
                jumlahPesanan[totalItemPesanan] = jumlah;
                totalItemPesanan++;
            }
            
            System.out.println("\n✓ " + menuDipilih.getNama() + " x" + jumlah + " ditambahkan ke pesanan");
            
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid! Silakan masukkan nomor menu atau 'selesai'.");
        }
    }
    
    private static double hitungTotalBiaya() {
        double subtotal = 0;
        
        for (int i = 0; i < totalItemPesanan; i++) {
           double hargaItem = pesanan[i].getHarga() * jumlahPesanan[i];
            subtotal += hargaItem;
        }
        
        return subtotal;
    }
    
    private static int hitungJumlahMinuman() {
        int totalMinuman = 0;
        
        for (int i = 0; i < totalItemPesanan; i++) {
            if (pesanan[i].getKategori().equals("minuman")) {
                totalMinuman += jumlahPesanan[i];
            }
        }
        
        return totalMinuman;
    }
    
    private static void prosesCheckout() {
        double subtotal = hitungTotalBiaya();
        int totalMinuman = hitungJumlahMinuman();
        
        double diskon = 0;
        boolean dapatDiskon10Persen = false;
        if (subtotal > 100000) {
            diskon = subtotal * 0.1;
            dapatDiskon10Persen = true;
            System.out.println("\n* Selamat! Anda mendapat diskon 10%");
        }
        
        boolean dapatPromoMinuman = false;
        if (subtotal > 50000 && totalMinuman > 0) {
            dapatPromoMinuman = true;
            System.out.println("* Selamat! Anda mendapat promo beli 1 gratis 1 minuman");
        }
        
        double subtotalSetelahDiskon = subtotal - diskon;
        double pajak = subtotalSetelahDiskon * 0.1;
        double biayaPelayanan = 20000;
        double totalAkhir = subtotalSetelahDiskon + pajak + biayaPelayanan;
        
        cetakStrukPesanan(subtotal, diskon, pajak, biayaPelayanan, totalAkhir, 
                         dapatDiskon10Persen, dapatPromoMinuman);
    }
    
    private static void cetakStrukPesanan(double subtotal, double diskon, double pajak, 
                                          double biayaPelayanan, double totalAkhir,
                                          boolean dapatDiskon, boolean dapatPromo) {
        System.out.println("\n========================================");
        System.out.println("            STRUK PEMESANAN");
        System.out.println("========================================");
        System.out.println("Tanggal: " + java.time.LocalDateTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.out.println("========================================");
        
        System.out.printf("%-25s %5s %10s %10s\n", "Item", "Qty", "Harga", "Total");
        System.out.println("----------------------------------------------------------");
        
        for (int i = 0; i < totalItemPesanan; i++) {
            double totalHargaItem = pesanan[i].getHarga() * jumlahPesanan[i];
            System.out.printf("%-25s %5d %,10.0f %,10.0f\n",
                pesanan[i].getNama(),
                jumlahPesanan[i],
                pesanan[i].getHarga(),
                totalHargaItem);
        }
        
        System.out.println("========================================");
        System.out.printf("Subtotal:                    %,15.0f\n", subtotal);
        
        if (dapatDiskon) {
            System.out.printf("Diskon (10%%)                -%,15.0f\n", diskon);
            System.out.printf("Subtotal (stlh diskon):      %,15.0f\n", subtotal - diskon);
        }
        
        if (dapatPromo) {
            System.out.println("\n★★★ PROMO: Beli 1 Gratis 1 Minuman! ★★★");
        }
        
        System.out.printf("Pajak (10%%):                     %,15.0f\n", pajak);
        System.out.printf("Biaya Pelayanan:             %,15.0f\n", biayaPelayanan);
        System.out.println("========================================");
        System.out.printf("TOTAL PEMBAYARAN:            %,15.0f\n", totalAkhir);
        System.out.println("========================================");
        System.out.println("\n   Terima kasih atas kunjungan Anda!");
        System.out.println("       Sampai jumpa kembali!");
        System.out.println("========================================\n");
    }

    private static void resetPesanan() {
        totalItemPesanan = 0;
        for (int i = 0; i < pesanan.length; i++) {
            pesanan[i] = null;
            jumlahPesanan[i] = 0;
        }
    }
    
    private static void menuPemilik() {
        boolean kembali = false;
        
        while (!kembali) {
            System.out.println("\n========================================");
            System.out.println("      MENU PENGELOLAAN RESTORAN");
            System.out.println("========================================");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Lihat Semua Menu");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            
            String pilihan = scanner.nextLine().trim();
            
            if (pilihan.equals("1")) {
                tambahMenuBaru();
            } else if (pilihan.equals("2")) {
                ubahMenu();
            } else if (pilihan.equals("3")) {
                hapusMenu();
            } else if (pilihan.equals("4")) {
                tampilkanDaftarMenu();
            } else if (pilihan.equals("5")) {
                kembali = true;
            } else {
                System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        }
    }
    
    private static void tambahMenuBaru() {
        System.out.println("\n--- TAMBAH MENU BARU ---");
        System.out.print("Berapa menu yang ingin ditambahkan? ");
        
        try {
            int jumlahTambah = Integer.parseInt(scanner.nextLine().trim());
            
            if (jumlahTambah <= 0) {
                System.out.println("Jumlah harus lebih dari 0!");
                return;
            }
            
            for (int i = 0; i < jumlahTambah; i++) {
                System.out.println("\n>> Menu ke-" + (i + 1));
                
                System.out.print("Nama menu: ");
                String nama = scanner.nextLine().trim();
                
                if (nama.isEmpty()) {
                    System.out.println("Nama menu tidak boleh kosong!");
                    i--;
                    continue;
                }
                
                double harga = 0;
                boolean hargaValid = false;
                
                while (!hargaValid) {
                    try {
                        System.out.print("Harga: Rp ");
                        harga = Double.parseDouble(scanner.nextLine().trim());
                        
                        if (harga <= 0) {
                            System.out.println("Harga harus lebih dari 0!");
                        } else {
                            hargaValid = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harga tidak valid!");
                    }
                }
                
                String kategori = "";
                boolean kategoriValid = false;
                
                while (!kategoriValid) {
                    System.out.print("Kategori (makanan/minuman): ");
                    kategori = scanner.nextLine().trim().toLowerCase();
                    
                    if (kategori.equals("makanan") || kategori.equals("minuman")) {
                        kategoriValid = true;
                    } else {
                        System.out.println("Kategori harus 'makanan' atau 'minuman'!");
                    }
                }
                
                if (jumlahMenu < daftarMenu.length) {
                    daftarMenu[jumlahMenu++] = new Menu(nama, harga, kategori);
                    System.out.println("✓ Menu berhasil ditambahkan!");
                } else {
                    System.out.println("Kapasitas menu penuh!");
                }
            }
            
            System.out.println("\n✓ Total " + jumlahTambah + " menu berhasil ditambahkan!");
            
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
    }
    
    private static void ubahMenu() {
        if (jumlahMenu == 0) {
            System.out.println("\nTidak ada menu yang tersedia!");
            return;
        }
        
        tampilkanDaftarMenu();
        
        int nomor = 0;
        boolean inputValid = false;
        
        while (!inputValid) {
            try {
                System.out.print("\nMasukkan nomor menu yang ingin diubah: ");
                nomor = Integer.parseInt(scanner.nextLine().trim());
                
                if (nomor < 1 || nomor > jumlahMenu) {
                    System.out.println("Nomor menu tidak valid!");
                } else {
                    inputValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid!");
            }
        }
        
        Menu menu = daftarMenu[nomor - 1];
        System.out.println("\nMenu yang dipilih: " + menu.getNama());
        System.out.println("Harga saat ini: Rp " + String.format("%,.0f", menu.getHarga()));
        
        System.out.print("\nNama baru (tekan Enter untuk tidak mengubah): ");
        String namaBaru = scanner.nextLine().trim();
        
        System.out.print("Harga baru (tekan Enter untuk tidak mengubah): ");
        String hargaStr = scanner.nextLine().trim();
        
        System.out.print("\nApakah Anda yakin ingin mengubah menu ini? (Ya/Tidak): ");
        String konfirmasi = scanner.nextLine().trim().toLowerCase();
        
        if (konfirmasi.equals("ya")) {
            if (!namaBaru.isEmpty()) {
                menu.setNama(namaBaru);
            }
            
            if (!hargaStr.isEmpty()) {
                try {
                    double hargaBaru = Double.parseDouble(hargaStr);
                    if (hargaBaru > 0) {
                        menu.setHarga(hargaBaru);
                    } else {
                        System.out.println("Harga tidak valid, harga tidak diubah.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Harga tidak valid, harga tidak diubah.");
                }
            }
            System.out.println("\n Menu berhasil diubah!");
        } else {
            System.out.println("\nPerubahan dibatalkan.");
        }
    }
    
    private static void hapusMenu() {
        if (jumlahMenu == 0) {
            System.out.println("\nTidak ada menu yang tersedia!");
            return;
        }
        
        tampilkanDaftarMenu();
        
        int nomor = 0;
        boolean inputValid = false;
        
        while (!inputValid) {
            try {
                System.out.print("\nMasukkan nomor menu yang ingin dihapus: ");
                nomor = Integer.parseInt(scanner.nextLine().trim());
                
                if (nomor < 1 || nomor > jumlahMenu) {
                    System.out.println("Nomor menu tidak valid!");
                } else {
                    inputValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid!");
            }
        }
        
        Menu menu = daftarMenu[nomor - 1];
        System.out.println("\nMenu yang akan dihapus: " + menu.getNama());
        System.out.print("Apakah Anda yakin ingin menghapus menu ini? (Ya/Tidak): ");
        String konfirmasi = scanner.nextLine().trim().toLowerCase();
        
        if (konfirmasi.equals("ya")) {
            for (int i = nomor - 1; i < jumlahMenu - 1; i++) {
                daftarMenu[i] = daftarMenu[i + 1];
            }
            daftarMenu[jumlahMenu - 1] = null;
            jumlahMenu--;
            System.out.println("\n✓ Menu berhasil dihapus!");
        } else {
            System.out.println("\nPenghapusan dibatalkan.");
        }
    }

}