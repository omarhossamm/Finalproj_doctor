package com.example.finalproj_doctor.Ui.Appointment_edit;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Pojo.Schedule_response;
import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Appointment_Repository {

    MutableLiveData<List<Schedule>> scheduleMutableLiveData = new MutableLiveData<>();

    Doctor_pref doctor_pref;

    public void Get_appointments(Context context){

        doctor_pref = new Doctor_pref(context , "Data");

        Client.getInstance().retrofitApi.Getdoctor_Schedule(doctor_pref.getData().get_id()).enqueue(new Callback<Schedule_response>() {
            @Override
            public void onResponse(Call<Schedule_response> call, Response<Schedule_response> response) {

                scheduleMutableLiveData.setValue(response.body().getData());

            }

            @Override
            public void onFailure(Call<Schedule_response> call, Throwable t) {

            }
        });
    }

}
