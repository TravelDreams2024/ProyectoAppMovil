package com.example.traveldreamsapp.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.traveldreamsapp.LoginActivity;
import com.example.traveldreamsapp.RegistroActivity;
import com.example.traveldreamsapp.databinding.FragmentPerfilBinding;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);

        // Aquí configuramos los listeners para los botones
        configureButtonActions();

        // Retornar la vista raíz del binding
        return binding.getRoot();
    }

    // Método para configurar los botones
    private void configureButtonActions() {
        // Configurar onClick para el botón "Iniciar Sesión"
        binding.buttonLogin.setOnClickListener(v -> {
            Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
            startActivity(loginIntent);
        });

        // Configurar onClick para el botón "Registrar"
        binding.buttonRegister.setOnClickListener(v -> {
            Intent registerIntent = new Intent(getActivity(), RegistroActivity.class);
            startActivity(registerIntent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}