package com.example.finalproj_doctor.Ui.Login;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Login;
import com.example.finalproj_doctor.Model.Pojo.Doctor_pojo;
import com.example.finalproj_doctor.Model.Response_signup;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Repository {

    MutableLiveData<String> responsee = new MutableLiveData<>();
    Doctor_pref doctor_pref;
    MutableLiveData<String> getloggedin = new MutableLiveData<>();

    public void Login(Login login , final Context context){
        Client.getInstance().retrofitApi.Login(login).enqueue(new Callback<Response_signup>() {
            @Override
            public void onResponse(Call<Response_signup> call, Response<Response_signup> response) {
                if (response.isSuccessful()){
                    responsee.setValue("تم تسجيل الدخول بنجاح");

                    doctor_pref = new Doctor_pref(context , "Data");
                    doctor_pref.set_Token(response.body().gettoken());
                    get_loggedin(context , response.body().gettoken());

                }else {
                    responsee.setValue("تاكد من صحة البيانات");
                }

            }

            @Override
            public void onFailure(Call<Response_signup> call, Throwable t) {

            }
        });
    }

    public void get_loggedin(final Context context , String Token){
        Client.getInstance().retrofitApi.signin_token(Token).enqueue(new Callback<Doctor_pojo>() {
            @Override
            public void onResponse(Call<Doctor_pojo> call, Response<Doctor_pojo> response) {
                if (response.isSuccessful()){
                    getloggedin.setValue("true");
                    doctor_pref = new Doctor_pref(context , "Data");
                    doctor_pref.setData(response.body().getData());
                }else {
                    getloggedin.setValue("false");
                }
            }

            @Override
            public void onFailure(Call<Doctor_pojo> call, Throwable t) {

            }
        });
    }

}
