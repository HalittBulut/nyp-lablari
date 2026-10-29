package org.boston.libraries;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Kitap envanterini (Book[]) yoneten sinif.
 * Arama, siralama ve koleksiyon islemleri yapar.
 */
public class Library {

    private Book[] inventory; // Kitap envanteri

    /**
     * Kutuphaneyi bos bir envanterle baslatir.
     */
    public Library() {
        this.inventory = new Book[0]; // Baslangicta bos
    }

    /**
     * 1. Madde: Ornek verileri envantere yukler.
     */
    public void loadSampleBooks() {
        inventory = new Book[5]; // Dizi boyutunu ayarla
        
        inventory[0] = new Book("Dune", "Frank Herbert", 1965, new int[]{8, 9, 10, 10}, new ArrayList<>(Arrays.asList("sci-fi", "classic")));
        inventory[1] = new Book("1984", "George Orwell", 1949, new int[]{7, 8, 8, 9}, new ArrayList<>(Arrays.asList("dystopian", "classic")));
        inventory[2] = new Book("Yuzuklerin Efendisi", "J.R.R. Tolkien", 1954, new int[]{10, 10, 9}, new ArrayList<>(Arrays.asList("fantasy")));
        inventory[3] = new Book("Dune", "Frank Herbert", 1965, new int[]{5, 6}, new ArrayList<>(Arrays.asList("sci-fi"))); // Farkli puanlar
        inventory[4] = new Book("Suc ve Ceza", "Dostoyevski", 1866, new int[]{9, 9, 8, 10}, new ArrayList<>(Arrays.asList("classic", "psychological")));
        
        System.out.println(inventory.length + " adet ornek kitap yuklendi.");
    }

    /**
     * 2. Madde: Tum envanteri listeler.
     */
    public void listBooks() {
        if (inventory == null || inventory.length == 0) {
            System.out.println("Envanter bos.");
            return;
        }
        System.out.println("--- Kutuphane Envanteri ---");
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                System.out.println((i + 1) + ". " + inventory[i].toString());
            }
        }
        System.out.println("---------------------------");
    }

    // 3. Arama Metotlari (Overloading)

    /**
     * 3. Madde: Sadece basliga gore arar (Overload 1).
     * @param title Aranan baslik (buyuk/kucuk harf duyarsiz).
     * @return Eslesen kitaplarin listesi.
     */
    public ArrayList<Book> search(String title) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : inventory) {
            if (book != null && book.getTitle().equalsIgnoreCase(title)) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * 4. Madde: Baslik ve yila gore arar (Overload 2).
     * @param title Aranan baslik.
     * @param year Aranan yil.
     * @return Eslesen kitaplarin listesi.
     */
    public ArrayList<Book> search(String title, int year) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : inventory) {
            if (book != null && book.getTitle().equalsIgnoreCase(title) && book.getYear() == year) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * 5. Madde: Kosula gore arar (Or: Minimum ortalama puan) (Overload 3).
     * @param minRating Minimum ortalama puan esigi.
     * @return Eslesen kitaplarin listesi.
     */
    public ArrayList<Book> search(double minRating) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : inventory) {
            if (book != null && book.calculateAverageRating() >= minRating) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * 6. Madde: Envanteri ortalama puana gore Selection Sort ile siralar.
     */
    public void sortInventoryByRating() {
        if (inventory == null || inventory.length < 2) {
            System.out.println("Siralama icin yeterli kitap yok.");
            return;
        }

        int n = inventory.length;
        for (int i = 0; i < n - 1; i++) {
            // Minimum elemanin indeksini bul
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (inventory[j] != null && inventory[minIndex] != null) {
                    if (inventory[j].calculateAverageRating() < inventory[minIndex].calculateAverageRating()) {
                        minIndex = j;
                    }
                }
            }

            // Swap (Yer degistirme)
            Book temp = inventory[minIndex];
            inventory[minIndex] = inventory[i];
            inventory[i] = temp;
        }
        
        System.out.println("Kitaplar ortalama puana gore siralandi (Selection Sort).");
        // Ozet goster
        System.out.println("En Dusuk Puanli: " + inventory[0].getTitle() + " (Puan: " + inventory[0].calculateAverageRating() + ")");
        System.out.println("En Yuksek Puanli: " + inventory[n-1].getTitle() + " (Puan: " + inventory[n-1].calculateAverageRating() + ")");
    }
    
    /**
     * 10. Madde: Mevcut envanter dizisini (Book[]) bir ArrayList'e aktarir.
     * @return Kitaplari iceren yeni bir ArrayList.
     */
    public ArrayList<Book> convertToArraylist() {
        if (inventory == null) {
            return new ArrayList<>();
        }
        // Arrays.asList() sabit boyutlu bir liste verir.
        // Degistirilebilir bir ArrayList icin yeni bir tane olusturmaliyiz.
        ArrayList<Book> list = new ArrayList<>(Arrays.asList(inventory));
        
        System.out.println("\n--- Diziden ArrayList'e Donusum ---");
        System.out.println("Dizi boyutu (length): " + inventory.length);
        System.out.println("ArrayList boyutu (size): " + list.size());
        System.out.println("ArrayList'ler sadece Nesne (Object) turlerini tutar (orn: Book).");
        System.out.println("ArrayList'in 'capacity' (kapasitesi) genellikle 'size'indan buyuktur ve eleman eklendikce otomatik artar.");
        
        return list;
    }

    /**
     * @param bookList Kaynak ArrayList.
     */
    public void convertFromArrayList(ArrayList<Book> bookList) {
        if (bookList == null) {
            this.inventory = new Book[0];
            return;
        }
        this.inventory = bookList.toArray(new Book[0]);
        
        System.out.println("\n--- ArrayList'ten Diziye Donusum ---");
        System.out.println("ArrayList boyutu (size): " + bookList.size());
        System.out.println("Yeni Dizi boyutu (length): " + inventory.length);
    }
    public Book getFirstBook() {
        if (inventory != null && inventory.length > 0) {
            return inventory[0];
        }
        return null;
    }
}