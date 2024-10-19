package com.example.traveldreamsapp.ui.nosotros;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.traveldreamsapp.R;

public class NosotrosFragment extends Fragment {

    private NosotrosViewModel mViewModel;

    public static NosotrosFragment newInstance() {
        return new NosotrosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nosotros, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NosotrosViewModel.class);
        // TODO: Use the ViewModel
    }

}