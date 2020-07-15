package com.example.finalproj_doctor.Ui.Qr_Scan;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Pojo.Scanqr_Pojo;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Qrscan_Repository {

    Doctor_pref doctor_pref;
    MutableLiveData<Scanqr_Pojo> responsee = new MutableLiveData<>();

    public void Scanqr(Context context , String appointment_id){
        doctor_pref = new Doctor_pref(context , "Data");
        Client.getInstance().retrofitApi.Scan_qr(appointment_id , doctor_pref.get_Token()).enqueue(new Callback<Scanqr_Pojo>() {
            @Override
            public void onResponse(Call<Scanqr_Pojo> call, Response<Scanqr_Pojo> response) {
                responsee.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Scanqr_Pojo> call, Throwable t) {

            }
        });

    }
}
