package com.example.finalproj_doctor.Ui.Personal_information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalproj_doctor.Model.Update_doc;
import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.example.finalproj_doctor.R;

public class Personal_information extends AppCompatActivity {

    EditText name , email , phone , description , address;
    Button ch_pass , logout , change_inf;

    Doctor_pref doctor_pref;
    Context context;

    Personal_Viewmodel personal_viewmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        personal_viewmodel = new Personal_Viewmodel();
        personal_viewmodel = ViewModelProviders.of(Personal_information.this).get(Personal_Viewmodel.class);
        doctor_pref = new Doctor_pref(context = Personal_information.this , "Data");

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        description = findViewById(R.id.description);
        address = findViewById(R.id.address);


        ch_pass = findViewById(R.id.change_password);
        logout = findViewById(R.id.logout);
        change_inf = findViewById(R.id.change_information);

        name.setText(doctor_pref.getData().getName());
        email.setText(doctor_pref.getData().getEmail());
        phone.setText(doctor_pref.getData().getPhone());
        description.setText(doctor_pref.getData().getDescription());
        address.setText(doctor_pref.getData().getAddress());

        personal_viewmodel.getsuc().observe(Personal_information.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(Personal_information.this , s , Toast.LENGTH_LONG).show();
            }
        });


        change_inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update_doc update_doc = new Update_doc(name.getText().toString() , email.getText().toString() ,
                        phone.getText().toString() , description.getText().toString() , address.getText().toString());
                personal_viewmodel.update_doc(context = Personal_information.this , update_doc);

           personal_viewmodel.update_doc(context = Personal_information.this , update_doc);


            }
        });


    }
}