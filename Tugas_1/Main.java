import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Restoran restoran = new Restoran();
        
        System.out.println("===========================================");
        System.out.println("   SELAMAT DATANG DI RESTORAN NUSANTARA");
        System.out.println("===========================================\n");
        
        restoran.tampilkanMenu();

        String[] pesananNama = new String[4];
        int[] pesananJumlah = new int[4];
        
        System.out.println("\n*** PEMESANAN ***");
        System.out.println("Silakan masukkan pesanan Anda (maksimal 4 menu)");
        System.out.println("Format: Nama Menu = Jumlah");
        System.out.println("Ketik 'selesai' jika sudah selesai memesan\n");
        
        System.out.print("Pesanan 1: ");
        String input1 = scanner.nextLine();
        if (!input1.equalsIgnoreCase("selesai")) {
            prosesInputPesanan(input1, pesananNama, pesananJumlah, 0);
            
            System.out.print("Pesanan 2: ");
            String input2 = scanner.nextLine();
            if (!input2.equalsIgnoreCase("selesai")) {
                prosesInputPesanan(input2, pesananNama, pesananJumlah, 1);
                
                System.out.print("Pesanan 3: ");
                String input3 = scanner.nextLine();
                if (!input3.equalsIgnoreCase("selesai")) {
                    prosesInputPesanan(input3, pesananNama, pesananJumlah, 2);
                    
                    System.out.print("Pesanan 4: ");
                    String input4 = scanner.nextLine();
                    if (!input4.equalsIgnoreCase("selesai")) {
                        prosesInputPesanan(input4, pesananNama, pesananJumlah, 3);
                    }
                }
            }
        }
        
        restoran.prosesStrukPesanan(pesananNama, pesananJumlah);
        
        scanner.close();
    }
    
    private static void prosesInputPesanan(String input, String[] pesananNama, int[] pesananJumlah, int index) {
        if (input.contains("=")) {
            String[] parts = input.split("=");
            if (parts.length == 2) {
                pesananNama[index] = parts[0].trim();
                pesananJumlah[index] = Integer.parseInt(parts[1].trim());
            }
        }
    }
}
