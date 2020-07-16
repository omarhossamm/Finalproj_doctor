package com.example.finalproj_doctor.Ui.Scan;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Pojo.Scanqr_Pojo;
import com.example.finalproj_doctor.Ui.Qr_Scan.Qrscan_Repository;

public class Scan_Viewmodel extends ViewModel {

    Scan_Repository qrscan_repository = new Scan_Repository();

    public void scanqr(Context context , String appointmentid){
        qrscan_repository.Scanqr(context , appointmentid);
    }

    public MutableLiveData<Scanqr_Pojo> getresponse(){
        return qrscan_repository.responsee;
    }

}
