package com.example.finalproj_doctor.Ui.Scan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Qr_Scan.Qr_Scan;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scan extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }

    @Override
    public void handleResult(Result rawResult) {

        Qr_Scan.code.setText("code : " + rawResult.getText());
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