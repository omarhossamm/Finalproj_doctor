package com.example.finalproj_doctor.Model.Pojo;


import com.example.finalproj_doctor.Model.Chat;
import com.example.finalproj_doctor.Model.RoomInfo;

import java.util.ArrayList;
import java.util.List;

public class Chat_pojo {

    private boolean success;
    private int count;
    private List<Chat> conversation;



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Chat> getConversation() {
        return conversation;
    }

    public void setConversation(List<Chat> conversation) {
        this.conversation = conversation;
    }
}
