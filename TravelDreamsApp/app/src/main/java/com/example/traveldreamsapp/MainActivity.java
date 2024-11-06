package com.example.traveldreamsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.traveldreamsapp.network.ApiService;
import com.example.traveldreamsapp.network.LoginRequest;
import com.example.traveldreamsapp.network.LoginResponse;

public class MainActivity extends AppCompatActivity {

    private Button btnExplorar;
    private ApiService apiService;  // Instancia del ApiService

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dtapp.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        btnExplorar = findViewById(R.id.btnExplorar);

        // Cuando se presiona el botón, se realiza el login y luego se navega
        btnExplorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Realiza el login antes de navegar
                LoginRequest loginRequest = new LoginRequest("usuario", "contraseña");
                login(loginRequest);  // Llamada al método login

                // Navegar a la siguiente actividad (como ya lo tenías en tu código)
                Intent intent = new Intent(MainActivity.this, NavigatorDrawer.class);
                startActivity(intent);
            }
        });
    }

    // Método para realizar el login
    private void login(LoginRequest loginRequest) {
        Call<LoginResponse> call = apiService.login(loginRequest);  // Llamada a la API para login
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    // Aquí puedes almacenar el token o hacer cualquier otra acción
                    Log.d("Login", "Access Token: " + loginResponse.getAccessToken());

                } else {
                    Log.e("Error", "Error en el login: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Error", "Fallo la conexión: " + t.getMessage());
            }
        });
    }
}