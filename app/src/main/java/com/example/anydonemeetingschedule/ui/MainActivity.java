package com.example.anydonemeetingschedule.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.anydonemeetingschedule.R;
import com.example.anydonemeetingschedule.adapter.TimeSlotAdapter;
import com.example.anydonemeetingschedule.databinding.ActivityMainBinding;
import com.example.anydonemeetingschedule.model.TimeSlot;
import com.example.anydonemeetingschedule.utils.DateUtils;
import com.example.anydonemeetingschedule.viewmodel.TimeSlotViewModel;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding activityMainBinding;
    TimeSlotAdapter timeSlotAdapter;
    TimeSlotViewModel timeSlotViewModel;
    List<TimeSlot> timeSlot = new ArrayList<>();
    public int mHour, mMinute;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        timeSlotViewModel = new ViewModelProvider(this).get(TimeSlotViewModel.class);
        timeSlotViewModel.allTimeSlots();

        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        setRecyclerView();
        setClickListener();

        timeSlotViewModel.getLiveTimeSlots().observe(this, new Observer<List<TimeSlot>>() {
            @Override
            public void onChanged(List<TimeSlot> timeSlots) {
                timeSlotAdapter.updateTimeSlot(timeSlots);
                timeSlot = timeSlots;
            }
        });
    }

    private void setClickListener() {
        activityMainBinding.btnCustomMeetingSchedule.setOnClickListener(this);
        activityMainBinding.btnAddMeeting.setOnClickListener(this);
    }

    public void setRecyclerView() {
        timeSlotAdapter = new TimeSlotAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        linearLayoutManager.setStackFromEnd(true);
        activityMainBinding.rvListOfMeetingTiming.setLayoutManager(linearLayoutManager);

        activityMainBinding.rvListOfMeetingTiming.setAdapter(timeSlotAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCustomMeetingSchedule:
                startTimePicker();
                break;

            case R.id.btnAddMeeting:
                addMeeting();
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void addMeeting() {
        TimeSlot slot = null;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM");
        String date = dateFormat.format(calendar.getTime());

        for (TimeSlot timeData : timeSlot) {
            if (timeData.isStatus()) {
                slot = timeData;
                break;
            }
        }
        if (slot != null){
            activityMainBinding.tvDisplaysDateTime.setText(date+" : "+DateUtils.getReadableTimeFormat(slot.getMeetingStartTime(),slot.getMeetingEndTime()));
        }
    }

    private void startTimePicker() {


        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {

                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                endTimePicker(calendar.getTimeInMillis());
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void endTimePicker(long startTime) {

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        long endTime  = calendar.getTimeInMillis();
                        addToTimeSlotInFragment(startTime,endTime);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void addToTimeSlotInFragment(long startTimeSlotAllocated, long endTimeSlotAllocated) {
        String randomID = UUID.randomUUID().toString();
        for (TimeSlot timeData : timeSlot) {
            timeData.setStatus(false);
        }
        timeSlot.add(new TimeSlot(randomID, startTimeSlotAllocated, endTimeSlotAllocated, true));
        timeSlotAdapter.updateTimeSlot(timeSlot);

    }
}