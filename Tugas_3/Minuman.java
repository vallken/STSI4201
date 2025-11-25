public class Minuman extends MenuItem {
    private String jenisMinuman; 
    
    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }
    
    public String getJenisMinuman() {
        return jenisMinuman;
    }
    
    public void setJenisMinuman(String jenisMinuman) {
        this.jenisMinuman = jenisMinuman;
    }
    
    @Override    
    public void tampilMenu() {
        System.out.println("==========================================");
        System.out.println("  [MINUMAN] " + getNama());
        System.out.println("  Jenis: " + jenisMinuman);
        System.out.printf("  Harga: Rp %.2f%n", getHarga());
        System.out.println("===========================================");
    }

    @Override    
    public String toFileString() {
        return "MINUMAN|" + getNama() + "|" + getHarga() + "|" + jenisMinuman;
    }

}