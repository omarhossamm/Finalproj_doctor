package com.example.finalproj_doctor.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    public static final String Baseurl = "https://sleepy-dusk-06409.herokuapp.com/";
    public RetrofitApi retrofitApi;
    private static Client instance;

    public Client() {


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApi = retrofit.create(RetrofitApi.class);


    }

    public static Client getInstance(){
        if (instance == null){

            instance = new Client();
        }

        return instance;
    }
}
