package lab4; 
import java.util.Scanner;

public class Lab4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Kac cihaz ekleyeceksiniz? ");
        int deviceCount = 0;
        try {
            deviceCount = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Hata. 0 atandı.");
            scanner.nextLine();
        }

        Device[] devices = new Device[deviceCount];
        scanner.nextLine(); 

        for (int i = 0; i < deviceCount; i++) {
            System.out.println("\n--- " + (i + 1) + ". Cihaz Ekleme ---");
            System.out.print("Cihaz turu (1-Lamba, 2-Supurge): ");
            int type = 2;
            try {
                type = scanner.nextInt();
            } catch (Exception e) {
                type = 2;
            }
            scanner.nextLine(); 

            System.out.print("Cihaz adi: ");
            String name = scanner.nextLine();

            System.out.print("Pil seviyesi: ");
            int battery = 50;
            try {
                battery = scanner.nextInt();
            } catch (Exception e) {}
            scanner.nextLine();

            if (type == 1) { 
                System.out.print("Parlaklik: ");
                int brightness = 50;
                try { brightness = scanner.nextInt(); } catch(Exception e){}
                scanner.nextLine();
                devices[i] = new SmartLamp(name, battery, brightness);
            } else { 
                System.out.print("Mod (eco/turbo/silent): ");
                String mode = scanner.nextLine();
                devices[i] = new RobotVacuum(name, battery, mode);
            }
        }

        System.out.println("\n===== CIHAZLAR ACILIYOR =====");
        for (Device d : devices) { if (d != null) d.turnOn(); }

        System.out.println("\n===== CIHAZ DURUMLARI =====");
        for (Device d : devices) { if (d != null) System.out.println(d.getStatus()); }

        System.out.println("\n===== CIHAZ ARAMA =====");
        System.out.print("Aranacak isim: ");
        String searchName = scanner.nextLine();
        boolean found = false;
        for (Device d : devices) {
            if (d != null && d.getName().toLowerCase().equals(searchName.toLowerCase())) {
                System.out.println(d.getStatus());
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Bulunamadı.");
        scanner.close();
    }
}