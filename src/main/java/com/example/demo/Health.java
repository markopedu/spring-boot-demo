package com.example.demo;

public class Health {
    public Health(String status, String time) {
        this.status = status;
        this.time = time;
    }

    private String status;

    private String time;

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }
}
