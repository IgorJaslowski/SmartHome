package jb.smarthome.api.model;

import java.util.ArrayList;

public class TemperatureResponse {
    private ArrayList<Temperature> temperatures;
    private String avgTemperature;
    private String avgHumidity;

    public TemperatureResponse(ArrayList<Temperature> temperatures, String avgTemperature, String avgHumidity) {
        this.temperatures = temperatures;
        this.avgTemperature = avgTemperature;
        this.avgHumidity = avgHumidity;
    }

    public ArrayList<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(ArrayList<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    public String getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(String avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public String getAvgHumidity() {
        return avgHumidity;
    }

    public void setAvgHumidity(String avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    @Override
    public String toString() {
        return "TemperatureResponse{" +
                "temperatures=" + temperatures +
                ", avgTemperature='" + avgTemperature + '\'' +
                ", avgHumidity='" + avgHumidity + '\'' +
                '}';
    }
}
