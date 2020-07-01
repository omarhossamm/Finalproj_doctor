package com.example.finalproj_doctor.Ui.Doctor_profile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Doctor;
import com.example.finalproj_doctor.Model.Review;

import java.util.List;

import okhttp3.MultipartBody;

public class Doctorprofile_Viewmodel extends ViewModel {

    Doctorprofile_Repository doctorprofile_repository = new Doctorprofile_Repository();
/*
    public void Get_docdetails(Context context){
        doctorprofile_repository.Get_docdetails(context);
    }
*/
    public void photo(Context context , MultipartBody.Part file){
        doctorprofile_repository.putimage(context , file);
    }

    public void getreviews(Context context){
        doctorprofile_repository.Get_Reviews(context);
    }

    public MutableLiveData<List<Review>> review_response(){
        return doctorprofile_repository.review;
    }

    public MutableLiveData<Integer> getcount(){
        return doctorprofile_repository.count;
    }

    public MutableLiveData<Doctor> getdata(){
        return doctorprofile_repository.data;
    }

    public MutableLiveData<String> getresponse(){return doctorprofile_repository.responsee;}

}
