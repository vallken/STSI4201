import java.util.Scanner;

class Restoran {
    private Menu[] daftarMenu;

    public Restoran() {
        daftarMenu = new Menu[8];
        daftarMenu[0] = new Menu("Nasi Padang", 25000, "Makanan");
        daftarMenu[1] = new Menu("Nasi Goreng", 20000, "Makanan");
        daftarMenu[2] = new Menu("Mie Ayam", 18000, "Makanan");
        daftarMenu[3] = new Menu("Soto Ayam", 22000, "Makanan");
        daftarMenu[4] = new Menu("Es Teh", 5000, "Minuman");
        daftarMenu[5] = new Menu("Es Jeruk", 7000, "Minuman");
        daftarMenu[6] = new Menu("Jus Alpukat", 12000, "Minuman");
        daftarMenu[7] = new Menu("Kopi", 10000, "Minuman");
    }

    public void tampilkanMenu() {
        System.out.println("========== DAFTAR MENU ==========\n");

        System.out.println(">>> MAKANAN <<<");
        tampilkanMenuByKategori("Makanan");

        System.out.println();

        System.out.println(">>> MINUMAN <<<");
        tampilkanMenuByKategori("Minuman");

        System.out.println("=================================\n");
    }

    private void tampilkanMenuByKategori(String kategori) {
        int nomor = 1;
        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i].getKategori().equalsIgnoreCase(kategori)) {
                System.out.printf("%d. %-20s Rp %,.0f\n",
                        nomor++,
                        daftarMenu[i].getNama(),
                        daftarMenu[i].getHarga());
            }
        }
    }

    public Menu cariMenu(String nama) {
        for (Menu menu : daftarMenu) {
            if (menu.getNama().equalsIgnoreCase(nama)) {
                return menu;
            }
        }
        return null;
    }

    public void prosesStrukPesanan(String[] namaPesanan, int[] jumlahPesanan) {
        Scanner sc = new Scanner(System.in);

        double totalBiaya = 0;
        boolean adaMinuman = false;
        String[] minumanPesanan = new String[namaPesanan.length];

        for (int i = 0; i < namaPesanan.length; i++) {
            if (namaPesanan[i] != null) {
                Menu menu = cariMenu(namaPesanan[i]);
                if (menu != null) {
                    double subtotal = menu.getHarga() * jumlahPesanan[i];
                    totalBiaya += subtotal;

                    if (menu.getKategori().equalsIgnoreCase("Minuman")) {
                        adaMinuman = true;
                        minumanPesanan[i] = menu.getNama();
                    }
                }
            }
        }

        double diskonMinuman = 0;
        String minumanGratis = "";

        if (totalBiaya > 50000 && adaMinuman) {
            System.out.println("\n*** SELAMAT! Anda Mendapat PROMO BELI 1 GRATIS 1 MINUMAN ***");
            System.out.println("Minuman yang tersedia dalam pesanan Anda:");
            for (String min : minumanPesanan) {
                if (min != null) {
                    System.out.println("- " + min);
                }
            }
            System.out.print("\nPilih minuman untuk gratis 1: ");
            minumanGratis = sc.nextLine();

            Menu menuGratis = cariMenu(minumanGratis);
            if (menuGratis != null) {
                diskonMinuman = menuGratis.getHarga();
                totalBiaya -= diskonMinuman;
            }
        }

        sc.close();

        System.out.println("\n===========================================");
        System.out.println("            STRUK PEMESANAN");
        System.out.println("===========================================");
        System.out.println("Restoran Nusantara");
        System.out.println("Jl. Malioboro No. 123, Yogyakarta");
        System.out.println("-------------------------------------------\n");

        for (int i = 0; i < namaPesanan.length; i++) {
            if (namaPesanan[i] != null) {
                Menu menu = cariMenu(namaPesanan[i]);
                if (menu != null) {
                    double subtotal = menu.getHarga() * jumlahPesanan[i];
                    System.out.printf("%-20s x%d\n", menu.getNama(), jumlahPesanan[i]);
                    System.out.printf("  @Rp %,.0f = Rp %,.0f\n\n", menu.getHarga(), subtotal);
                }
            }
        }

        System.out.println("-------------------------------------------");
        System.out.printf("Subtotal:                    Rp %,.0f\n", totalBiaya + diskonMinuman);

        if (diskonMinuman > 0) {
            System.out.printf("Promo Minuman Gratis (%s): -Rp %,.0f\n", minumanGratis, diskonMinuman);
            System.out.printf("Subtotal setelah promo:      Rp %,.0f\n", totalBiaya);
        }

        double pajak = totalBiaya * 0.10;
        System.out.printf("Pajak (10%%):                 Rp %,.0f\n", pajak);

        double biayaPelayanan = 20000;
        System.out.printf("Biaya Pelayanan:             Rp %,.0f\n", biayaPelayanan);

        double totalSebelumDiskon = totalBiaya + pajak + biayaPelayanan;

        double diskon = 0;
        if (totalSebelumDiskon > 100000) {
            diskon = totalSebelumDiskon * 0.10;
            System.out.println("-------------------------------------------");
            System.out.printf("Total Sebelum Diskon:        Rp %,.0f\n", totalSebelumDiskon);
            System.out.printf("Diskon (10%%):               -Rp %,.0f\n", diskon);
        }

        double totalAkhir = totalSebelumDiskon - diskon;
        System.out.println("===========================================");
        System.out.printf("TOTAL PEMBAYARAN:            Rp %,.0f\n", totalAkhir);
        System.out.println("===========================================\n");

        System.out.println("   Terima kasih atas kunjungan Anda!");
        System.out.println("===========================================");
    }
}
