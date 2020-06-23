package com.example.finalproj_doctor.Ui.Put_Appointment;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Network.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Putappointment_Repository {

    MutableLiveData<String> respnsee = new MutableLiveData<>();

    public void Post_Schedule(String Token , Schedule schedule){

        Client.getInstance().retrofitApi.Post_Schedule(Token , schedule).enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                if(response.isSuccessful()){
                    respnsee.setValue("تم اضافة موعد بنجاح");
                }
                else {
                    respnsee.setValue("لم ينجح");
                }
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {

                respnsee.setValue("error");

            }
        });

    }

}
