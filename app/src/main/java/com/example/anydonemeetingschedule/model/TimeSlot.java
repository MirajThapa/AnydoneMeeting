package com.example.anydonemeetingschedule.model;

public class TimeSlot {
    String id;
    long meetingStartTime;
    long meetingEndTime;
    boolean status = false;

    public TimeSlot(String id, long meetingStartTime, long meetingEndTime, boolean status) {
        this.id = id;
        this.meetingStartTime = meetingStartTime;
        this.meetingEndTime = meetingEndTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMeetingStartTime() {
        return meetingStartTime;
    }

    public void setMeetingStartTime(long meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    public long getMeetingEndTime() {
        return meetingEndTime;
    }

    public void setMeetingEndTime(long meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
