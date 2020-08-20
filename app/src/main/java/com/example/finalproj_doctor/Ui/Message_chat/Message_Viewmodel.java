package com.example.finalproj_doctor.Ui.Message_chat;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Message;
import com.example.finalproj_doctor.Model.Post_msg;

import java.util.List;

public class Message_Viewmodel extends ViewModel {

    Message_Repository message_repository = new Message_Repository();

    public void Get_Conversation(Context context , String room_id){
        message_repository.Get_conversation(context , room_id);
    }

    public void Post_msg(Context context , String room_id , Post_msg msg){
        message_repository.Post_msg(context , room_id , msg);
    }

    MutableLiveData<String> get_msgresp(){
        return message_repository.resp_post;
    }
    MutableLiveData<List<Message>> get_response(){
        return message_repository.responsee;
    }

}
