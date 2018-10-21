package jb.smarthome;

public class Temperature  {
    private String room;
    private String degrees;
    private String humidity;

    public Temperature(String room, String degrees, String humidity) {
        this.room = room;
        this.degrees = degrees;
        this.humidity = humidity;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
