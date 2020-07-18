package com.example.finalproj_doctor.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproj_doctor.Model.Pojo.Schedule_response;
import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Appointment_edit.Myappointment_edit;
import com.example.finalproj_doctor.Ui.Doctor_profile.Doctor_profile;
import com.example.finalproj_doctor.Ui.Schedule_update.Sceduleupdate_Viewmodel;
import com.example.finalproj_doctor.Ui.Schedule_update.Schedule_update;
import com.example.finalproj_doctor.Ui.Schedule_update.Scheduleupdate_Repository;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctorappointments_edit_Adapter extends RecyclerView.Adapter<Doctorappointments_edit_Adapter.holderr> {

    private List<Schedule> arrayList = new ArrayList<>();

    @NonNull
    @Override
    public holderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.myappointment_item, null);
        holderr viewHolder = new holderr(layout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull holderr holder, final int position) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
        final SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm aa");


        final Calendar startat = Calendar.getInstance();
        Calendar endat = Calendar.getInstance();
        startat.setTimeInMillis(arrayList.get(position).getStartedAt());
        endat.setTimeInMillis(arrayList.get(position).getEndedAt());
        final String start = formatter.format(startat.getTime());
        String end = formatter1.format(endat.getTime());


        holder.total_number.setText("All booked" + " : " + arrayList.get(position).getTotalNumber());
        holder.current_number.setText("Current number" + " : " + arrayList.get(position).getCurrentNumber());
        holder.date.setText(arrayList.get(position).getDay() + " , " + start);
        holder.time.setText( formatter1.format(startat.getTime()) + " , " + end);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                AlertDialog alertDialog;
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setTitle("Day : " + arrayList.get(position).getDay() + " , " + start + "\n" + "Start at : " + formatter1.format(startat.getTime()))
                        .setMessage("if you want delete this schedule click Delete button" + "\n" +
                                "if you want to edit this schedule click Edit Button")
                        .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Gson gson = new Gson();
                                String schedule = gson.toJson(arrayList.get(position));

                                Intent intent = new Intent(v.getContext() , Schedule_update.class);
                                intent.putExtra("schedule" , schedule);
                                v.getContext().startActivity(intent);
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Scheduleupdate_Repository scheduleupdate_repository = new Scheduleupdate_Repository();
                                scheduleupdate_repository.Delete_Schedule(arrayList.get(position).get_id().toString() , v.getContext());
                                ((Activity) v.getContext()).finish();
                                v.getContext().startActivity(new Intent(v.getContext() , Myappointment_edit.class));
                            }
                        });

                alertDialog = dialog.create();
                alertDialog = dialog.show();
            }
        });

    }

    public static class holderr extends RecyclerView.ViewHolder {
        TextView total_number , current_number , date , time;
        LinearLayout linearLayout;

        public holderr(View itemView) {
            super(itemView);

            total_number = itemView.findViewById(R.id.total_reservision);
            current_number = itemView.findViewById(R.id.current_number);
            date = itemView.findViewById(R.id.date_reservation_item);
            time = itemView.findViewById(R.id.time_reservation_item);
            linearLayout = itemView.findViewById(R.id.linear);
        }
    }

    public void setList(List<Schedule> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
