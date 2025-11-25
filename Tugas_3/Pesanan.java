import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pesanan {
    private ArrayList<MenuItem> itemPesanan;
    private Diskon diskonDipakai;
    private String namaPelanggan;
    private static int nomorPesananCounter = 1;
    private int nomorPesanan;
    

    public Pesanan(String namaPelanggan) {
        this.itemPesanan = new ArrayList<>();
        this.diskonDipakai = null;
        this.namaPelanggan = namaPelanggan;
        this.nomorPesanan = nomorPesananCounter++;
    }
    
    public void tambahItem(MenuItem item) {
        if (item instanceof Diskon) {
            System.out.println("✗ Diskon tidak bisa ditambahkan sebagai item pesanan!");
            return;
        }
        itemPesanan.add(item);
        System.out.println("✓ '" + item.getNama() + "' ditambahkan ke pesanan");
    }
    
    public void terapkanDiskon(Diskon diskon) {
        this.diskonDipakai = diskon;
        System.out.println("✓ Diskon '" + diskon.getNama() + "' berhasil diterapkan!");
    }

    public double hitungSubtotal() {
        double subtotal = 0;
        for (MenuItem item : itemPesanan) {
            subtotal += item.getHarga();
        }
        return subtotal;
    }

    public double hitungTotal() {
        double subtotal = hitungSubtotal();
        
        if (diskonDipakai != null) {
            double nilaiDiskon = diskonDipakai.hitungDiskon(subtotal);
            return subtotal - nilaiDiskon;
        }
        
        return subtotal;
    }
    
    public void tampilkanStruk() {
        if (itemPesanan.isEmpty()) {
            System.out.println("Pesanan masih kosong!");
            return;
        }
        
        System.out.println("\n========================================");
        System.out.println("            STRUK PEMBAYARAN             ");
        System.out.println("==========================================");
        System.out.println("  Nomor Pesanan: #" + nomorPesanan);
        System.out.println("  Nama Pelanggan: " + namaPelanggan);
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("  Waktu: " + now.format(formatter));
        
        System.out.println("=========================================");
        System.out.println("  ITEM PESANAN:");
        System.out.println("=========================================");
        
        for (int i = 0; i < itemPesanan.size(); i++) {
            MenuItem item = itemPesanan.get(i);
            System.out.printf("  %d. %-25s Rp %,.2f%n", 
                (i + 1), item.getNama(), item.getHarga());
        }
        
        System.out.println("------------------------------------------");
        System.out.printf("  Subtotal:              Rp %,.2f%n", hitungSubtotal());
        
        if (diskonDipakai != null) {
            double nilaiDiskon = diskonDipakai.hitungDiskon(hitungSubtotal());
            System.out.printf("  Diskon (%s %.1f%%):   -Rp %,.2f%n", 
                diskonDipakai.getNama(), 
                diskonDipakai.getPersenDiskon(), 
                nilaiDiskon);
        }
        
        System.out.println("==========================================");
        System.out.printf("  TOTAL:                 Rp %,.2f%n", hitungTotal());
        System.out.println("==========================================\n");
    }

    public void simpanStrukKeFile() {
        String namaFile = "struk_pesanan_" + nomorPesanan + ".txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            writer.write("=========================================\n");
            writer.write("         STRUK PEMBAYARAN\n");
            writer.write("=========================================\n");
            writer.write("Nomor Pesanan: #" + nomorPesanan + "\n");
            writer.write("Nama Pelanggan: " + namaPelanggan + "\n");
            
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            writer.write("Waktu: " + now.format(formatter) + "\n");
            
            writer.write("========================================\n");
            writer.write("ITEM PESANAN:\n");
            writer.write("========================================\n");
            
            for (int i = 0; i < itemPesanan.size(); i++) {
                MenuItem item = itemPesanan.get(i);
                writer.write(String.format("%d. %-25s Rp %,.2f%n", 
                    (i + 1), item.getNama(), item.getHarga()));
            }
            
            writer.write("=========================================\n");
            writer.write(String.format("Subtotal:              Rp %,.2f%n", hitungSubtotal()));
            
            if (diskonDipakai != null) {
                double nilaiDiskon = diskonDipakai.hitungDiskon(hitungSubtotal());
                writer.write(String.format("Diskon (%s %.1f%%):   -Rp %,.2f%n", 
                    diskonDipakai.getNama(), 
                    diskonDipakai.getPersenDiskon(), 
                    nilaiDiskon));
            }
            
            writer.write("========================================\n");
            writer.write(String.format("TOTAL:                 Rp %,.2f%n", hitungTotal()));
            writer.write("========================================\n");
            writer.write("\nTerima kasih atas kunjungan Anda!\n");
            
            System.out.println("✓ Struk berhasil disimpan ke file: " + namaFile);
        } catch (IOException e) {
            System.out.println("✗ Error saat menyimpan struk: " + e.getMessage());
        }
    }
    
    public boolean isEmpty() {
        return itemPesanan.isEmpty();
    }

    public int getNomorPesanan() {
        return nomorPesanan;
    }

}