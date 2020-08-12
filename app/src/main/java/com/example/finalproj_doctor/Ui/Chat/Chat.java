package com.example.finalproj_doctor.Ui.Chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalproj_doctor.Adapter.Chatroom_Adapter;
import com.example.finalproj_doctor.Adapter.Doctorappointments_Adapter;
import com.example.finalproj_doctor.Model.Room;
import com.example.finalproj_doctor.Model.RoomInfo;
import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.My_Appointment.My_Appointment;
import com.example.finalproj_doctor.Ui.My_Appointment.Myappointment_Viewmodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Chat extends Fragment {
    Chat_Viewmodel chat_viewmodel;
    Chatroom_Adapter chatroom_adapter;
    RecyclerView recyclerView;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = LayoutInflater.from(getContext()).inflate(R.layout.activity_chat, null);

        chat_viewmodel = new Chat_Viewmodel();
        chatroom_adapter = new Chatroom_Adapter();
        recyclerView = layout.findViewById(R.id.recycler);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

            chat_viewmodel.Getresponse().observe(Chat.this, new Observer<List<com.example.finalproj_doctor.Model.Chat>>() {
                        @Override
                        public void onChanged(List<com.example.finalproj_doctor.Model.Chat> chats) {
                       chatroom_adapter.setList(chats);
                        }
                    });
                    chat_viewmodel.get_chat(context = getContext());
                recyclerView.setAdapter(chatroom_adapter);





        return layout;


    }
}