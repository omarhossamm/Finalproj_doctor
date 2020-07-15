package com.example.finalproj_doctor.Ui.Qr_Scan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Doctor_profile.Doctor_profile;
import com.example.finalproj_doctor.Ui.Scan.Scan;

public class Qr_Scan extends AppCompatActivity {

    Button qrscan_Button;
    public static TextView code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr__scan);

        Permission();
        qrscan_Button = findViewById(R.id.Qrscan_Button);
        code = findViewById(R.id.code);

        qrscan_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Qr_Scan.this , Scan.class));

            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
            else {
                Toast.makeText(getApplicationContext() , "Denied" , Toast.LENGTH_LONG).show();
            }
        }
    }


    public void Permission() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Qr_Scan.this, new String[]{Manifest.permission.CAMERA}
                    , 1);

        } else {


        }

    }


}