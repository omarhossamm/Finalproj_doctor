package com.example.finalproj_doctor.Ui.Appointment_edit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.finalproj_doctor.Adapter.Doctorappointments_Adapter;
import com.example.finalproj_doctor.Adapter.Doctorappointments_edit_Adapter;
import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.R;

import java.util.List;

public class Myappointment_edit extends AppCompatActivity {

    Appointment_Viewmodel myappointment_viewmodel;
    Doctorappointments_edit_Adapter doctorappointments_adapter;
    RecyclerView recyclerView;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myappointment_edit);
        myappointment_viewmodel = new  Appointment_Viewmodel();
        doctorappointments_adapter = new Doctorappointments_edit_Adapter();
        recyclerView = findViewById(R.id.recycler);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        myappointment_viewmodel.Get_response().observe(Myappointment_edit.this, new Observer<List<Schedule>>() {
            @Override
            public void onChanged(List<Schedule> schedules) {
                doctorappointments_adapter.setList(schedules);
            }
        });

        myappointment_viewmodel.Get_appointments(context = Myappointment_edit.this);
        recyclerView.setAdapter(doctorappointments_adapter);

    }




}