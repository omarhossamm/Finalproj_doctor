package com.example.finalproj_doctor.Model.Pojo;

import com.example.finalproj_doctor.Model.Doctor;
import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Model.UsersAppointment_model;

import java.util.List;

public class Scanqr_Pojo {

    private boolean success;
    private UsersAppointment_model appointment;
    private Doctor doctor;
    private Schedule schedule;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UsersAppointment_model getAppointment() {
        return appointment;
    }

    public void setAppointment(UsersAppointment_model appointment) {
        this.appointment = appointment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
