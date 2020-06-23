package com.example.finalproj_doctor.Network;

import com.example.finalproj_doctor.Model.Login;
import com.example.finalproj_doctor.Model.Pojo.Doctor_pojo;
import com.example.finalproj_doctor.Model.Response_signup;
import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Model.Sign_up;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitApi {

    @POST("api/v1/doctors/auth/register")
    Call<Response_signup> sign_up(@Body Sign_up sign_up_doctor);

    @GET("api/v1/doctors/auth/me")
    Call<Doctor_pojo> signin_token(@Header("Authorization:Bearer") String Token);

    @POST("api/v1/doctors/schedules")
    Call<Schedule> Post_Schedule(@Header("Authorization:Bearer") String Token , @Body Schedule schedule);

    @POST("api/v1/doctors/auth/login")
    Call<Response_signup> Login(@Body Login login);


}
