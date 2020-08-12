package com.example.finalproj_doctor.Ui.Chat;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Chat;
import com.example.finalproj_doctor.Model.RoomInfo;

import java.util.List;

public class Chat_Viewmodel extends ViewModel {

    MutableLiveData<List<Chat>> response = new MutableLiveData<>();
    Chat_Repository chat_repository = new Chat_Repository();

    public void get_chat(Context context){

        chat_repository.Get_chatconv(context);

    }

    MutableLiveData<List<Chat>> Getresponse(){
        return chat_repository.responsee;
    }

}
