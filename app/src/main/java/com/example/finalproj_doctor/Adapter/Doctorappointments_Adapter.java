package com.example.finalproj_doctor.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Doctorappointments_Adapter extends RecyclerView.Adapter<Doctorappointments_Adapter.holderr> {

    private List<Schedule> arrayList = new ArrayList<>();


    @NonNull
    @Override
    public holderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.myappointment_item, null);
        holderr viewHolder = new holderr(layout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull holderr holder, int position) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
        SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm aa");


        Calendar startat = Calendar.getInstance();
        Calendar endat = Calendar.getInstance();
        startat.setTimeInMillis(arrayList.get(position).getStartedAt());
        endat.setTimeInMillis(arrayList.get(position).getEndedAt());
        String start = formatter.format(startat.getTime());
        String end = formatter1.format(endat.getTime());


        holder.total_number.setText("عدد الحجوزات" + " : " + arrayList.get(position).getTotalNumber());
        holder.current_number.setText("الرقم الحالي" + " : " + arrayList.get(position).getCurrentNumber());
        holder.date.setText(arrayList.get(position).getDay() + " , " + start);
        holder.time.setText( formatter1.format(startat.getTime()) + " , " + end);

    }

    public static class holderr extends RecyclerView.ViewHolder {
        TextView total_number , current_number , date , time;

        public holderr(View itemView) {
            super(itemView);

            total_number = itemView.findViewById(R.id.total_reservision);
            current_number = itemView.findViewById(R.id.current_number);
            date = itemView.findViewById(R.id.date_reservation_item);
            time = itemView.findViewById(R.id.time_reservation_item);

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
