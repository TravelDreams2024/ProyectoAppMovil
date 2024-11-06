package com.example.traveldreamsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private TextView forgotPassword;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        forgotPassword = findViewById(R.id.forgotPassword); // Asocia el TextView de recuperación de contraseña

        // Configurar el listener para "Olvidé mi contraseña"
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RecoveryPasswordActivity.class);
                startActivity(intent);
            }
        });




        // Redirección al registro al hacer clic en "registrarse"
        TextView registerPrompt = findViewById(R.id.registerPrompt);
        registerPrompt.setOnClickListener(v -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(registerIntent);
        });

        // Configurar Retrofit
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dtapp.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        buttonLogin.setOnClickListener(v -> {
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
                        new Handler().postDelayed(() -> {
                            Intent intent = new Intent(LoginActivity.this, NavigatorDrawer.class);
                            startActivity(intent);
                            finish(); // Cierra la pantalla de login
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
        });
    }


    // Método para alternar la visibilidad de la contraseña
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Ocultar contraseña
            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        } else {
            // Mostrar contraseña
            editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        }
        isPasswordVisible = !isPasswordVisible;
        editTextPassword.setSelection(editTextPassword.length()); // Mantener el cursor al final
    }
}
