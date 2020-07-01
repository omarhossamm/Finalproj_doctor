package com.example.finalproj_doctor.Ui.Location;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Pojo.Doctor_pojo;
import com.example.finalproj_doctor.Model.Response_signup;
import com.example.finalproj_doctor.Model.Sign_up;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Location_Repository {

    MutableLiveData<String> responsee = new MutableLiveData<>();
    Doctor_pref doctor_pref;

    public void sign_up(final Context context , Sign_up sign_up){

        Client.getInstance().retrofitApi.sign_up(sign_up).enqueue(new Callback<Response_signup>() {
            @Override
            public void onResponse(Call<Response_signup> call, Response<Response_signup> response) {
                if (response.isSuccessful()){

                    responsee.setValue("تم التسجيل بنجاح");
                    doctor_pref = new Doctor_pref(context , "Data");
                    doctor_pref.set_Token(response.body().gettoken());
                    login(context , response.body().gettoken());

                }else {

                    responsee.setValue("لم يتم التسجيل");

                }

            }

            @Override
            public void onFailure(Call<Response_signup> call, Throwable t) {

            }
        });

    }

    public void login(final Context context , final String token){
        Client.getInstance().retrofitApi.signin_token(token).enqueue(new Callback<Doctor_pojo>() {
            @Override
            public void onResponse(Call<Doctor_pojo> call, Response<Doctor_pojo> response) {

                doctor_pref = new Doctor_pref(context , "Data");
                doctor_pref.setData(response.body().getData());
                doctor_pref.setProfile(response.body().getData().getPhoto());

            }

            @Override
            public void onFailure(Call<Doctor_pojo> call, Throwable t) {

            }
        });
    }

}
