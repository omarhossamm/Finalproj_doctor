package com.example.finalproj_doctor.Ui.Personal_information;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Update_doc;

public class Personal_Viewmodel extends ViewModel {

    Personal_Repository personal_repository = new Personal_Repository();

    public void update_doc(Context context , Update_doc update_doc){
        personal_repository.Update_doc(context , update_doc);
    }

    public MutableLiveData<String> getsuc(){
        return personal_repository.suc;
    }

}
