public class Makanan extends MenuItem {
    private String jenisMakanan;
    
    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }
    
    public String getJenisMakanan() {
        return jenisMakanan;
    }
    
    public void setJenisMakanan(String jenisMakanan) {
        this.jenisMakanan = jenisMakanan;
    }

    @Override
    public void tampilMenu() {
        System.out.println("==========================================");
        System.out.println("  [MAKANAN] " + getNama());
        System.out.println("  Jenis: " + jenisMakanan);
        System.out.printf("  Harga: Rp %.2f%n", getHarga());
        System.out.println("===========================================");
    }

    @Override    
    public String toFileString() {
        return "MAKANAN|" + getNama() + "|" + getHarga() + "|" + jenisMakanan;
    }

}