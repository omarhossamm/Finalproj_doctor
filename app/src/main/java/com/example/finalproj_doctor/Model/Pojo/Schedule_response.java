package com.example.finalproj_doctor.Model.Pojo;

import com.example.finalproj_doctor.Model.Schedule;

import java.util.List;

public class Schedule_response {

    private boolean success;
    private int count;
    private List<Schedule> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Schedule> getData() {
        return data;
    }

    public void setData(List<Schedule> data) {
        this.data = data;
    }
}
