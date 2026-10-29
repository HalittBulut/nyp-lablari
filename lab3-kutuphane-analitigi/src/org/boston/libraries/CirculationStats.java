package org.boston.libraries;

/**
 */
public class CirculationStats {
    private int[][] usageStats;

    /**
     * Ornek istatistik verileriyle baslatir.
     */
    public CirculationStats() {
        loadSampleStats();
    }

    private void loadSampleStats() {
       
        usageStats = new int[3][];

       
       usageStats[0] = new int[]{150, 200, 180, 220};
       
        
        usageStats[1] = new int[]{210, 240, 190};
        
        
        usageStats[2] = new int[]{230, 250, 300, 280};
        
       System.out.println("Istatistik verisi (Ragged Array) yuklendi.");
    }
    /**
    * Tum istatistik tablosunu ve satir uzunluklarini yazdirir.
     */
    public void printFullStats() {
       System.out.println("--- Yillik Kullanim Istatistikleri (Duzensiz Dizi) ---");
       if (usageStats == null) {
           System.out.println("Veri yok.");
           return;
       }
       
       for (int year = 0; year < usageStats.length; year++) {
           System.out.print("Yil " + (2023 + year) + " (Satir " + year + "): ");
           if (usageStats[year] != null) {
                System.out.print("[ ");
               for (int quarterData : usageStats[year]) {
                   System.out.print(quarterData + " ");
               }
                System.out.println("] -> (Toplam " + usageStats[year].length + " ceyrek veri)");
           } else {
                System.out.println("[VERI YOK]");
           }
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * @param yearIndex Satir indeksi (0'dan baslar).
     */
    public void getTotalUsageForYear(int yearIndex) {
        if (usageStats == null || yearIndex < 0 || yearIndex >= usageStats.length || usageStats[yearIndex] == null) {
            System.out.println("Gecersiz yil indeksi: " + yearIndex);
            return;
        }

        int total = 0;
        for (int usage : usageStats[yearIndex]) {
            total += usage;
        }
        System.out.println((2023 + yearIndex) + " yili (Indeks: " + yearIndex + ") toplam kullanimi: " + total);
    }

    /**
     *
     */
    public void findBusiestQuarter() {
        if (usageStats == null) {
            System.out.println("Veri yok.");
            return;
        }

        int maxUsage = -1;
        int busiestYear = -1;
        int busiestQuarter = -1;

        for (int y = 0; y < usageStats.length; y++) {
            if (usageStats[y] != null) {
                for (int q = 0; q < usageStats[y].length; q++) {
                    if (usageStats[y][q] > maxUsage) {
                        maxUsage = usageStats[y][q];
                        busiestYear = y;
                        busiestQuarter = q;
  }
    }
  }
  }

        if (maxUsage != -1) {
            System.out.println("En yogun ceyrek bulundu:");
            System.out.printf("Yil: %d (Indeks: %d), Ceyrek: %d (Indeks: %d), Kullanim: %d\n",
                    (2023 + busiestYear), busiestYear, (busiestQuarter + 1), busiestQuarter, maxUsage);
        } else {
            System.out.println("Kayit bulunamadi.");
       }
    }

    /**
     
     */
    public void printYearlySummary() {
        System.out.println("--- Yillik Ozet Raporu ---");
        if (usageStats == null) {
            System.out.println("Veri yok.");
            return;
        }
        for (int i = 0; i < usageStats.length; i++) {
            getTotalUsageForYear(i); // Zaten yazdirma islemi yapiyor
        }
        System.out.println("---------------------------");
    }
}