package com.example.anydonemeetingschedule.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anydonemeetingschedule.R;
import com.example.anydonemeetingschedule.model.TimeSlot;

import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter implements OnItemClick {

    List<TimeSlot> listOfMeetingTime;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_time_slot, parent, false);
        return new TimeSlotVH(view, this::onItemClickAction);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TimeSlotVH timeSlotVH = (TimeSlotVH) holder;
        timeSlotVH.onBind(listOfMeetingTime.get(position));
    }

    @Override
    public int getItemCount() {
        if (listOfMeetingTime != null) {
            return listOfMeetingTime.size();
        } else {
            return 0;
        }
    }

    public void updateTimeSlot(List<TimeSlot> listOfMeetingTime) {
        this.listOfMeetingTime = listOfMeetingTime;
        notifyDataSetChanged();
    }

    @Override
    public void onItemClickAction(TimeSlot timeSlot) {
        for (TimeSlot timeData : listOfMeetingTime) {
            if (timeData.getId() == timeSlot.getId()){
                timeData.setStatus(true);

            }else {
                timeData.setStatus(false);
            }
        }
        notifyDataSetChanged();
    }
}
