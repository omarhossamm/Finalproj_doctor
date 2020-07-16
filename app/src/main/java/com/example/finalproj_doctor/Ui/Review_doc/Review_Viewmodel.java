package com.example.finalproj_doctor.Ui.Review_doc;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproj_doctor.Model.Review;

import java.util.List;

public class Review_Viewmodel extends ViewModel {

    Review_Repository review_repository = new Review_Repository();

    public void Getreview(Context context){
        review_repository.Get_Reviews(context);
    }

    public MutableLiveData<List<Review>> getResponse(){
        return review_repository.mutableLiveData;
    }
}
