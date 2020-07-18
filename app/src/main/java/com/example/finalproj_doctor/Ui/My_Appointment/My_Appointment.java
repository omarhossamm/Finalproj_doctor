package com.example.finalproj_doctor.Ui.My_Appointment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproj_doctor.Adapter.Doctorappointments_Adapter;
import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Home_page.Home_page;

import java.util.List;

public class My_Appointment extends Fragment {

    Myappointment_Viewmodel myappointment_viewmodel;
    Doctorappointments_Adapter doctorappointments_adapter;
    RecyclerView recyclerView;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = LayoutInflater.from(getContext()).inflate(R.layout.activity_my__appointment, null);

        myappointment_viewmodel = new  Myappointment_Viewmodel();
        doctorappointments_adapter = new Doctorappointments_Adapter();
        recyclerView = layout.findViewById(R.id.recycler);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                myappointment_viewmodel.Get_response().observe(My_Appointment.this, new Observer<List<Schedule>>() {
                    @Override
                    public void onChanged(List<Schedule> schedules) {
                        doctorappointments_adapter.setList(schedules);
                    }
                });

                myappointment_viewmodel.Get_appointments(context = getContext());
                recyclerView.setAdapter(doctorappointments_adapter);

            }
        }, 1000);







        return layout;


    }
}