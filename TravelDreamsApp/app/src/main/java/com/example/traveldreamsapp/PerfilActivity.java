package com.example.traveldreamsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.imageview.ShapeableImageView;
import android.content.pm.PackageManager;


public class PerfilActivity extends AppCompatActivity {

    private TextView tvName, tvSurname, tvEmail, tvPhone;
    private Button btnEditData, buttonLogout;
    private ShapeableImageView profileImage;
    private static final int PICK_IMAGE = 100;

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
        btnEditData.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, EditDataActivity.class);
            intent.putExtra("name", tvName.getText().toString());
            intent.putExtra("surname", tvSurname.getText().toString());
            intent.putExtra("email", tvEmail.getText().toString());
            intent.putExtra("phone", tvPhone.getText().toString());
            startActivityForResult(intent, 1);
        });

        // Referencia al botón de cerrar sesión
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(v -> confirmLogout());

        // Vincular el ImageView del layout
        profileImage = findViewById(R.id.profile_image);

        // Cargar imagen guardada
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String imageUriString = sharedPreferences.getString("profileImageUri", null);
        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            profileImage.setImageURI(imageUri);
        }

        // Configurar el listener para abrir la galería
        profileImage.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(PerfilActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(PerfilActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            profileImage.setImageURI(selectedImage);
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("profileImageUri", selectedImage.toString());
            editor.apply();
        }
    }

    private void confirmLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Cerrar Sesión")
                .setMessage("¿Estás seguro de que deseas cerrar sesión?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> logout())
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    private void logout() {
        // Limpiar las preferencias compartidas
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Eliminar todos los datos
        editor.apply();

        // Redirigir a la pantalla de inicio de sesión
        Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permiso denegado para leer el almacenamiento externo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
