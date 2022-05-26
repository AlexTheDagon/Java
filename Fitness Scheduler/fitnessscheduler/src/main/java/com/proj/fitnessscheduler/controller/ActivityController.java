package com.proj.fitnessscheduler.controller;


import com.proj.fitnessscheduler.model.Activity;
import com.proj.fitnessscheduler.model.PlannedActivity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/activity")
public class ActivityController {






    List<Activity> initializeActivities() {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity("Inot", 100));
        activities.add(new Activity("Alergat", 300));
        activities.add(new Activity("Sala", 200));
        activities.add(new Activity("Karate", 150));
        activities.add(new Activity("Judo", 200));
        return activities;
    }

    List<PlannedActivity> initializePlannedActivities() {
        List<Activity> activities = initializeActivities();
        List<PlannedActivity> plannedActivities = new ArrayList<>();
        for(int i = 0; i < 5; ++ i) {
            plannedActivities.add(new PlannedActivity( activities.get(i), i, "1653537600000", "1653541200000"));
        }
        for(int i = 0; i < 5; ++ i) {
            plannedActivities.add(new PlannedActivity( activities.get(i), i, "1653584400000", "1653588000000"));
        }
        return plannedActivities;
    }

    private List<PlannedActivity> plannedActivities = initializePlannedActivities();

    @RequestMapping(method = RequestMethod.GET, value = "/getActivities")
    @ResponseBody
    public List<Activity> getAllAnswers() {
        List<Activity> list = initializeActivities();
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPlannedActivities")
    @ResponseBody
    public List<PlannedActivity> getPlannedActivities() {
        return this.plannedActivities;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updatePlannedActivities")
    @ResponseBody
    public List<PlannedActivity> updatePlannedActivities(@RequestBody PlannedActivity plannedActivity) {
        Integer dayIndex = plannedActivity.getDay();
        //System.out.println(plannedActivity.getStartTime());
        plannedActivities.set(dayIndex, plannedActivity);
        return plannedActivities;
    }

}
