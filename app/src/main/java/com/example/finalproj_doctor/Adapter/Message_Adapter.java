package com.example.finalproj_doctor.Adapter;

import android.content.Context;
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

import com.example.finalproj_doctor.Model.Message;
import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Message_chat.Message_chat;
import com.example.finalproj_doctor.Ui.Qr_Scan.Qr_Scan;

import java.util.ArrayList;
import java.util.List;

public class Message_Adapter extends RecyclerView.Adapter<Message_Adapter.holderr> {

    private List<Message> arrayList = new ArrayList<>();
    Context context;
    View layout;
    int left = 0 , right = 1;

    public Message_Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public holderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == left) {
            layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, null);
            holderr viewHolder = new holderr(layout);
            return viewHolder;
        }else {
            layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, null);
            holderr viewHolder = new holderr(layout);
            return viewHolder;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull holderr holder, final int position) {



        holder.msg.setText(arrayList.get(position).getMessage());

    }

    public static class holderr extends RecyclerView.ViewHolder {

        TextView msg;

        public holderr(View itemView) {
            super(itemView);

            msg = itemView.findViewById(R.id.msg);

        }
    }

    public void setList(List<Message> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Doctor_pref doctor_pref = new Doctor_pref(context , "Data");
        if (arrayList.get(position).getPostedByUser().toString().equals(doctor_pref.getData().get_id())){
            return left;
        }else {
            return right;
        }
    }
}
