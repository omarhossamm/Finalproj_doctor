package com.example.finalproj_doctor.Ui.Scan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproj_doctor.Model.Pojo.Scanqr_Pojo;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Qr_Scan.Qr_Scan;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scan extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    Scan_Viewmodel scan_viewmodel;
    Context context;
    String appointment_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        scan_viewmodel = new Scan_Viewmodel();
        scan_viewmodel = ViewModelProviders.of(Scan.this).get(Scan_Viewmodel.class);

        Intent intent = getIntent();
        appointment_id = intent.getStringExtra("appointment_id");
    }

    @Override
    public void handleResult(final Result rawResult) {

        if (rawResult.getText().equals(appointment_id)) {

            scan_viewmodel.scanqr(context = Scan.this, appointment_id);
            scan_viewmodel.getresponse().observe(Scan.this, new Observer<Scanqr_Pojo>() {
                @Override
                public void onChanged(Scanqr_Pojo scanqr_pojo) {
                    Qr_Scan.linearLayout.setVisibility(View.VISIBLE);
                    Qr_Scan.name.setText(scanqr_pojo.getAppointment().getPatientName());
                    Qr_Scan.number.setText("" + scanqr_pojo.getAppointment().getPatientNumber());
                    Qr_Scan.code.setText("code : " + rawResult.getText());
                }
            });
        }else {
            Toast.makeText(getApplicationContext() , "Code incorrect" , Toast.LENGTH_LONG).show();
        }
        onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();

        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}