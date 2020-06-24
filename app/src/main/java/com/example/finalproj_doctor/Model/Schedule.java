package com.example.finalproj_doctor.Model;

import java.util.List;

public class Schedule {

    private String bookSystem, day;
    private int currentNumber, totalNumber;
    private long startedAt, endedAt;
    private double sessionCost;
    private Object _id;

    public Schedule(String bookSystem, String day, int currentNumber, int totalNumber, long startedAt, long endedAt, double sessionCost) {
        this.bookSystem = bookSystem;
        this.day = day;
        this.currentNumber = currentNumber;
        this.totalNumber = totalNumber;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.sessionCost = sessionCost;
    }

    public String getBookSystem() {
        return bookSystem;
    }

    public void setBookSystem(String bookSystem) {
        this.bookSystem = bookSystem;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(long startedAt) {
        this.startedAt = startedAt;
    }

    public long getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(long endedAt) {
        this.endedAt = endedAt;
    }

    public double getSessionCost() {
        return sessionCost;
    }

    public void setSessionCost(double sessionCost) {
        this.sessionCost = sessionCost;
    }

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }
}