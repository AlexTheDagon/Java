package com.proj.fitnessscheduler.model;

public class Activity {

    private String name;
    private Integer kcalH;

    public Activity(String name, Integer kcalH) {
        this.name = name;
        this.kcalH = kcalH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKcalH() {
        return kcalH;
    }

    public void setKcalH(Integer kcalH) {
        this.kcalH = kcalH;
    }
}
