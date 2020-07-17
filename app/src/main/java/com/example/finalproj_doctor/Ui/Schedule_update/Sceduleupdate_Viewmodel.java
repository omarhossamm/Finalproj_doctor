package com.example.finalproj_doctor.Ui.Schedule_update;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Model.Schedule_update;

public class Sceduleupdate_Viewmodel extends ViewModel {

    Scheduleupdate_Repository schedule_update = new Scheduleupdate_Repository();

    public void Update_schedule(String schedule_id , Context context , Schedule_update schedule){

        schedule_update.Update_schedule(schedule_id , context , schedule);

    }

    public MutableLiveData<String> getresponse(){
        return schedule_update.respnsee;
    }

}
