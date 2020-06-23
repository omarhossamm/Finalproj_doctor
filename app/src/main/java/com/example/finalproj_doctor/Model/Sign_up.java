package com.example.finalproj_doctor.Model;

import java.util.ArrayList;
import java.util.List;

public class Sign_up {

    private String name , password , phone , email , address , description ;
    private Location location;
    private ArrayList<String> speciality;

    public Sign_up(String name, String password, String phone, String email, String address, Location location, ArrayList<String> speciality , String description) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.location = location;
        this.speciality = speciality;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<String> getSpeciality() {
        return speciality;
    }

    public void setSpeciality(ArrayList<String> speciality) {
        this.speciality = speciality;
    }

}
