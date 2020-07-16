package com.example.finalproj_doctor.Ui.Personal_information;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Pojo.Doctor_pojo;
import com.example.finalproj_doctor.Model.Update_doc;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Personal_Repository {

    Doctor_pref doctor_pref;
    MutableLiveData<String> suc = new MutableLiveData<>();

    public void Update_doc(Context context , Update_doc update_doc){
        doctor_pref = new Doctor_pref(context , "Data");

        Client.getInstance().retrofitApi.Update_dr(doctor_pref.getData().get_id() , doctor_pref.get_Token() , update_doc).enqueue(new Callback<Doctor_pojo>() {
            @Override
            public void onResponse(Call<Doctor_pojo> call, Response<Doctor_pojo> response) {
                if (response.isSuccessful()){
                    suc.setValue("Success");
                    doctor_pref.setData(response.body().getData());
                }
                else {suc.setValue("Failed");
                }
            }
            @Override
            public void onFailure(Call<Doctor_pojo> call, Throwable t) {
            }
    });

        }

    }
