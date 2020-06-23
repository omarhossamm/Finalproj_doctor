package com.example.finalproj_doctor.Ui.Login;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Login;

public class Login_Viewmodel extends ViewModel {

    Login_Repository loginRepository = new Login_Repository();

    public void Login(Login login , final Context context){
        loginRepository.Login(login , context);
    }

    public void getloggedin(Context context , String Token){
        loginRepository.get_loggedin(context , Token);
    }

    public MutableLiveData<String> getresponse(){
        return loginRepository.responsee;
    }
    public MutableLiveData<String> getLoggedin()
    {
    return loginRepository.getloggedin;
    }
}
