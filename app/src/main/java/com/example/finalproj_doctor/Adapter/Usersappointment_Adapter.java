package com.example.finalproj_doctor.Adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproj_doctor.Model.UsersAppointment_model;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Qr_Scan.Qr_Scan;

import java.util.ArrayList;
import java.util.List;

public class Usersappointment_Adapter extends RecyclerView.Adapter<Usersappointment_Adapter.holderr> {

    private List<UsersAppointment_model> arrayList = new ArrayList<>();


    @NonNull
    @Override
    public holderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.usersappointment_item, null);
        holderr viewHolder = new holderr(layout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull holderr holder, final int position) {

        holder.tracking_number.setText("رقم : " + arrayList.get(position).getPatientNumber());
        holder.age.setText(arrayList.get(position).getAge() + " سنة");
        holder.name.setText(arrayList.get(position).getPatientName());
        holder.phone.setText(arrayList.get(position).getPhone() + "");

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext() , Qr_Scan.class);
                intent.putExtra("appointment_id" , arrayList.get(position).get_id().toString());
                view.getContext().startActivity(intent);
            }
        });

    }

    public static class holderr extends RecyclerView.ViewHolder {

        TextView name , phone , age , tracking_number;
        LinearLayout linearLayout;

        public holderr(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            age = itemView.findViewById(R.id.age);
            tracking_number = itemView.findViewById(R.id.tracking_number);
            linearLayout = itemView.findViewById(R.id.linear);

        }
    }

    public void setList(List<UsersAppointment_model> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
