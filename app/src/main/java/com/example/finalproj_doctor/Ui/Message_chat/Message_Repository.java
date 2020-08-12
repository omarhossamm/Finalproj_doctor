package com.example.finalproj_doctor.Ui.Message_chat;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Message;
import com.example.finalproj_doctor.Model.Pojo.Message_Pojo;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message_Repository {

    Doctor_pref doctor_pref;
    MutableLiveData<List<Message>> responsee = new MutableLiveData();

    public void Get_conversation(Context context){
        doctor_pref = new Doctor_pref(context , "Data");
        Client.getInstance().retrofitApi.Get_conversation(doctor_pref.get_Token()).enqueue(new Callback<Message_Pojo>() {
            @Override
            public void onResponse(Call<Message_Pojo> call, Response<Message_Pojo> response) {
                responsee.setValue(response.body().getConversation());
            }

            @Override
            public void onFailure(Call<Message_Pojo> call, Throwable t) {

            }
        });
    }
}
