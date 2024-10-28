package com.example.traveldreamsapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveldreamsapp.models.RegisterRequest;
import com.example.traveldreamsapp.models.RegisterResponse;
import com.example.traveldreamsapp.network.ApiClient;
import com.example.traveldreamsapp.network.ApiService;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    // Declaración de campos
    private EditText editTextName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private CheckBox checkBoxPrivacy;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicialización de los campos
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        checkBoxPrivacy = findViewById(R.id.checkBoxPrivacy);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Configuración del botón de registro
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxPrivacy.isChecked()) {
                    registerUser();
                } else {
                    Toast.makeText(RegistroActivity.this, "Debes aceptar la política de privacidad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para registrar el usuario
    private void registerUser() {
        String firstName = editTextName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        // Validaciones
        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isEmailValid(email)) {
            Toast.makeText(this, "Correo no es de un dominio válido", Toast.LENGTH_SHORT).show();
            return;
        }
        String passwordErrors = getPasswordErrors(password);
        if (!passwordErrors.isEmpty()) {
            Toast.makeText(this, passwordErrors, Toast.LENGTH_LONG).show();
            return;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterRequest request = new RegisterRequest(firstName, lastName, email, password, confirmPassword);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<RegisterResponse> call = apiService.registerUser(request);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 400) {
                    Toast.makeText(RegistroActivity.this, "Correo electrónico ya registrado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistroActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegistroActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para validar correo
    private boolean isEmailValid(String email) {
        String[] dominiosValidos = {"@gmail.com", "@yahoo.com", "@hotmail.com"};
        for (String dominio : dominiosValidos) {
            if (email.endsWith(dominio)) return true;
        }
        return false;
    }

    // Método para validar contraseña y devolver los errores como un solo mensaje
    private String getPasswordErrors(String password) {
        StringBuilder errors = new StringBuilder();
        if (password.length() < 8) {
            errors.append("La contraseña debe tener al menos 8 caracteres\n");
        }
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            errors.append("Debe contener al menos una mayúscula\n");
        }
        if (!Pattern.compile("[!@#$%]").matcher(password).find()) {
            errors.append("Debes incluir al menos 1 carácter especial (!, @, #, $, %)\n");
        }
        return errors.toString().trim();  // Retorna los errores con saltos de línea
    }
}

