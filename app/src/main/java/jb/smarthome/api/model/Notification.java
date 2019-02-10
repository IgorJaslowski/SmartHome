package jb.smarthome.api.model;


import java.io.Serializable;

public class Notification implements Serializable {
    private String type;
    private Object description;
    private String date;




    public Notification( Object description, String date,String type) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public Notification(Object description, String date) {
        this.description = description;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "type='" + type + '\'' +
                ", description=" + description +
                ", date='" + date + '\'' +
                '}';
    }
}

