package com.example.traveldreamsapp;

import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveldreamsapp.adapter.IntegranteAdapter;
import com.example.traveldreamsapp.network.ApiClient;
import com.example.traveldreamsapp.network.ApiService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Nosotros extends AppCompatActivity {
    private RecyclerView recyclerView;
    private IntegranteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nosotros);

        recyclerView = findViewById(R.id.recyclerViewIntegrantes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Llamar al método para obtener los integrantes
        obtenerIntegrantes();
    }

    private void obtenerIntegrantes() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Integrante>> call = apiService.getIntegrantes();

        call.enqueue(new Callback<List<Integrante>>() {
            @Override
            public void onResponse(Call<List<Integrante>> call, Response<List<Integrante>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Integrante> integranteList = response.body();
                    adapter = new IntegranteAdapter(Nosotros.this, integranteList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(Nosotros.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Integrante>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
                Toast.makeText(Nosotros.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

