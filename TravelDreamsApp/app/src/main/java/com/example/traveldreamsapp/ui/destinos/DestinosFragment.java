package com.example.traveldreamsapp.ui.destinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.traveldreamsapp.adapter.DestinosAdapter;
import com.example.traveldreamsapp.databinding.FragmentDestinosBinding;
import com.example.traveldreamsapp.models.Destinos;
import com.example.traveldreamsapp.network.ApiClient;
import com.example.traveldreamsapp.network.ApiService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinosFragment extends Fragment {

    private List<Destinos> destinos;
    private FragmentDestinosBinding binding;
    private DestinosAdapter destinosAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDestinosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupRecyclerView();
        showDestinos();

        return root;
    }

    private void setupRecyclerView() {
        binding.navDestinos.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }

    public void showDestinos() {
        Call<List<Destinos>> call = ApiClient.getClient().create(ApiService.class).getDestinos();
        call.enqueue(new Callback<List<Destinos>>(){
            @Override
            public void onResponse(Call<List<Destinos>> call, Response<List<Destinos>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    destinos = response.body();
                    destinosAdapter = new DestinosAdapter(destinos, requireContext());
                    binding.navDestinos.setAdapter(destinosAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Destinos>> call, Throwable throwable) {
                Toast.makeText(getContext(), "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}