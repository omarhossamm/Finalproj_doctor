package com.example.finalproj_doctor.Ui.Message_chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproj_doctor.Adapter.Message_Adapter;
import com.example.finalproj_doctor.Model.Message;
import com.example.finalproj_doctor.Model.Pojo.Message_Pojo;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.example.finalproj_doctor.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message_chat extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText message;
    TextView user_name;
    Doctor_pref doctor_pref;
    Button send_message;
    Message_Viewmodel message_viewmodel;
    Message_Adapter message_adapter;
    Context context;

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://doryapi.herokuapp.com/");

        } catch (URISyntaxException e) {
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_chat);

        doctor_pref = new Doctor_pref(getApplicationContext() , "Data");
        recyclerView = findViewById(R.id.recycler);
        message = findViewById(R.id.message);
        user_name = findViewById(R.id.user_name);
        send_message = findViewById(R.id.send_msg);

        message_adapter = new Message_Adapter(context = Message_chat.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext() , RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);

        message_viewmodel = new Message_Viewmodel();
        message_viewmodel = ViewModelProviders.of(Message_chat.this).get(Message_Viewmodel.class);

        message_viewmodel.get_response().observe(Message_chat.this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                message_adapter.setList(messages);
            }
        });

        message_viewmodel.Get_Conversation(getApplicationContext());
        recyclerView.setAdapter(message_adapter);


        onSocketConnect();
        mSocket.emit("identity", "5f126b2e1f077e0004c0e955");
        mSocket.emit("subscribe", "5f2c3e9066da620004360971");

    }

    public void onSocketConnect(){

        mSocket.on("new message" , onNewMessage);
        mSocket.connect();
    }


    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                   Client.getInstance().retrofitApi.Get_conversation(doctor_pref.get_Token()).enqueue(new Callback<Message_Pojo>() {
                       @Override
                       public void onResponse(Call<Message_Pojo> call, Response<Message_Pojo> response) {
                           message_adapter.setList(response.body().getConversation());
                           recyclerView.setAdapter(message_adapter);
                       }

                       @Override
                       public void onFailure(Call<Message_Pojo> call, Throwable t) {

                       }
                   });
                }
            });
        }
    };

}