package com.example.anydonemeetingschedule.viewmodel;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.anydonemeetingschedule.model.TimeSlot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TimeSlotViewModel extends ViewModel {
    MutableLiveData<List<TimeSlot>> mutableLiveData = new MutableLiveData<>();

    public void allTimeSlots(){
        ArrayList<TimeSlot> timeSlots = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        for (int i=0;i<9;i++){
            long startTime  = calendar.getTimeInMillis();
            calendar.add(Calendar.MINUTE,60);
            long endTime  = calendar.getTimeInMillis();
            timeSlots.add(new TimeSlot(String.valueOf(i),startTime,endTime,false));
        }
        mutableLiveData.postValue(timeSlots);
    }

    public LiveData<List<TimeSlot>> getLiveTimeSlots(){
        return mutableLiveData;
    }
}
