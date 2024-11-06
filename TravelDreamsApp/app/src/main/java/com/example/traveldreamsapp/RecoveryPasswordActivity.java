package com.example.traveldreamsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RecoveryPasswordActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_password);

        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(RecoveryPasswordActivity.this, "Por favor, ingrese su correo electrónico", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificar si el email es válido
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    // Mostrar un AlertDialog indicando que un administrador se contactará
                    new AlertDialog.Builder(RecoveryPasswordActivity.this)
                            .setTitle("Recuperación de contraseña")
                            .setMessage("Un administrador se contactará a la brevedad para recuperar su contraseña")
                            .setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss())
                            .show();
                } else {
                    Toast.makeText(RecoveryPasswordActivity.this, "Por favor, ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
