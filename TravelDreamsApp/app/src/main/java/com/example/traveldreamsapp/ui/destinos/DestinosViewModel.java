package com.example.traveldreamsapp.ui.destinos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DestinosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DestinosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is destinos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}