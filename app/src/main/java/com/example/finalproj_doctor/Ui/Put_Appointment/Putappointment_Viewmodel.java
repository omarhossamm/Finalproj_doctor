package com.example.finalproj_doctor.Ui.Put_Appointment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Schedule;

public class Putappointment_Viewmodel extends ViewModel {

    Putappointment_Repository putappointment_repository = new Putappointment_Repository();

    public void Post_Schedule(String Token , Schedule schedule){
        putappointment_repository.Post_Schedule(Token , schedule);
    }

    public MutableLiveData<String> get_response(){
        return putappointment_repository.respnsee;
    }

}
