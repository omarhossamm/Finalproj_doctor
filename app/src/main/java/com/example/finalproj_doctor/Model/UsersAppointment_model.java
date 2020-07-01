package com.example.finalproj_doctor.Model;

import java.util.Date;
import java.util.List;

public class UsersAppointment_model {

    private String patientName ;
    private Object _id , schedule;
    private int phone , age , patientNumber ;
    private Date createdAt;
    private boolean sessionBegun;


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public Object getSchedule() {
        return schedule;
    }

    public void setSchedule(Object schedule) {
        this.schedule = schedule;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(int patientNumber) {
        this.patientNumber = patientNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isSessionBegun() {
        return sessionBegun;
    }

    public void setSessionBegun(boolean sessionBegun) {
        this.sessionBegun = sessionBegun;
    }
}
