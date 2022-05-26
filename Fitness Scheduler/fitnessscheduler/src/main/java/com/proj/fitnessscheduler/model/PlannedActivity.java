package com.proj.fitnessscheduler.model;

import java.sql.Time;


public class PlannedActivity {
    private Activity activity;
    private Integer day;
    private String startTime;
    private String endTime;

    public PlannedActivity(Activity activity, Integer day, String startTime, String endTime) {
        this.activity = activity;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
