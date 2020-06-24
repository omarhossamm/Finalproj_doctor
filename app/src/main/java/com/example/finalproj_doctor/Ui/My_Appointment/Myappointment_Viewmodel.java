package com.example.finalproj_doctor.Ui.My_Appointment;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Schedule;

import java.util.List;

public class Myappointment_Viewmodel extends ViewModel {

    Myappointment_Repository myappointment_repository = new Myappointment_Repository();

    public void Get_appointments(Context context){
        myappointment_repository.Get_appointments(context);
    }

    MutableLiveData<List<Schedule>> Get_response(){
        return myappointment_repository.scheduleMutableLiveData;
    }


}
