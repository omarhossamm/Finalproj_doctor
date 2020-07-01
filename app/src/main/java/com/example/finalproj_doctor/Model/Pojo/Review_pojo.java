package com.example.finalproj_doctor.Model.Pojo;

import com.example.finalproj_doctor.Model.Review;

import java.util.List;

public class Review_pojo {

    private boolean success;
    private int count;
    private List<Review> data;

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

    public List<Review> getData() {
        return data;
    }

    public void setData(List<Review> data) {
        this.data = data;
    }
}
