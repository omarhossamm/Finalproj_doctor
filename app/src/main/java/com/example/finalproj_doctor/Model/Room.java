package com.example.finalproj_doctor.Model;

public class Room {

    private String userId , doctorId;

    public Room(String userId, String doctorId) {
        this.userId = userId;
        this.doctorId = doctorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
