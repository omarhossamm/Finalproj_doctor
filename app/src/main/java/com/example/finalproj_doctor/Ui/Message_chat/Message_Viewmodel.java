package com.example.finalproj_doctor.Ui.Message_chat;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Message;

import java.util.List;

public class Message_Viewmodel extends ViewModel {

    Message_Repository message_repository = new Message_Repository();

    public void Get_Conversation(Context context){
        message_repository.Get_conversation(context);
    }

    MutableLiveData<List<Message>> get_response(){
        return message_repository.responsee;
    }

}
