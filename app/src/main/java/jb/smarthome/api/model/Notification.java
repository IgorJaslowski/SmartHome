package jb.smarthome.api.model;

public class Notification {
    private String status;
    private String description;
    private String date;

    public Notification(String status, String description, String date) {
        this.status = status;
        this.description = description;
        this.date = date;
    }

    public Notification(String description, String date) {
        this.description = description;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
