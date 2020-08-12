package com.example.finalproj_doctor.Model;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    private Object _id , messageId , chatRoomId;
    private String message , photo;
    private Doctor postedByUser;
    private List<User> roomInfo;

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public Object getMessageId() {
        return messageId;
    }

    public void setMessageId(Object messageId) {
        this.messageId = messageId;
    }

    public Object getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Object chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Doctor getPostedByUser() {
        return postedByUser;
    }

    public void setPostedByUser(Doctor postedByUser) {
        this.postedByUser = postedByUser;
    }

    public List<User> getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(List<User> roomInfo) {
        this.roomInfo = roomInfo;
    }
}
