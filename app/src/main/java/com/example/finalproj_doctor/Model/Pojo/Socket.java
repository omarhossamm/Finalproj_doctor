package com.example.finalproj_doctor.Model.Pojo;

import com.example.finalproj_doctor.Model.Message_Socket;

import java.util.List;

public class Socket {

    boolean success;
    public List<Message_Socket> conversation;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Message_Socket> getConversation() {
        return conversation;
    }

    public void setConversation(List<Message_Socket> conversation) {
        this.conversation = conversation;
    }

}
