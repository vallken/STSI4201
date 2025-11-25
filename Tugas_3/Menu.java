import java.util.ArrayList;
import java.io.*;

public class Menu {
    private ArrayList<MenuItem> daftarMenu; // Collection untuk menyimpan semua menu
    private static final String FILE_MENU = "menu_restoran.txt";
   
    public Menu() {
        daftarMenu = new ArrayList<>();
    }
    

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
        System.out.println("✓ Item '" + item.getNama() + "' berhasil ditambahkan!");
    }

    public void tampilkanSemuaMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu masih kosong!");
            return;
        }
        
        System.out.println("\n========================================");
        System.out.println("         DAFTAR MENU RESTORAN            ");
        System.out.println("==========================================\n");
        
        System.out.println("=== MAKANAN ===");
        for (MenuItem item : daftarMenu) {
            if (item instanceof Makanan) {
                item.tampilMenu(); 
                System.out.println();
            }
        }
        
        System.out.println("=== MINUMAN ===");
        for (MenuItem item : daftarMenu) {
            if (item instanceof Minuman) {
                item.tampilMenu(); 
                System.out.println();
            }
        }
        
        System.out.println("=== DISKON TERSEDIA ===");
        for (MenuItem item : daftarMenu) {
            if (item instanceof Diskon) {
                item.tampilMenu(); 
                System.out.println();
            }
        }
    }
    
    public MenuItem cariItem(String nama) throws MenuNotFoundException {
        for (MenuItem item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        throw new MenuNotFoundException("Item '" + nama + "' tidak ditemukan dalam menu!");
    }
    

    public ArrayList<Diskon> getDaftarDiskon() {
        ArrayList<Diskon> diskonList = new ArrayList<>();
        for (MenuItem item : daftarMenu) {
            if (item instanceof Diskon) {
                diskonList.add((Diskon) item);
            }
        }
        return diskonList;
    }

    public void simpanKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_MENU))) {
            for (MenuItem item : daftarMenu) {
                writer.write(item.toFileString());
                writer.newLine();
            }
            System.out.println("✓ Menu berhasil disimpan ke file!");
        } catch (IOException e) {
            System.out.println("✗ Error saat menyimpan menu: " + e.getMessage());
        }
    }

    public void muatDariFile() {
        File file = new File(FILE_MENU);
        if (!file.exists()) {
            System.out.println("File menu belum ada. Mulai dengan menu kosong.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_MENU))) {
            String line;
            daftarMenu.clear();
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                
                if (parts[0].equals("MAKANAN")) {
                    Makanan makanan = new Makanan(parts[1], Double.parseDouble(parts[2]), parts[3]);
                    daftarMenu.add(makanan);
                } else if (parts[0].equals("MINUMAN")) {
                    Minuman minuman = new Minuman(parts[1], Double.parseDouble(parts[2]), parts[3]);
                    daftarMenu.add(minuman);
                } else if (parts[0].equals("DISKON")) {
                    Diskon diskon = new Diskon(parts[1], Double.parseDouble(parts[2]));
                    daftarMenu.add(diskon);
                }
            }
            System.out.println(" Menu berhasil dimuat dari file!");
        } catch (IOException e) {
            System.out.println("✗ Error saat memuat menu: " + e.getMessage());
        }
    }

    public ArrayList<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }

}