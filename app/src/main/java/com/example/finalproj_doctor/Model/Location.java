package com.example.finalproj_doctor.Model;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private ArrayList<Double> coordinates;
    private String state , city , street , appartement , type;


    public Location(ArrayList<Double> coordinates, String state, String city, String street, String appartement , String type) {
        this.coordinates = coordinates;
        this.state = state;
        this.city = city;
        this.street = street;
        this.appartement = appartement;
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAppartement() {
        return appartement;
    }

    public void setAppartement(String appartement) {
        this.appartement = appartement;
    }

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }


}
