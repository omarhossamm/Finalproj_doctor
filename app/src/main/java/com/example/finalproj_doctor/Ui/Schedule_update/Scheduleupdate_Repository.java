package com.example.finalproj_doctor.Ui.Schedule_update;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Pojo.Schedule_response;
import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Model.Schedule_update;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Scheduleupdate_Repository {

    Doctor_pref doctor_pref;
    MutableLiveData<String> respnsee = new MutableLiveData<>();

    public void Delete_Schedule(String schedule_id , Context context){
        doctor_pref = new Doctor_pref(context , "Data");
        Client.getInstance().retrofitApi.Delete_schedule(schedule_id , doctor_pref.get_Token()).enqueue(new Callback<Schedule_response>() {
            @Override
            public void onResponse(Call<Schedule_response> call, Response<Schedule_response> response) {
                if(response.isSuccessful()){
                    respnsee.setValue("Schedule added");
                }
                else {
                    respnsee.setValue("Failed");
                }
            }

            @Override
            public void onFailure(Call<Schedule_response> call, Throwable t) {

            }
        });
    }


    public void Update_schedule(String schedule_id , Context context , Schedule_update schedule){
        doctor_pref = new Doctor_pref(context , "Data");

        Client.getInstance().retrofitApi.Update_schedule(schedule_id , doctor_pref.get_Token() , schedule).enqueue(new Callback<Schedule_update>() {
            @Override
            public void onResponse(Call<Schedule_update> call, Response<Schedule_update> response) {
                if (response.isSuccessful()){
                    respnsee.setValue("Success");
                }else {
                    respnsee.setValue("Failed");
                }
            }

            @Override
            public void onFailure(Call<Schedule_update> call, Throwable t) {

            }
        });

    }

}
