package jb.smarthome.api.model;

import com.google.firebase.database.DataSnapshot;

public class Notification {
    private String status;
    private Object description;
    private String date;

    public Notification(String status, Object description, String date) {
        this.status = status;
        this.description = description;
        this.date = date;
    }

    public Notification(Object description, String date) {
        this.description = description;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
