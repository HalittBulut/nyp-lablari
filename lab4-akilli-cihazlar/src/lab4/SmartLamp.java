package lab4; 

public class SmartLamp extends Device {
    private int brightness;

    public SmartLamp(String name, int battery, int brightness) {
        super(name, battery);
        this.brightness = (brightness >= 0 && brightness <= 100) ? brightness : 50;
    }

    public void setBrightness(int value) {
        if (value >= 0 && value <= 100) {
            this.brightness = value;
            System.out.println(getName() + " parlakligi " + value + " olarak ayarlandi.");
            System.out.println("Hata: Gecersiz parlaklik degeri (0-100 arasi olmali).");
        }
    }

    @Override
    public String getStatus() {
        return super.getStatus() + ", Parlaklik: " + this.brightness;
    }
}