package com.example.finalproj_doctor.Ui.Review_doc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.finalproj_doctor.Adapter.Review_Adapter;
import com.example.finalproj_doctor.Model.Review;
import com.example.finalproj_doctor.Model.UsersAppointment_model;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Users_appointment.Users_appointment;
import com.example.finalproj_doctor.Ui.Users_appointment.Usersappointment_Viewmodel;

import java.util.List;

public class Review_doc extends AppCompatActivity {

    RecyclerView recyclerView;
    Review_Adapter adapter;
    Review_Viewmodel review_viewmodel;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_doc);

        adapter = new Review_Adapter();
        review_viewmodel = new Review_Viewmodel();

        recyclerView = findViewById(R.id.recycler);


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        review_viewmodel = ViewModelProviders.of(Review_doc.this).get(Review_Viewmodel.class);
        review_viewmodel.getResponse().observe(Review_doc.this, new Observer<List<Review>>() {
                    @Override
                    public void onChanged(List<Review> reviews) {

                        adapter.setList(reviews);

                    }
                });


        review_viewmodel.Getreview(context = Review_doc.this);

        recyclerView.setAdapter(adapter);



    }
}