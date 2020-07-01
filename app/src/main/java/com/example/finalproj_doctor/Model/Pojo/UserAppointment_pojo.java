package com.example.finalproj_doctor.Model.Pojo;

import com.example.finalproj_doctor.Model.UsersAppointment_model;

import java.util.List;

public class UserAppointment_pojo {

    private boolean success;
    private int count;
    private List<UsersAppointment_model> data;

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

    public List<UsersAppointment_model> getData() {
        return data;
    }

    public void setData(List<UsersAppointment_model> data) {
        this.data = data;
    }
}
