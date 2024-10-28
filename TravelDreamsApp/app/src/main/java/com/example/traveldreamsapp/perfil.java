package com.example.traveldreamsapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.imageview.ShapeableImageView;

public class perfil extends AppCompatActivity {

///////////////////////////////////////////////////////////////////////////////////////////
public class PerfilActivity extends AppCompatActivity {

    private TextView tvName, tvSurname, tvEmail, tvPhone;
    private Button btnEditData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Referencias a los TextView
        tvName = findViewById(R.id.tv_name);
        tvSurname = findViewById(R.id.tv_full_name);
        tvEmail = findViewById(R.id.tv_email);
        tvPhone = findViewById(R.id.tv_phone);

        // Referencia al botón "Modificar Datos"
        btnEditData = findViewById(R.id.btn_edit_data);

        // Listener para abrir la actividad de edición
        btnEditData.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, EditDataActivity.class);

            // Pasar datos actuales a la actividad de edición
            intent.putExtra("name", tvName.getText().toString());
            intent.putExtra("surname", tvSurname.getText().toString());
            intent.putExtra("email", tvEmail.getText().toString());
            intent.putExtra("phone", tvPhone.getText().toString());

            // Iniciar la actividad esperando un resultado (los datos modificados)
            startActivityForResult(intent, 1);
        });
    }

    // Manejar el resultado de la actividad de edición
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Actualizar los TextView con los nuevos datos
            tvName.setText(data.getStringExtra("name"));
            tvSurname.setText(data.getStringExtra("surname"));
            tvEmail.setText(data.getStringExtra("email"));
            tvPhone.setText(data.getStringExtra("phone"));
        }
    }
}



////////////////// Cambiar imagen de perfil //////////////////////////////////////////////

    // Definimos la imagen de perfil
    private ShapeableImageView profileImage;
    // Código de solicitud para seleccionar imagen
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Vincular el ImageView del layout
        profileImage = findViewById(R.id.profile_image);

        // Cargamos la imagen, siempre que exista una
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String imageUriString = sharedPreferences.getString("profileImageUri", null);
        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            // Cargar la imagen previamente guardada
            profileImage.setImageURI(imageUri);
        }

        // Configurar el listeiner para abrir la galería cuando se haga clic en la imagen de perfil
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Verificamos si el permiso para leer el almacenamiento externo está otorgado
                if (ContextCompat.checkSelfPermission(perfil.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Si no está otorgado, solicita el permiso
                    ActivityCompat.requestPermissions(perfil.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    // Si ya está otorgado, abre la galería para seleccionar una imagen
                    openGallery();
                }
            }
        });
    }

    // Método para abrir la galería
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    // Manejar el resultado de la selección de imagen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            // Actualizar el ImageView con la imagen seleccionada
            profileImage.setImageURI(selectedImage);

            // Guardar la URI de la imagen seleccionada en SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("profileImageUri", selectedImage.toString());
            editor.apply();
        }
    }

    // Método para manejar la respuesta del usuario a la solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso otorgado, abrir la galería
                openGallery();
            } else {
                // Permiso denegado, mostrar un mensaje
                Toast.makeText(this, "Permiso denegado para leer el almacenamiento externo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
