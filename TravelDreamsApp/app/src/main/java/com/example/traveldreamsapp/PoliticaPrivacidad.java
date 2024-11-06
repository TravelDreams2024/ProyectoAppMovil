package com.example.traveldreamsapp;

import android.content.Intent; // Asegúrate de incluir esto
import android.os.Bundle;
import android.view.View; // Asegúrate de incluir esto también
import android.widget.Button; // Asegúrate de incluir esto
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

public class PoliticaPrivacidad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politica_privacidad);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnVolverRegistro = findViewById(R.id.btnBackReg); // Asegúrate de que el ID sea correcto
        btnVolverRegistro.setText("Volver a Registro");
        btnVolverRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la actividad de registro
                Intent intent = new Intent(PoliticaPrivacidad.this, RegistroActivity.class); // Cambia 'RegistroActivity.class' al nombre de tu actividad de registro
                startActivity(intent);
                finish(); // Finaliza la actividad actual
            }
        });
    }
}
