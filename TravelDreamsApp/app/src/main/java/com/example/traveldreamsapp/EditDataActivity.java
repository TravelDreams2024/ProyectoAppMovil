package com.example.traveldreamsapp;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditDataActivity extends AppCompatActivity {

    private EditText etName, etSurname, etEmail, etPhone;
    private Button btnSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);

        // Referencias a los EditText
        etName = findViewById(R.id.et_name);
        etSurname = findViewById(R.id.et_surname);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);

        // Obtener datos desde la actividad de perfil
        Intent intent = getIntent();
        etName.setText(intent.getStringExtra("name"));
        etSurname.setText(intent.getStringExtra("surname"));
        etEmail.setText(intent.getStringExtra("email"));
        etPhone.setText(intent.getStringExtra("phone"));

        // Botón para guardar cambios
        btnSaveData = findViewById(R.id.btn_save_data);
        btnSaveData.setOnClickListener(v -> {
            // Crear un intent para devolver los datos modificados
            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", etName.getText().toString());
            resultIntent.putExtra("surname", etSurname.getText().toString());
            resultIntent.putExtra("email", etEmail.getText().toString());
            resultIntent.putExtra("phone", etPhone.getText().toString());

            // Establecer el resultado y finalizar la actividad
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
