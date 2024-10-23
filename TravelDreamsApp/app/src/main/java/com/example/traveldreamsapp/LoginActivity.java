package com.example.traveldreamsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveldreamsapp.network.ApiService;
import com.example.traveldreamsapp.network.LoginRequest;
import com.example.traveldreamsapp.network.LoginResponse;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Redirección al registro al hacer clic en "registrarse"
        TextView registerPrompt = findViewById(R.id.registerPrompt);
        registerPrompt.setOnClickListener(v -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(registerIntent);
        });

        // Configurar Retrofit
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dtapp.pythonanywhere.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor, ingrese ambos campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear solicitud de inicio de sesión
                LoginRequest loginRequest = new LoginRequest(email, password);
                Call<LoginResponse> call = apiService.login(loginRequest);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            String token = response.body().getAccessToken();
                            Toast.makeText(LoginActivity.this, "Login Exitoso, bienvenido!", Toast.LENGTH_SHORT).show();

                            // Redirigir después de 1.5 segundos
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(LoginActivity.this, NavigatorDrawer.class);
                                    startActivity(intent);
                                    finish(); // Cierra la pantalla de login
                                }
                            }, 1500);
                        } else {
                            Toast.makeText(LoginActivity.this, "Error en el inicio de sesión, compruebe su usuario y/o contraseña", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
