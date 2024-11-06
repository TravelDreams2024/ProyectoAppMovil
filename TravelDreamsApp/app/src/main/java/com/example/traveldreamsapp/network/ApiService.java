package com.example.traveldreamsapp.network;
import com.google.gson.annotations.SerializedName;


import com.example.traveldreamsapp.models.Destinos;
import com.example.traveldreamsapp.models.RegisterRequest;
import com.example.traveldreamsapp.models.RegisterResponse;

import java.util.List;
import retrofit2.http.Header;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/v1/destinos/")
    Call<List<Destinos>> getDestinos();

    @GET("api/v1/destinos/{id_destino}")
    Call<Destinos> getDestinoById(@Path("id_destino") int id);

    @POST("api/v1/login/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("api/v1/register/")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @GET("api/v1/usuarios/")
    Call<List<User>> getUsuarios();

    @GET("api/v1/user/profile/")
    Call<User> getUserProfile(@Header("Authorization") String authHeader);
}
