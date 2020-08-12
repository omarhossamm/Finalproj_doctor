package com.example.finalproj_doctor.Adapter;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproj_doctor.Model.Chat;
import com.example.finalproj_doctor.Model.Pojo.Chat_pojo;
import com.example.finalproj_doctor.Model.RoomInfo;
import com.example.finalproj_doctor.Model.User;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Qr_Scan.Qr_Scan;
import com.google.firebase.database.Transaction;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class Chatroom_Adapter extends RecyclerView.Adapter<Chatroom_Adapter.holderr> {

    private List<Chat> arrayList = new ArrayList<>();


    @NonNull
    @Override
    public holderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.roomchat_item, null);
        holderr viewHolder = new holderr(layout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final holderr holder, final int position) {

      holder.name.setText(arrayList.get(position).getRoomInfo().get(0).getLocal().getName());
      holder.message.setText(arrayList.get(position).getMessage());



    }

    public static class holderr extends RecyclerView.ViewHolder {
        TextView name , message;

        public holderr(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.message);

        }
    }

    public void setList(List<Chat> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
