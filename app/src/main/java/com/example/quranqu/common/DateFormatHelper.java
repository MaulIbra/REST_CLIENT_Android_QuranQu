package com.example.quranqu.common;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Maulana Ibrahim on 04/May/2020
 * Email maulibrahim19@gmail.com
 */
public class DateFormatHelper {

    public String getCurrentDateString(String dFormat) {
        Locale lokal = new Locale("id", "ID");
        SimpleDateFormat dateFormat = new SimpleDateFormat(dFormat, lokal);
        Date currentLocalTime = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00")).getTime();
        return dateFormat.format(currentLocalTime);
    }

    public String convertIndTime(String time) {
        String[] timeSplit = time.split(" ");
        if (timeSplit[1].equals("am")) {
            return timeSplit[0] + ":00";
        } else {
            String[] times = timeSplit[0].split(":");
            int hours = Integer.valueOf(times[0]) + 12;
            return String.valueOf(hours) + ":" + times[1] + ":00";
        }
    }

    public Calendar convertToTimeCalendar(String cal) {
        String[] s = cal.split(":");
        Calendar x = Calendar.getInstance();
        x.set(Calendar.HOUR_OF_DAY, Integer.valueOf(s[0]));
        x.set(Calendar.MINUTE, Integer.valueOf(s[1]));
        x.set(Calendar.SECOND, Integer.valueOf(s[2]));
        return x;
    }

    public Boolean compareTwoTime(String startTime, String endTime) {
        Calendar x = Calendar.getInstance();
        Calendar y = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", new Locale("id", "ID"));
        try {
            x.setTime(sdf.parse(startTime));
            y.setTime(sdf.parse(endTime));
            if (x.compareTo(y) < 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Calendar getDiffTime(Calendar startTime, Calendar endTime) {
        Calendar result = Calendar.getInstance();
        result.set(Calendar.HOUR_OF_DAY,endTime.get(Calendar.HOUR_OF_DAY)- startTime.get(Calendar.HOUR_OF_DAY));
        result.set(Calendar.MINUTE,endTime.get(Calendar.MINUTE)-startTime.get(Calendar.MINUTE));
        result.set(Calendar.SECOND,endTime.get(Calendar.SECOND)-startTime.get(Calendar.SECOND));
        return result;
    }

}
