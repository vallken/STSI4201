import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Menu menu = new Menu();
    private static Pesanan pesananAktif = null;
    
    public static void main(String[] args) {
        menu.muatDariFile();
        
        if (menu.getDaftarMenu().isEmpty()) {
            inisialisasiMenuDefault();
        }
        
        System.out.println("=========================================");
        System.out.println("    SELAMAT DATANG DI RESTORAN KAMI     ");
        System.out.println("=========================================");
        
        boolean running = true;
        
        while (running) {
            tampilkanMenuUtama();
            
            try {
                System.out.print("\nPilih menu (1-6): ");
                int pilihan = Integer.parseInt(scanner.nextLine());
                
                switch (pilihan) {
                    case 1:
                        tambahItemMenu();
                        break;
                    case 2:
                        menu.tampilkanSemuaMenu();
                        break;
                    case 3:
                        buatPesananBaru();
                        break;
                    case 4:
                        lihatStrukPesanan();
                        break;
                    case 5:
                        selesaikanPesanan();
                        break;
                    case 6:
                        menu.simpanKeFile();
                        System.out.println("\n✓ Terima kasih telah menggunakan sistem kami!");
                        running = false;
                        break;
                    default:
                        System.out.println("✗ Pilihan tidak valid!");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("✗ Input harus berupa angka!");
            } catch (Exception e) {
                System.out.println("✗ Terjadi kesalahan: " + e.getMessage());
            }
            
            if (running) {
                System.out.println("\nTekan Enter untuk melanjutkan...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n========================================");
        System.out.println("|           MENU UTAMA                  |");
        System.out.println("|=======================================|");
        System.out.println("| 1. Tambah Item Menu                   |");
        System.out.println("| 2. Tampilkan Menu Restoran            |");
        System.out.println("| 3. Buat Pesanan Baru                  |");
        System.out.println("| 4. Lihat Struk Pesanan                |");
        System.out.println("| 5. Selesaikan & Simpan Pesanan        |");
        System.out.println("| 6. Keluar                             |");
        System.out.println("|=======================================|");
    }
    
    private static void tambahItemMenu() {
        System.out.println("\n=== TAMBAH ITEM MENU ===");
        System.out.println("1. Tambah Makanan");
        System.out.println("2. Tambah Minuman");
        System.out.println("3. Tambah Diskon");
        System.out.print("Pilih jenis item (1-3): ");
        
        try {
            int jenis = Integer.parseInt(scanner.nextLine());
            
            switch (jenis) {
                case 1:
                    tambahMakanan();
                    break;
                case 2:
                    tambahMinuman();
                    break;
                case 3:
                    tambahDiskon();
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Input harus berupa angka!");
        }
    }

    private static void tambahMakanan() {
        System.out.print("Nama makanan: ");
        String nama = scanner.nextLine();
        
        System.out.print("Harga: ");
        double harga = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Jenis makanan (Pembuka/Utama/Penutup): ");
        String jenis = scanner.nextLine();
        
        Makanan makanan = new Makanan(nama, harga, jenis);
        menu.tambahItem(makanan);
    }

    private static void tambahMinuman() {
        System.out.print("Nama minuman: ");
        String nama = scanner.nextLine();
        
        System.out.print("Harga: ");
        double harga = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Jenis minuman (Dingin/Panas/Soda): ");
        String jenis = scanner.nextLine();
        
        Minuman minuman = new Minuman(nama, harga, jenis);
        menu.tambahItem(minuman);
    }

    private static void tambahDiskon() {
        System.out.print("Nama diskon: ");
        String nama = scanner.nextLine();
        
        System.out.print("Persentase diskon (0-100): ");
        double persen = Double.parseDouble(scanner.nextLine());
        
        if (persen < 0 || persen > 100) {
            System.out.println(" Persentase diskon harus antara 0-100!");
            return;
        }
        
        Diskon diskon = new Diskon(nama, persen);
        menu.tambahItem(diskon);
    }

    private static void buatPesananBaru() {
        System.out.print("\nMasukkan nama pelanggan: ");
        String namaPelanggan = scanner.nextLine();
        
        pesananAktif = new Pesanan(namaPelanggan);
        System.out.println("\n✓ Pesanan baru dibuat untuk " + namaPelanggan);
        
        boolean selesaiPesan = false;
        
        while (!selesaiPesan) {
            System.out.println("\n--- Menu Pesanan ---");
            System.out.println("1. Tambah item ke pesanan");
            System.out.println("2. Terapkan diskon");
            System.out.println("3. Selesai memesan");
            System.out.print("Pilih (1-3): ");
            
            try {
                int pilihan = Integer.parseInt(scanner.nextLine());
                
                switch (pilihan) {
                    case 1:
                        tambahItemKePesanan();
                        break;
                    case 2:
                        terapkanDiskonKePesanan();
                        break;
                    case 3:
                        selesaiPesan = true;
                        System.out.println("✓ Pesanan selesai!");
                        break;
                    default:
                        System.out.println("✗ Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Input harus berupa angka!");
            }
        }
    }

    private static void tambahItemKePesanan() {
        if (pesananAktif == null) {
            System.out.println("✗ Belum ada pesanan aktif! Buat pesanan baru dulu.");
            return;
    }
        
        System.out.print("Nama item yang ingin dipesan: ");
        String namaItem = scanner.nextLine();
        
        try {
            MenuItem item = menu.cariItem(namaItem);
            pesananAktif.tambahItem(item);
        } catch (MenuNotFoundException e) {
            // Menangkap custom exception
            System.out.println(" " + e.getMessage());
        }
    }

    private static void terapkanDiskonKePesanan() {
        if (pesananAktif == null) {
            System.out.println("✗ Belum ada pesanan aktif!");
            return;
    }
        
        ArrayList<Diskon> daftarDiskon = menu.getDaftarDiskon();
        
        if (daftarDiskon.isEmpty()) {
            System.out.println("✗ Tidak ada diskon yang tersedia!");
            return;
        }
        
        System.out.println("\n=== DISKON TERSEDIA ===");
        for (int i = 0; i < daftarDiskon.size(); i++) {
            Diskon d = daftarDiskon.get(i);
            System.out.printf("%d. %s (%.1f%%)\n", (i + 1), d.getNama(), d.getPersenDiskon());
        }
        
        System.out.print("Pilih diskon (1-" + daftarDiskon.size() + "): ");
        try {
            int pilihan = Integer.parseInt(scanner.nextLine());
            
            if (pilihan >= 1 && pilihan <= daftarDiskon.size()) {
                pesananAktif.terapkanDiskon(daftarDiskon.get(pilihan - 1));
            } else {
                System.out.println("✗ Pilihan tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Input harus berupa angka!");
        }
    }

    private static void lihatStrukPesanan() {
        if (pesananAktif == null || pesananAktif.isEmpty()) {
                System.out.println("\n✗ Belum ada pesanan aktif atau pesanan masih kosong!");
            return;
    }
        
        pesananAktif.tampilkanStruk();
    }
    private static void selesaikanPesanan() {
        if (pesananAktif == null || pesananAktif.isEmpty()) {
            System.out.println("\n✗ Belum ada pesanan aktif atau pesanan masih kosong!");
            return;
        }
        
        pesananAktif.tampilkanStruk();
        pesananAktif.simpanStrukKeFile();
        
        System.out.println("✓ Pesanan telah diselesaikan!");
        pesananAktif = null;
    }

    private static void inisialisasiMenuDefault() {
        System.out.println("Menginisialisasi menu default...");
        
        menu.tambahItem(new Makanan("Nasi Goreng", 25000, "Utama"));
        menu.tambahItem(new Makanan("Mie Goreng", 20000, "Utama"));
        menu.tambahItem(new Makanan("Sate Ayam", 30000, "Utama"));
        menu.tambahItem(new Makanan("Lumpia", 15000, "Pembuka"));
        menu.tambahItem(new Makanan("Es Krim", 12000, "Penutup"));
        
        menu.tambahItem(new Minuman("Es Teh", 5000, "Dingin"));
        menu.tambahItem(new Minuman("Es Jeruk", 7000, "Dingin"));
        menu.tambahItem(new Minuman("Kopi Panas", 8000, "Panas"));
        menu.tambahItem(new Minuman("Coca Cola", 10000, "Soda"));
        
        menu.tambahItem(new Diskon("Diskon Member", 10));
        menu.tambahItem(new Diskon("Diskon Weekend", 15));
        
        menu.simpanKeFile();
    }

}