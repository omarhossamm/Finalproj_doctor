package com.example.finalproj_doctor.Ui.Users_appointment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.UsersAppointment_model;

import java.util.List;

public class Usersappointment_Viewmodel extends ViewModel {

    Usersappointment_Repository usersappointment_repository = new Usersappointment_Repository();

    public void Getschedule_usere(String schedule_id){
        usersappointment_repository.Users_Schedule(schedule_id);
    }

    public MutableLiveData<List<UsersAppointment_model>> getresponse(){
        return usersappointment_repository.userAppointmentPojoMutableLiveData;
    }


    public MutableLiveData<String> asd(){
        return usersappointment_repository.asd;
    }

}
