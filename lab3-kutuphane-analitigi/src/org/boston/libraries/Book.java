package org.boston.libraries;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Kitap nesnesini temsil eder.
 * Puanlar ve etiketler gibi alanlari yonetir.
 * Guvenli kopya (deep copy) icin Cloneable arayuzunu uygular.
 */
public class Book implements Cloneable {

    private String title;
    private String author;
    private int year;
    private int[] ratings; // 1 boyutlu puan dizisi
    private ArrayList<String> tags; // Opsiyonel etiketler

    // 1. Kurucular (Constructors)

    /**
     * Tam parametreli kurucu.
     */
    public Book(String title, String author, int year, int[] ratings, ArrayList<String> tags) {
        this.title = title;
        this.author = author;
        this.year = year;
        // Diziyi kopyalayarak al (Encapsulation icin onemli)
        this.ratings = ratings != null ? ratings.clone() : new int[0];
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
    }

    /**
     * Parametresiz kurucu (Makul varsayilanlar).
     */
    public Book() {
        this.title = "Bilinmeyen Baslik";
        this.author = "Bilinmeyen Yazar";
        this.year = 2000;
        this.ratings = new int[0]; // Bos bir dizi, null degil
        this.tags = new ArrayList<>();
    }

    // 2. Temel Metotlar

    /**
     * Kitabin ortalama puanini hesaplar.
     * @return Ortalama puan (double). Puan yoksa 0.0 doner.
     */
    public double calculateAverageRating() {
        if (ratings == null || ratings.length == 0) {
            return 0.0;
        }
        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return (double) sum / ratings.length;
    }

    /**
     * Kitaba yeni bir etiket ekler.
     */
    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Kitaptan bir etiketi kaldirir.
     */
    public void removeTag(String tag) {
        tags.remove(tag);
    }

    /**
     * Iki Book nesnesinin "puanlar" dizisinin iceriginin esit olup olmadigini kontrol eder.
     * @param other Karsilastirilacak diger Book nesnesi.
     * @return Puan dizileri icerik olarak esitse true.
     */
    public boolean areRatingsEqual(Book other) {
        if (other == null) {
            return false;
        }
        // Statik yardimci metodu kullan
        return Book.areRatingsArraysEqual(this.ratings, other.ratings);
    }
    
    // equals() ve toString() metotlari iyi bir pratiktir
    
    @Override
    public String toString() {
        return String.format("Kitap: %s, Yazar: %s, Yil: %d, Ortalama Puan: %.2f, Puanlar: %s",
                title, author, year, calculateAverageRating(), Arrays.toString(ratings));
    }

    // 3. Statik Yardimcilar ve Kopyalama

    /**
     * Iki puan dizisinin icerik olarak esit olup olmadigini test eder.
     * @param arr1 Birinci dizi
     * @param arr2 Ikinci dizi
     * @return Icerik esitse true.
     */
    public static boolean areRatingsArraysEqual(int[] arr1, int[] arr2) {
        // Ikisi de null ise esit kabul edilebilir
        if (arr1 == null && arr2 == null) {
            return true;
        }
        // Sadece biri null ise esit degildir
        if (arr1 == null || arr2 == null) {
            return false;
        }
        // Uzunluklar farkliysa esit degildir
        if (arr1.length != arr2.length) {
            return false;
        }
        // Eleman eleman kontrol (Erken cikis)
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false; // Ilk farkta cik
            }
        }
        return true; // Fark bulunamadi
    }

    /**
     * Bir Book nesnesinin guvenli (derin) kopyasini uretir.
     * @param original Kopyalanacak orijinal kitap.
     * @param useClone 'true' ise clone() metodunu, 'false' ise manuel (elle) kopyalamayi kullanir.
     * @return Orijinalin derin kopyasi olan yeni bir Book nesnesi.
     */
    public static Book createSafeCopy(Book original, boolean useClone) {
        if (original == null) {
            return null;
        }
        
        if (useClone) {
            try {
                // 7. madde: clone() yaklasimi
                return (Book) original.clone();
            } catch (CloneNotSupportedException e) {
                // Book, Cloneable uyguladigi icin bu hata olmamali
                System.err.println("Klonlama hatasi: " + e.getMessage());
                return null; // Veya manuel kopyalamaya dus
            }
        } else {
            // 7. madde: Elle kopya yaklasimi
            Book copy = new Book();
            copy.title = original.title;
            copy.author = original.author;
            copy.year = original.year;
            
            // Dizi ve ArrayList icin 'deep copy' yapilmali
            copy.ratings = original.ratings.clone(); // Diziler icin en kolay derin kopya
            copy.tags = new ArrayList<>(original.tags); // ArrayList'in kopya kurucusu
            
            return copy;
        }
    }

    /**,
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 1. super.clone() ile yuzeysel (shallow) kopya al
        Book cloned = (Book) super.clone();
        cloned.ratings = this.ratings.clone();
        cloned.tags = new ArrayList<>(this.tags);
        
        return cloned;
    }

    // Getter/Setter'lar (Gerektigi kadar)
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public int[] getRatings() { return ratings.clone(); } // Kopyasini ver
}