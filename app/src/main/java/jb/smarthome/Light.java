package jb.smarthome;

public class Light {
    private String room;
    private Boolean isOn;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Boolean getOn() {
        return isOn;
    }

    public void setOn(Boolean on) {
        isOn = on;
    }

    public Light(String room, Boolean isOn) {
        this.room = room;
        this.isOn = isOn;
    }
}

