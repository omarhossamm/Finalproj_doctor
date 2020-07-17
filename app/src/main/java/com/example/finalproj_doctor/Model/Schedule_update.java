package com.example.finalproj_doctor.Model;

public class Schedule_update {

    private String bookSystem , day;
    private long startedAt , endedAt;
    private double sessionCost;

    private boolean success;
    private Schedule data;

    public Schedule_update(String bookSystem, String day, long startedAt, long endedAt, double sessionCost) {
        this.bookSystem = bookSystem;
        this.day = day;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.sessionCost = sessionCost;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Schedule getData() {
        return data;
    }

    public void setData(Schedule data) {
        this.data = data;
    }
}
