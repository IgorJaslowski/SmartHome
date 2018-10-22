package jb.smarthome.api.model;

public class Sensor {
    private String name;
    private Boolean detection;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDetection() {
        return detection;
    }

    public void setDetection(Boolean detection) {
        this.detection = detection;
    }

    public Sensor(String name, Boolean detection) {

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
