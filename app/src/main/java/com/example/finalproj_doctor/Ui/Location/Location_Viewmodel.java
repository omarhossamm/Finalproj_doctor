package com.example.finalproj_doctor.Ui.Location;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Sign_up;

public class Location_Viewmodel extends ViewModel {

    Location_Repository location_repository = new Location_Repository();


    public void signup(Context context , Sign_up sign_up){
        location_repository.sign_up(context , sign_up);
    }


    public MutableLiveData<String> getResponse(){
        return location_repository.responsee;
    }


}
