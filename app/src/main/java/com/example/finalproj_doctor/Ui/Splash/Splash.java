package com.example.finalproj_doctor.Ui.Splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Doctor_profile.Doctor_profile;
import com.example.finalproj_doctor.Ui.Home_page.Home_page;
import com.example.finalproj_doctor.Ui.Login.Login;
import com.example.finalproj_doctor.Ui.Login.Login_Viewmodel;
import com.example.finalproj_doctor.Ui.My_Appointment.My_Appointment;
import com.example.finalproj_doctor.Ui.Put_Appointment.Put_Appointment;

public class Splash extends AppCompatActivity {
    Login_Viewmodel login_viewmodel;
    Context context;
    Doctor_pref doctor_pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        login_viewmodel = new Login_Viewmodel();
        login_viewmodel = ViewModelProviders.of(Splash.this).get(Login_Viewmodel.class);
        doctor_pref = new Doctor_pref(context = Splash.this , "Data");


        login_viewmodel.getLoggedin().observe(Splash.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("true")) {
                    startActivity(new Intent(Splash.this, Home_page.class));
                }else {startActivity(new Intent(Splash.this , Login.class));}
            }
        });


        login_viewmodel.getloggedin(context = Splash.this , doctor_pref.get_Token());

    }
}