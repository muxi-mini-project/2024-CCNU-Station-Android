package com.example.ccnu_station.Reuse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CCNU_ViewModel<DataType> extends ViewModel {
    private MutableLiveData<DataType> data = new MutableLiveData<>();
    public LiveData<DataType> getData() {
        return data;
    }
    public void updateData(DataType newData) {
        data.setValue(newData);
    }
}
