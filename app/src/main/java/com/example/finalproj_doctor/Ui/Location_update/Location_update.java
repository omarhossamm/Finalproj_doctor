package com.example.finalproj_doctor.Ui.Location_update;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.example.finalproj_doctor.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Location_update extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    Doctor_pref doctor_pref;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_update);

        doctor_pref = new Doctor_pref(context = Location_update.this , "Data");

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        MarkerOptions markerOptions = new MarkerOptions();
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(doctor_pref.getData().getLocation().getCoordinates().get(1) ,
                doctor_pref.getData().getLocation().getCoordinates().get(0)) , 18f));

        markerOptions.position(new LatLng(doctor_pref.getData().getLocation().getCoordinates().get(1) ,
                doctor_pref.getData().getLocation().getCoordinates().get(0)));

        map.addMarker(markerOptions);

    }
}