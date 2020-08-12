package com.example.finalproj_doctor.Model;

import java.util.List;

public class User {

    private Object _id;
    private String address , role , method , phone;
    private User_local local;

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User_local getLocal() {
        return local;
    }

    public void setLocal(User_local local) {
        this.local = local;
    }
}
