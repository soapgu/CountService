package com.soapgu.countservice;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class MainViewModel extends ObservableViewModel {
    private String message = "Hello";

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    @Bindable
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.notifyPropertyChanged(BR.message);
    }
}
