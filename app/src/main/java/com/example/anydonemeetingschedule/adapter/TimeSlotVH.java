package com.example.anydonemeetingschedule.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anydonemeetingschedule.R;
import com.example.anydonemeetingschedule.model.TimeSlot;
import com.example.anydonemeetingschedule.utils.DateUtils;

public class TimeSlotVH extends RecyclerView.ViewHolder {
    TextView meetingTime;
    CardView cvTimeSlot;

    private OnItemClick onItemClick;

    public TimeSlotVH(@NonNull View itemView, OnItemClick onItemClickAction) {
        super(itemView);
        meetingTime = itemView.findViewById(R.id.tvTime);
        cvTimeSlot = itemView.findViewById(R.id.cvTimeSlot);
        this.onItemClick = onItemClickAction;
    }


    @SuppressLint("SetTextI18n")
    public void onBind(TimeSlot timeSlot){
        meetingTime.setText(DateUtils.getReadableTimeFormat(timeSlot.getMeetingStartTime(),timeSlot.getMeetingEndTime()));

        if (timeSlot.isStatus()){
            cvTimeSlot.setCardBackgroundColor(ContextCompat.getColor(cvTimeSlot.getContext(),R.color.color_selected_state));
            meetingTime.setTextColor(ContextCompat.getColor(cvTimeSlot.getContext(),R.color.color_default_state));

        }else {
            cvTimeSlot.setCardBackgroundColor(ContextCompat.getColor(cvTimeSlot.getContext(),R.color.color_default_state));
            meetingTime.setTextColor(ContextCompat.getColor(cvTimeSlot.getContext(),R.color.color_selected_state));
        }

        cvTimeSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClickAction(timeSlot);
            }
        });
    }

}
