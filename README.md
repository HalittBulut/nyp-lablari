# Nesneye Yönelimli Programlama Lab'ları

Nesneye Yönelimli Programlama dersinde Java ile yaptığım laboratuvar çalışmaları.

![Java](https://img.shields.io/badge/Java-ED8B00?logo=openjdk&logoColor=white)

## Lab 3 — Kütüphane Analitiği

[`lab3-kutuphane-analitigi/`](lab3-kutuphane-analitigi/src/org/boston/libraries)

Bir kütüphanenin kitap envanterini yöneten ve ödünç alma istatistiklerini çıkaran konsol uygulaması.

- `Book`: puanlar ve etiketlerle kitap nesnesi, `Cloneable` ile güvenli derin kopya
- `Library`: envanterde arama, sıralama ve koleksiyon işlemleri
- `CirculationStats`: iki boyutlu dizide ödünç alma istatistikleri
- `LibraryApp`: menülü konsol arayüzü, hatalı girişe karşı kontrol

## Lab 4 — Akıllı Ev Cihazları

[`lab4-akilli-cihazlar/`](lab4-akilli-cihazlar/src/lab4)

Kalıtım ve kapsülleme üzerine akıllı ev cihazı simülasyonu.

- `Device`: ad, batarya ve açık/kapalı durumu olan temel sınıf
- `SmartLamp`: parlaklık ayarı (0–100 arası doğrulama)
- `RobotVacuum`: temizlik modu
- `Lab4`: kullanıcıdan cihaz bilgilerini alan konsol uygulaması

## Çalıştırma

Her lab bağımsız bir Java projesidir. `src` klasörünü bir IDE'de (NetBeans, IntelliJ) açıp ana sınıfı çalıştırabilirsin:

```bash
javac -d out $(find lab4-akilli-cihazlar/src -name "*.java")
java -cp out lab4.Lab4
```
