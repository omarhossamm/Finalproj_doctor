package com.example.finalproj_doctor.Ui.Users_appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.finalproj_doctor.Adapter.Usersappointment_Adapter;
import com.example.finalproj_doctor.Model.UsersAppointment_model;
import com.example.finalproj_doctor.R;

import java.util.List;

public class Users_appointment extends AppCompatActivity {

    Usersappointment_Viewmodel usersappointment_viewmodel;
    RecyclerView recyclerView;
    Usersappointment_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_appointment);

        usersappointment_viewmodel = new Usersappointment_Viewmodel();
        Intent intent = getIntent();
        String Schedule_id = intent.getStringExtra("Schedule_id");

        recyclerView = findViewById(R.id.recycler);
        adapter = new Usersappointment_Adapter();
        usersappointment_viewmodel = new Usersappointment_Viewmodel();

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        usersappointment_viewmodel = ViewModelProviders.of(Users_appointment.this).get(Usersappointment_Viewmodel.class);
        usersappointment_viewmodel.getresponse().observe(Users_appointment.this, new Observer<List<UsersAppointment_model>>() {
            @Override
            public void onChanged(List<UsersAppointment_model> usersAppointment_models) {
                adapter.setList(usersAppointment_models);
            }
        });


        usersappointment_viewmodel.Getschedule_usere(Schedule_id);

        recyclerView.setAdapter(adapter);


    }
}
