package com.example.traveldreamsapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    public static Retrofit getClient (){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://dtapp.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create()) //Gson
                .build();
        return retrofit;
    }
}
