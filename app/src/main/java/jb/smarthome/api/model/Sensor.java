package jb.smarthome.api.model;

public class Sensor {
    private String name;
    private boolean detection;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDetection() {
        return detection;
    }

    public void setDetection(boolean detection) {
        this.detection = detection;
    }

    public Sensor(String name, boolean detection) {

        this.name = name;
        this.detection = detection;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "name='" + name + '\'' +
                ", detection=" + detection +
                '}';
    }
}
