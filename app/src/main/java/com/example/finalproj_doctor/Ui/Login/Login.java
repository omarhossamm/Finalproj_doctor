package com.example.finalproj_doctor.Ui.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproj_doctor.Model.Doctor;
import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.My_Appointment.My_Appointment;
import com.example.finalproj_doctor.Ui.Put_Appointment.Put_Appointment;

public class Login extends AppCompatActivity {

    EditText email , password;
    TextView sign_up;
    Button login_confirmation;
    Login_Viewmodel login_viewmodel;
    Context context;
    Doctor_pref doctor_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_viewmodel = new Login_Viewmodel();
        doctor_pref = new Doctor_pref(context = Login.this , "Data");

        email = findViewById(R.id.email_edt_login);
        password = findViewById(R.id.pass_edt_login);
        sign_up = findViewById(R.id.signup_login);
        login_confirmation = findViewById(R.id.Login_button_login);

        login_viewmodel.getloggedin(context = Login.this , doctor_pref.get_Token());
        login_viewmodel.getLoggedin().observe(Login.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("true")) {
                    startActivity(new Intent(Login.this, My_Appointment.class));
            }else {}
            }
        });

        login_viewmodel = ViewModelProviders.of(Login.this).get(Login_Viewmodel.class);
        login_viewmodel.getresponse().observe(Login.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("تم تسجيل الدخول بنجاح")){
                    startActivity(new Intent(Login.this , My_Appointment.class));
                }else Toast.makeText(getApplicationContext() , "برجاء التاكد من صحة البيانات" , Toast.LENGTH_LONG).show();
            }
        });






        login_confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                com.example.finalproj_doctor.Model.Login login =
                        new com.example.finalproj_doctor.Model.Login(email.getText().toString()
                , password.getText().toString());

                if (email.getText().length() == 0 || password.getText().length() == 0){
                    Toast.makeText(Login.this , "برجاء ادخال التفاصيل" , Toast.LENGTH_LONG).show();
                }else {
                    login_viewmodel.Login(login , context = Login.this);
                }

            }
        });

    }
}
