package org.boston.libraries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

/**
 * NYP Lab 3: Kutuphane Analitigi Ana Uygulamasi.
 * Kullanici menusunu (switch-case) calistirir ve siniflari yonetir.
 */
public class LibraryApp {

    // Scanner'i tum metotlarin erisebilmesi icin statik yapalim
    private static Scanner scanner = new Scanner(System.in);
    
    // Kutuphane ve Istatistik nesnelerimizi olusturalim
    private static Library myLibrary = new Library();
    private static CirculationStats myStats = new CirculationStats();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    myLibrary.loadSampleBooks();
                    break;
                case 2:
                    myLibrary.listBooks();
                    break;
                case 3:
                    searchByTitle();
                    break;
                case 4:
                    searchByTitleAndYear();
                    break;
                case 5:
                    searchByRating();
                    break;
                case 6:
                    myLibrary.sortInventoryByRating();
                    break;
                case 7:
                    demonstrateArrayCopy();
                    break;
                case 8:
                    testArrayContentEquality();
                    break;
                case 9:
                    runStatsMenu(); // Istatistik icin alt menu
                    break;
                case 10:
                    demonstrateArrayListBridge();
                    break;
                case 11:
                    observeVectorCapacity();
                    break;
                case 0:
                    running = false;
                    System.out.println("Programdan cikiliyor. Hoscakalin!");
                    break;
                default:
                    System.out.println("Gecersiz secim! Lutfen 0-11 arasinda bir sayi girin.");
            }
            
            if (running) {
                System.out.println("\n(Menuye donmek icin Enter'a basin...)");
                scanner.nextLine(); // Kullanicinin ciktilari gormesi icin bekle
            }
        }
        scanner.close();
    }

    /**
     * Ana menuyu ekrana yazdirir.
     */
    private static void printMenu() {
        System.out.println("\n--- KUTUPHANE ANALITIGI MENUSU ---");
        System.out.println("1) Kitaplari Yukle (Ornek Veri)");
        System.out.println("2) Kitaplari Listele");
        System.out.println("3) Ara (Sadece Baslik)");
        System.out.println("4) Ara (Baslik + Yil)");
        System.out.println("5) Ara (Minimum Puana Gore)");
        System.out.println("6) Sirala (Ortalama Puan Artan - Selection Sort)");
        System.out.println("7) Puan Dizisi: Kopya Al (Elle / clone) Gosterimi");
        System.out.println("8) Puan Dizisi: Icerik Esitligi Testi");
        System.out.println("9) Istatistik (2D Dizi) Menusu");
        System.out.println("10) ArrayList <-> Dizi Donusumu");
        System.out.println("11) Vector Kapasite Gozlemi");
        System.out.println("0) Cikis");
        System.out.print("Seciminiz: ");
    }

    /**
     * Kullanicidan guvenli bir sekilde tamsayi girisi alir.
     * @return Kullanicinin secimi (int).
     */
    private static int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // new line karakterini tuket
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Hatali giris! Lutfen bir sayi girin.");
            scanner.nextLine(); // Hatali girisi temizle
            return -1; // Gecersiz secimi tetikle
        }
    }

    // --- Menu Islem Metotlari ---

    private static void searchByTitle() {
        System.out.print("Aranacak kitap basligini girin: ");
        String title = scanner.nextLine();
        ArrayList<Book> results = myLibrary.search(title);
        printSearchResults(results);
    }

    private static void searchByTitleAndYear() {
        System.out.print("Aranacak kitap basligini girin: ");
        String title = scanner.nextLine();
        System.out.print("Aranacak yili girin: ");
        try {
            int year = scanner.nextInt();
            scanner.nextLine(); // new line
            ArrayList<Book> results = myLibrary.search(title, year);
            printSearchResults(results);
        } catch (InputMismatchException e) {
            System.out.println("Gecersiz yil girdiniz.");
            scanner.nextLine();
        }
    }

    private static void searchByRating() {
        System.out.print("Minimum ortalama puani girin (orn: 8.5): ");
        try {
            double rating = scanner.nextDouble();
            scanner.nextLine(); // new line
            ArrayList<Book> results = myLibrary.search(rating);
            printSearchResults(results);
        } catch (InputMismatchException e) {
            System.out.println("Gecersiz puan girdiniz.");
            scanner.nextLine();
        }
    }
    
    private static void printSearchResults(ArrayList<Book> results) {
        if (results.isEmpty()) {
            System.out.println("Sonuc bulunamadi.");
        } else {
            System.out.println(results.size() + " adet sonuc bulundu:");
            for (Book book : results) {
                System.out.println(" * " + book);
            }
        }
    }

    /**
     * 7. Madde: Dizi kopyalamanın (manuel vs clone) alias olmadigini kanitlar.
     */
    private static void demonstrateArrayCopy() {
        System.out.println("--- Dizi Kopyalama Gosterimi ---");
        Book originalBook = myLibrary.getFirstBook();
        if (originalBook == null) {
            System.out.println("Lutfen once 1. secenegi kullanarak kitaplari yukleyin.");
            return;
        }
        
        // Gosterim icin yeni bir dizi olusturalim:
        int[] sourceRatings = new int[]{10, 8, 9};
        System.out.println("Orijinal Puan Dizisi: " + Arrays.toString(sourceRatings));
        
        // 1. Elle Kopya (loop ile)
        int[] manualCopy = new int[sourceRatings.length];
        for (int i = 0; i < sourceRatings.length; i++) {
            manualCopy[i] = sourceRatings[i];
        }
        
        // 2. clone() ile Kopya
        int[] cloneCopy = sourceRatings.clone();
        
        System.out.println("Manuel Kopyayi Degistirme (manualCopy[0] = 99)...");
        manualCopy[0] = 99;
        
        System.out.println("Clone Kopyayi Degistirme (cloneCopy[1] = 11)...");
        cloneCopy[1] = 11;
        
        System.out.println("\n--- SONUCLAR ---");
        System.out.println("Orijinal Dizi     : " + Arrays.toString(sourceRatings));
        System.out.println("Manuel Kopya (DEEP): " + Arrays.toString(manualCopy));
        System.out.println("Clone Kopya (DEEP) : " + Arrays.toString(cloneCopy));
        System.out.println("Sonuc: Her iki yontem de 'deep copy' (derin kopya) yapar ve orijinal diziyi (alias degil) etkilemez.");
    }

    /**
     * 
     */
    private static void testArrayContentEquality() {
        System.out.println("--- Dizi Icerik Esitligi Testi ---");
        int[] arr1 = {10, 20, 30};
        int[] arr2 = {10, 20, 30};
        int[] arr3 = {10, 99, 30}; // Farkli
        int[] arr4 = {10, 20};     // Farkli uzunluk
        
        System.out.println("arr1 = " + Arrays.toString(arr1));
        System.out.println("arr2 = " + Arrays.toString(arr2));
        System.out.println("arr3 = " + Arrays.toString(arr3));
        System.out.println("arr4 = " + Arrays.toString(arr4));
        
        // (Esit)
        System.out.println("\narr1 ve arr2 esit mi? ... " + Book.areRatingsArraysEqual(arr1, arr2));
        
        // (Farkli icerik)
        System.out.println("arr1 ve arr3 esit mi? ... " + Book.areRatingsArraysEqual(arr1, arr3));
        
        //(Farkli uzunluk)
        System.out.println("arr1 ve arr4 esit mi? ... " + Book.areRatingsArraysEqual(arr1, arr4));
        
        //(Null)
        System.out.println("arr1 ve null esit mi? ... " + Book.areRatingsArraysEqual(arr1, null));
    }

    /**
     
     */
    private static void runStatsMenu() {
        System.out.println("\n--- Istatistik Alt Menusu ---");
        System.out.println("1. Tum Istatistikleri Goster (Ragged Yapi)");
        System.out.println("2. Yila Gore Toplam Kullanim");
        System.out.println("3. En Yogun Ceyregi Bul");
        System.out.println("4. Yillik Ozet Raporu");
        System.out.print("Alt secim: ");

        int subChoice = getUserChoice();
        switch (subChoice) {
            case 1:
                myStats.printFullStats();
                break;
            case 2:
                System.out.print("Hangi yilin toplamini gormek istersiniz? (Indeks 0-2): ");
                int yearIndex = getUserChoice();
                myStats.getTotalUsageForYear(yearIndex);
                break;
            case 3:
                myStats.findBusiestQuarter();
                break;
            case 4:
                myStats.printYearlySummary();
                break;
            default:
                System.out.println("Gecersiz alt menu secimi.");
        }
    }
    
    /**
    
     */
    private static void demonstrateArrayListBridge() {
      
        ArrayList<Book> bookList = myLibrary.convertToArraylist();
        
        
        System.out.println("\nArrayList'e yeni bir 'Test Kitabi' ekleniyor...");
        bookList.add(new Book("Test Kitabi", "Test Yazar", 2025, new int[]{5}, new ArrayList<>()));
        System.out.println("ArrayList'in yeni boyutu (size): " + bookList.size());
        
       
        myLibrary.convertFromArrayList(bookList);
        
        System.out.println("\nAna envanter listesi guncellendi. Dogrulamak icin '2) Kitaplari Listele' secenegini kullanin.");
    }

    /**
     */
    private static void observeVectorCapacity() {
        System.out.println("--- Vector Kapasite Gozlemi ---");
        Vector<String> myVector = new Vector<>(2, 2); 
        
        System.out.println("Baslangic: size() = " + myVector.size() + ", capacity() = " + myVector.capacity());
        
        System.out.println("1. Eleman ekleniyor...");
        myVector.add("Eleman 1");
        System.out.println("Durum: size() = " + myVector.size() + ", capacity() = " + myVector.capacity());
        
        System.out.println("2. Eleman ekleniyor...");
        myVector.add("Eleman 2");
        System.out.println("Durum: size() = " + myVector.size() + ", capacity() = " + myVector.capacity());
        
        System.out.println("3. Eleman ekleniyor (Kapasite asilacak)...");
        myVector.add("Eleman 3");
        System.out.println("Durum: size() = " + myVector.size() + ", capacity() = " + myVector.capacity() + " (Kapasite artti!)");
        
        System.out.println("\nensureCapacity(20) cagiriliyor...");
        myVector.ensureCapacity(20);
        System.out.println("Son Durum: size() = " + myVector.size() + ", capacity() = " + myVector.capacity());
        
        System.out.println("Vector, ArrayList'e benzer ancak 'thread-safe' (eszamanlilik korumali) oldugu icin daha yavastir. Genellikle ArrayList tercih edilir.");
    }
}