package com.example.finalproj_doctor.Ui.My_Appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproj_doctor.Adapter.Doctorappointments_Adapter;
import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.example.finalproj_doctor.R;

import java.util.List;

public class My_Appointment extends AppCompatActivity {

    Myappointment_Viewmodel myappointment_viewmodel;
    Doctorappointments_Adapter doctorappointments_adapter;
    RecyclerView recyclerView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__appointment);

        myappointment_viewmodel = new  Myappointment_Viewmodel();
        doctorappointments_adapter = new Doctorappointments_Adapter();
        recyclerView = findViewById(R.id.recycler);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
recyclerView.setLayoutManager(layoutManager);

        myappointment_viewmodel.Get_response().observe(My_Appointment.this, new Observer<List<Schedule>>() {
                    @Override
                    public void onChanged(List<Schedule> schedules) {
                        doctorappointments_adapter.setList(schedules);
                    }
                });

        myappointment_viewmodel.Get_appointments(context = My_Appointment.this);
        recyclerView.setAdapter(doctorappointments_adapter);

      }
}
