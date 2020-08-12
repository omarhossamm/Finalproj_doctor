package com.example.finalproj_doctor.Model;

public class Message {
    private Object _id , chatRoomId , postedByUser;
    private String message , createdAt , updatedAt;
    private int __v;

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public Object getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Object chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public Object getPostedByUser() {
        return postedByUser;
    }

    public void setPostedByUser(Object postedByUser) {
        this.postedByUser = postedByUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
