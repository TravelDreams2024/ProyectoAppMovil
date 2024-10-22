package com.example.traveldreamsapp.network;

import com.example.traveldreamsapp.Integrante;
import com.example.traveldreamsapp.models.Destinos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/v1/destinos/")
    Call<List<Destinos>> getDestinos();

    @GET("api/v1/destinos/{id_destino}")
    Call<Destinos> getDestinoById(@Path("id_destino") int id);

    @GET("api/v1/nosotros/")
    Call<List<Integrante>> getIntegrantes();
}
