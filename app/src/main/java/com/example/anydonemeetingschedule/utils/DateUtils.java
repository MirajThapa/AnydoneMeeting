package com.example.anydonemeetingschedule.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtils {

    public static String getReadableTimeFormat(long start, long end){
        return millisecondToDate(start)+" - "+millisecondToDate(end);
    }

    public static String millisecondToDate(long t) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String time = simpleDateFormat.format(t);
        return time;
    }
}
