package com.example.traveldreamsapp.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("v1/login/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
