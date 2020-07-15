package com.example.finalproj_doctor.Ui.Qr_Scan;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Pojo.Scanqr_Pojo;

public class Qrscan_Viewmodel extends ViewModel {
    Qrscan_Repository qrscan_repository = new Qrscan_Repository();

    public void scanqr(Context context , String appointmentid){
        qrscan_repository.Scanqr(context , appointmentid);
    }

    public MutableLiveData<Scanqr_Pojo> getresponse(){
        return qrscan_repository.responsee;
    }
}
