package com.example.finalproj_doctor.Ui.Users_appointment;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Pojo.UserAppointment_pojo;
import com.example.finalproj_doctor.Model.UsersAppointment_model;
import com.example.finalproj_doctor.Network.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Usersappointment_Repository {

    MutableLiveData<List<UsersAppointment_model>> userAppointmentPojoMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> asd = new MutableLiveData<>();

    public void Users_Schedule(String schedule_id){


        Client.getInstance().retrofitApi.Getusers_appointment(schedule_id).enqueue(new Callback<UserAppointment_pojo>() {
            @Override
            public void onResponse(Call<UserAppointment_pojo> call, Response<UserAppointment_pojo> response) {
                if (response.body().isSuccess()) {
                    asd.setValue("ok");
                }else asd.setValue("no");
                userAppointmentPojoMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<UserAppointment_pojo> call, Throwable t) {

            }
        });


    }

}
