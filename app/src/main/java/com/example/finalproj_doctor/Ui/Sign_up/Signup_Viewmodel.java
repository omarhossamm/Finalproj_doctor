package com.example.finalproj_doctor.Ui.Sign_up;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class Signup_Viewmodel extends ViewModel {

    Signup_Repository signup_repository = new Signup_Repository();


    public ArrayList<String> Get_items(){
        return signup_repository.Spinner_careers();
    }


}
