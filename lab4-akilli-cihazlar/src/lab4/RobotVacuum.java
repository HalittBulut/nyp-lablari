package lab4; 

public class RobotVacuum extends Device {
    private String mode;

    public RobotVacuum(String name, int battery, String mode) {
        super(name, battery);
        setMode(mode);
    }

    public void setMode(String mode) {
        String lowerMode = mode.toLowerCase();
        if (lowerMode.equals("eco") || lowerMode.equals("turbo") || lowerMode.equals("silent")) {
            this.mode = lowerMode;
        } else {
            this.mode = "eco";
            System.out.println("Uyarı: Gecersiz mod. " + getName() + " modu 'eco' olarak ayarlandı.");
        }
    }

    @Override
    public void turnOn() {
        if (getBattery() < 20) {
            System.out.println(getName() + " pil seviyesi dusuk (" + getBattery() + "%) olduğu icin acilamadi!");
            return;
        }
        if (isOn()) {
            super.turnOn();
            return;
        }
        super.turnOn();
        if (this.mode.equals("turbo")) {
            System.out.println("Turbo modunda calistigi icin " + getName() + " pil seviyesi 10 azaltildi.");
            setBattery(getBattery() - 10);
        }
    }

    @Override
    public String getStatus() {
        return super.getStatus() + ", Mod: " + this.mode;
    }
}