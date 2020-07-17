package com.example.finalproj_doctor.Ui.Appointment_edit;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Ui.My_Appointment.Myappointment_Repository;

import java.util.List;

public class Appointment_Viewmodel extends ViewModel {


    Appointment_Repository myappointment_repository = new Appointment_Repository();

    public void Get_appointments(Context context){
        myappointment_repository.Get_appointments(context);
    }

    MutableLiveData<List<Schedule>> Get_response(){
        return myappointment_repository.scheduleMutableLiveData;
    }



}
