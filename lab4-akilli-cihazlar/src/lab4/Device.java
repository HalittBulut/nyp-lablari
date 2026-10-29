package lab4; 

public class Device {
    // Özellikler
    private String name;
    protected int battery;
    private boolean isOn;

    // Kurucu Metot
    public Device(String name, int battery) {
        this.name = name;
        if (battery >= 0 && battery <= 100) {
            this.battery = battery;
        } else {
            System.out.println("Hata: Gecersiz pil seviyesi! Pil 50 olarak ayarlandi.");
            this.battery = 50;
        }
        this.isOn = false;
    }

    public String getName() { return name; }
    public int getBattery() { return battery; }
    public boolean isOn() { return isOn; }

    protected void setBattery(int newBattery) {
        if (newBattery > 100) {
            this.battery = 100;
        } else if (newBattery < 0) {
            this.battery = 0;
            if (this.isOn) {
                turnOffInternal();
                System.out.println(this.name + " - Pil seviyesi 0 olduğu için otomatik kapandi!");
            }
        } else {
            this.battery = newBattery;
        }
    }

    public void turnOn() {
        if (this.isOn) {
            System.out.println(this.name + " zaten acik!");
        } else {
            this.isOn = true;
            System.out.println(this.name + " acildi.");
        }
    }

    public void turnOff() {
        turnOffInternal();
        if (!this.isOn) {
            System.out.println(this.name + " zaten kapali!");
        }
    }

    private void turnOffInternal() {
        if (this.isOn) {
            this.isOn = false;
        }
    }

    public String getStatus() {
        String status = this.isOn ? "Acik" : "Kapali";
        return "[" + (this.getClass().getSimpleName().equals("SmartLamp") ? "Lamba" :
                     (this.getClass().getSimpleName().equals("RobotVacuum") ? "Supurge" : "Cihaz"))
               + "] " + this.name + " - Pil: " + this.battery + "%, Durum: " + status;
    }
}