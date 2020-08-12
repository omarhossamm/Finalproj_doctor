package com.example.finalproj_doctor.Model.Pojo;

import com.example.finalproj_doctor.Model.Chat;
import com.example.finalproj_doctor.Model.Message;

import java.util.List;

public class Message_Pojo {

    private boolean success;
    private int count;
    private List<Message> conversation;

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

    public List<Message> getConversation() {
        return conversation;
    }

    public void setConversation(List<Message> conversation) {
        this.conversation = conversation;
    }
}
