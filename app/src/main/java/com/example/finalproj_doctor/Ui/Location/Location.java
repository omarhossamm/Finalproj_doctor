package com.example.finalproj_doctor.Ui.Location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproj_doctor.Model.Sign_up;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Doctor_profile.Doctor_profile;
import com.example.finalproj_doctor.Ui.Home_page.Home_page;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Location extends AppCompatActivity implements OnMapReadyCallback {

    String name , email , password , phone , birthday;
    String careers;
    ArrayList<String> careerss = new ArrayList<>();
    EditText country , state , city , street , block;
    Button registration , select_location;
    Location_Viewmodel location_viewmodel;
    GoogleMap map;
    List<Address> addresses;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        location_viewmodel = new Location_Viewmodel();
        location_viewmodel = ViewModelProviders.of(this).get(Location_Viewmodel.class);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        supportMapFragment.getMapAsync(this);

        Intent intent=getIntent();
        name =intent.getStringExtra("name");
        email =intent.getStringExtra("email");
        password =intent.getStringExtra("password");
        phone =intent.getStringExtra("phone");
        birthday =intent.getStringExtra("birthday");
        careers =intent.getStringExtra("careers");
        careerss.add(careers);

        country = findViewById(R.id.country);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        street = findViewById(R.id.street);
        block = findViewById(R.id.block);

        select_location = findViewById(R.id.select_location);
        registration = findViewById(R.id.registration);

        location_viewmodel.getResponse().observe(Location.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(Location.this , s , Toast.LENGTH_LONG).show();
                if (s.equals("تم التسجيل بنجاح")){
                    registration.setEnabled(true);
                    registration.setBackgroundResource(R.drawable.radius_button);
                    startActivity(new Intent(Location.this , Home_page.class));
                    finish();
                }else {
                    registration.setEnabled(true);
                    registration.setBackgroundResource(R.drawable.radius_button);
                    Toast.makeText(getApplicationContext() , "Failed" , Toast.LENGTH_LONG).show();
                }
            }
        });














    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.0444, 31.2357) , 10f));
        final MarkerOptions markerOptions = new MarkerOptions();

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {



                markerOptions.position(latLng);
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , 17f));
                map.addMarker(markerOptions);
                map.clear();
                map.addMarker(markerOptions);


                select_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Geocoder geocoder;

                        Locale lHebrew = new Locale("ar");
                        geocoder = new Geocoder(Location.this, lHebrew);


                        try {

                            addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


//                        String address = addresses.get(0).getAddressLine(0);
                        String cityy = addresses.get(0).getLocality();
                        final String statee = addresses.get(0).getAdminArea();
                        final String countryy = addresses.get(0).getCountryName();
                        String postalCodee = addresses.get(0).getPostalCode();
                        final String knownNamee = addresses.get(0).getFeatureName();
                        String x = addresses.get(0).getSubLocality();
                        String y = addresses.get(0).getAdminArea();
                        String cityy2 = addresses.get(0).getSubAdminArea();

                        country.setText(countryy);
                        state.setText(statee);
                        if (city.getText().toString().length() == 0)
                        {
                            city.setText(cityy2);
                        }
                        else {
                            city.setText(cityy);
                        }
                        street.setText(knownNamee);
                        block.setText("");

                        //Toast.makeText(Location.this , "برجاء التاكد من صحة العنوان" , Toast.LENGTH_LONG).show();




                        registration.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (country.length() == 0 || state.length() == 0 || city.length() == 0
                                        || street.length() == 0 || block.length() == 0) {
                                    Toast.makeText(Location.this, "Please enter full location", Toast.LENGTH_LONG).show();
                                } else {
                                    registration.setEnabled(false);
                                    registration.setBackgroundResource(R.drawable.loading_radius);
                                    ArrayList<Double> list = new ArrayList<>();
                                    list.add(latLng.longitude);
                                    list.add(latLng.latitude);

                                    com.example.finalproj_doctor.Model.Location location =
                                            new com.example.finalproj_doctor.Model.Location(list, statee,
                                                    city.getText().toString()
                                                    , street.getText().toString(), block.getText().toString(), "Point");


                                    Sign_up sign_up = new Sign_up(name, password, phone, email
                                            , countryy + " , " + statee + " , " + city.getText().toString() + " , " +
                                            street.getText().toString() + " , " + block.getText().toString(), location, careerss, "Hello for every one");

                                    location_viewmodel.signup(context = Location.this , sign_up);

                                }
                            }
                        });






                    }
                });


            }
        });



    }


}
