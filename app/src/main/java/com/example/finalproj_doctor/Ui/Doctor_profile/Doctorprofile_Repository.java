package com.example.finalproj_doctor.Ui.Doctor_profile;

import android.content.ContentUris;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproj_doctor.Model.Doctor;
import com.example.finalproj_doctor.Model.Pojo.Doctor_pojo;
import com.example.finalproj_doctor.Model.Pojo.Review_pojo;
import com.example.finalproj_doctor.Model.Review;
import com.example.finalproj_doctor.Model.Upload_response;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctorprofile_Repository {

    Doctor_pref doctor_pref;
    MutableLiveData<Doctor> data = new MutableLiveData<>();
    MutableLiveData<String> responsee = new MutableLiveData<>();
    MutableLiveData<List<Review>> review = new MutableLiveData<>();
    MutableLiveData<Integer> count = new MutableLiveData<>();
/*
    public void Get_docdetails(Context context){

        doctor_pref = new Doctor_pref(context , "Data");
        Client.getInstance().retrofitApi.Getdoc_details("5ee6b5c5f380e40004ecec22").enqueue(new Callback<Doctor_pojo>() {
            @Override
            public void onResponse(Call<Doctor_pojo> call, Response<Doctor_pojo> response) {

                data.setValue(response.body().getData());

            }

            @Override
            public void onFailure(Call<Doctor_pojo> call, Throwable t) {

            }
        });

    }
*/
    public void putimage(Context context , MultipartBody.Part file){
        doctor_pref = new Doctor_pref(context , "Data");

        Client.getInstance().retrofitApi.putimage(doctor_pref.getData().get_id() , doctor_pref.get_Token() , file).enqueue(new Callback<Upload_response>() {
            @Override
            public void onResponse(Call<Upload_response> call, Response<Upload_response> response) {

                if (response.isSuccessful()){
                    responsee.setValue("تم تغيير الصورة بنجاح");
                    doctor_pref.setProfile(response.body().getData());
                }else {
                    responsee.setValue("حدث خطأ");
                }

            }

            @Override
            public void onFailure(Call<Upload_response> call, Throwable t) {

            }
        });
    }

    public void Get_Reviews(Context context){
        doctor_pref = new Doctor_pref(context , "Data");
        Client.getInstance().retrofitApi.getreviews(doctor_pref.getData().get_id()).enqueue(new Callback<Review_pojo>() {
            @Override
            public void onResponse(Call<Review_pojo> call, Response<Review_pojo> response) {

                review.setValue(response.body().getData());
                count.setValue(response.body().getCount());

            }

            @Override
            public void onFailure(Call<Review_pojo> call, Throwable t) {

            }
        });

    }
}
