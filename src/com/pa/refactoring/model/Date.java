package com.pa.refactoring.model;

import java.time.LocalDateTime;

public class Date {
    private int day, year,month,hour, minute;

    public Date(){
        LocalDateTime d= LocalDateTime.now();
        day=d.getDayOfMonth();
        month=d.getMonthValue();
        year=d.getYear();
        hour=d.getHour();
        minute =d.getMinute();
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        String  dateStr= String.format("%02d/%02d/%4d %02d:%02d", day,month,year,hour, minute);
        return dateStr;
    }
}
