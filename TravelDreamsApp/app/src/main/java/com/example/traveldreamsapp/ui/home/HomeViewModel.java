package com.example.traveldreamsapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mDestinosText;

    public HomeViewModel() {

        mDestinosText = new MutableLiveData<>();
        mDestinosText.setValue("Nuestros Destinos");
    }

    public LiveData<String> getDestinosText() {
        return mDestinosText;
    }
}