public class Diskon extends MenuItem {
    private double persenDiskon; 

    public Diskon(String nama, double persenDiskon) {
        super(nama, 0, "Diskon"); 
        this.persenDiskon = persenDiskon;
    }
    
    public double getPersenDiskon() {
        return persenDiskon;
    }
    
    public void setPersenDiskon(double persenDiskon) {
        this.persenDiskon = persenDiskon;
    }
    
    public double hitungDiskon(double totalHarga) {
        return totalHarga * (persenDiskon / 100);
    }

    @Override    
    public void tampilMenu() {
        System.out.println("==========================================");
        System.out.println("  [DISKON] " + getNama());
        System.out.printf("  Diskon: %.1f%%%n", persenDiskon);
        System.out.println("  Dapat digunakan untuk pesanan!");
        System.out.println("==========================================");
    }
    
    @Override    
    public String toFileString() {
        return "DISKON|" + getNama() + "|" + persenDiskon;
    }
}
