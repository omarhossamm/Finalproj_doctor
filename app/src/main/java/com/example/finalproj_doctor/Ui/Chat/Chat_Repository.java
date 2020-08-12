package com.example.finalproj_doctor.Ui.Chat;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Chat;
import com.example.finalproj_doctor.Model.Doctor;
import com.example.finalproj_doctor.Model.Pojo.Chat_pojo;
import com.example.finalproj_doctor.Model.RoomInfo;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat_Repository {

    MutableLiveData<List<Chat>> responsee = new MutableLiveData<>();
    Doctor_pref doctor_pref;

    public void Get_chatconv(final Context context){

        doctor_pref = new Doctor_pref(context , "Data");
        Client.getInstance().retrofitApi.Get_chats(doctor_pref.get_Token()).enqueue(new Callback<Chat_pojo>() {
            @Override
            public void onResponse(Call<Chat_pojo> call, Response<Chat_pojo> response) {
           responsee.setValue(response.body().getConversation());

            }

            @Override
            public void onFailure(Call<Chat_pojo> call, Throwable t) {

            }
        });

    }

}
