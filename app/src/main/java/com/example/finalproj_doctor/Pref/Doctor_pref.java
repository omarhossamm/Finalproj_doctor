package com.example.finalproj_doctor.Pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.finalproj_doctor.Model.Doctor;
import com.google.gson.Gson;

public class Doctor_pref {

    SharedPreferences preferences;

    public Doctor_pref(Context context , String filename) {
        preferences = context.getSharedPreferences(filename , Context.MODE_PRIVATE);
    }

    public void clear(){
        SharedPreferences.Editor pref = preferences.edit();
        pref.clear();
        pref.apply();
    }

    public void set_Token(String token){
        SharedPreferences.Editor pref = preferences.edit();
        pref.clear();
        pref.putString("token" , token);
        pref.apply();
    }

    public String get_Token(){
        String token = preferences.getString("token" , "");
        return token;
    }

    public void setData(Doctor doctor){

        Gson gson = new Gson();
        String data = gson.toJson(doctor);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("doctor" , data);
        editor.apply();

    }

    public void setProfile(String image){

        SharedPreferences.Editor pref = preferences.edit();
        pref.putString("profile" , image);
        pref.apply();

    }

    public String get_Image(){
        String image = preferences.getString("image" , "");
        return image;
    }

    public Doctor getData(){
        Gson gson = new Gson();
        String data =preferences.getString("doctor" , "");
        Doctor doctor = gson.fromJson(data , Doctor.class);
        return doctor;
}


}
