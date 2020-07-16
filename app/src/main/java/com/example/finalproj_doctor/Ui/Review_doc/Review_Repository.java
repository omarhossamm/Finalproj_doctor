package com.example.finalproj_doctor.Ui.Review_doc;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Pojo.Review_pojo;
import com.example.finalproj_doctor.Model.Review;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Review_Repository {

    Doctor_pref doctor_pref;
    MutableLiveData<List<Review>> mutableLiveData = new MutableLiveData<>();

    public void Get_Reviews(Context context) {
        doctor_pref = new Doctor_pref(context , "Data");
        Client.getInstance().retrofitApi.getreviews(doctor_pref.getData().get_id()).enqueue(new Callback<Review_pojo>() {
            @Override
            public void onResponse(Call<Review_pojo> call, Response<Review_pojo> response) {

                mutableLiveData.setValue(response.body().getData());

            }

            @Override
            public void onFailure(Call<Review_pojo> call, Throwable t) {

            }
        });

    }

}
