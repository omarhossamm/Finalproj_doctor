package com.example.finalproj_doctor.Network;

import com.example.finalproj_doctor.Model.Login;
import com.example.finalproj_doctor.Model.Pojo.Chat_pojo;
import com.example.finalproj_doctor.Model.Pojo.Doctor_pojo;
import com.example.finalproj_doctor.Model.Pojo.Message_Pojo;
import com.example.finalproj_doctor.Model.Pojo.Postmsg_Response;
import com.example.finalproj_doctor.Model.Pojo.Review_pojo;
import com.example.finalproj_doctor.Model.Pojo.Scanqr_Pojo;
import com.example.finalproj_doctor.Model.Pojo.Schedule_response;
import com.example.finalproj_doctor.Model.Pojo.UserAppointment_pojo;
import com.example.finalproj_doctor.Model.Post_msg;
import com.example.finalproj_doctor.Model.Response_signup;
import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Model.Schedule_update;
import com.example.finalproj_doctor.Model.Sign_up;
import com.example.finalproj_doctor.Model.Update_doc;
import com.example.finalproj_doctor.Model.Upload_response;

import java.util.Calendar;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApi {

    @POST("api/v1/doctors/auth/register")
    Call<Response_signup> sign_up(@Body Sign_up sign_up_doctor);

    @GET("api/v1/doctors/auth/me")
    Call<Doctor_pojo> signin_token(@Header("Authorization:Bearer") String Token);

    @POST("api/v1/doctors/schedules")
    Call<Schedule> Post_Schedule(@Header("Authorization:Bearer") String Token , @Body Schedule schedule);

    @POST("api/v1/doctors/auth/login")
    Call<Response_signup> Login(@Body Login login);

    @GET("api/v1/doctors/{id}/schedules")
    Call<Schedule_response> Getdoctor_Schedule(@Path("id") String doctor_id);

    @GET("api/v1/appointments")
    Call<UserAppointment_pojo> Getusers_appointment(@Query("schedule") String schedule_id);

    @GET("api/v1/doctors/{id}")
    Call<Doctor_pojo> Getdoc_details(@Path("id") String id);

    @Multipart
    @PUT("api/v1/doctors/{id}/photo")
    Call<Upload_response> putimage(@Path("id") String id , @Header("Authorization:Bearer") String token , @Part MultipartBody.Part file);

    @GET("api/v1/doctors/{id}/reviews")
    Call<Review_pojo> getreviews(@Path("id") String id);

    @PUT("api/v1/doctors/appointments/{appointment_id}/qr")
    Call<Scanqr_Pojo> Scan_qr(@Path("appointment_id") String appointment_id ,@Header("Authorization:Bearer") String token);

    @PUT("api/v1/doctors/{id}")
    Call<Doctor_pojo> Update_dr(@Path("id") String id , @Header("Authorization:Bearer") String token , @Body Update_doc update_doc);

    @DELETE("api/v1/schedules/{schedule_id}")
    Call<Schedule_response> Delete_schedule(@Path("schedule_id") String schedule_id , @Header("Authorization:Bearer") String token);

    @PUT("api/v1/schedules/{schedule_id}")
    Call<Schedule_update> Update_schedule(@Path("schedule_id") String schedule_id , @Header("Authorization:Bearer") String token , @Body Schedule_update schedule);

    @GET("api/v1/doctors/auth/logout")
    Call<Doctor_pojo> Logout(@Header("Authorization:Bearer") String token);


    @GET("api/v1/chats")
    Call<Chat_pojo> Get_chats(@Header("Authorization:Bearer") String token);

    @GET("api/v1/chats/5f2c3e9066da620004360971")
    Call<Message_Pojo> Get_conversation(@Header("Authorization:Bearer") String token);

    @POST("api/v1/chats/5f2c3e9066da620004360971/message")
    Call<Postmsg_Response> Post_msg(@Header("Authorization:Bearer") String token , @Body Post_msg post_msg);

}
