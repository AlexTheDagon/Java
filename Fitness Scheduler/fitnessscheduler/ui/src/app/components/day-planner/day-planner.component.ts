import { Component, OnInit } from '@angular/core';
import {ActivityService} from "../../service/ActivityService/activity.service";
import {ActivatedRoute, Router} from "@angular/router";
import {PlannedActivity} from "../../model/planned-activity";
import {Activity} from "../../model/activity";

@Component({
  selector: 'app-day-planner',
  templateUrl: './day-planner.component.html',
  styleUrls: ['./day-planner.component.scss']
})

export class DayPlannerComponent implements OnInit {

  // This will be the data that i fetch from BE (possible activities and the planned ones)
  activities: any[] = [];
  plannedActivities: any[] = [];

  // These indexes will be used to get save which activity was previously choosen for the current day
  morningIndexSelected = 0;
  nightIndexSelected = 0;


  // These are some initializers for the time pickers
  s_time_m = {hour: 7, minute: 0};
  e_time_m = {hour: 8, minute: 0};
  s_time_n = {hour: 20, minute: 0};
  e_time_n = {hour: 21, minute: 0};



  weekDays: string[] = [
    "Luni",
    "Marti",
    "Miercuri",
    "Joi",
    "Vineri",
  ]

  constructor(
    private activityService: ActivityService,
    private activatedRoute: ActivatedRoute,
  ) { }



  ngOnInit(): void {
    this.retrievePossibleActivities();
    this.retrievePlannedActivities();
  }



  // Based on the current day (from 0 to 4) and the planned activities until now i will initialize the time pickers for the previously saved values.
  inititializeTimesPickers(dayIndex: number):void {
    let morningStartTime: Date = new Date();
    let morningEndTime: Date = new Date();
    let nightStartTime: Date = new Date();
    let nightEndTime: Date = new Date();

    morningStartTime.setTime(+this.plannedActivities[dayIndex]?.startTime);
    morningEndTime.setTime(+this.plannedActivities[dayIndex]?.endTime);
    // The first 5 cell of the plannedActivities array are used for the morning activities (0-4 from Monday-Friday)
    // The next 5 cell are for the evening planned activities
    nightStartTime.setTime(+this.plannedActivities[dayIndex + 5]?.startTime);
    nightEndTime.setTime(+this.plannedActivities[dayIndex + 5]?.endTime);

    this.s_time_m = {hour: morningStartTime.getHours(), minute: morningStartTime.getMinutes()};
    this.e_time_m = {hour: morningEndTime.getHours(), minute: morningEndTime.getMinutes()};
    this.s_time_n = {hour: nightStartTime.getHours(), minute: nightStartTime.getMinutes()};
    this.e_time_n = {hour: nightEndTime.getHours(), minute: nightEndTime.getMinutes()};

  }


  retrievePossibleActivities(): void {
    this.activityService.getActivities()
      .subscribe({
        next: (data) => {
          this.activities = data;
          //console.log(this.activities);
        },
        error: (e) => console.error(e)
      });
  }

  retrievePlannedActivitiesWithReload(): void {
    this.activityService.getPlannedActivities()
      .subscribe({
        next: (data) => {
          let myplannedActivities = data;
          let dayIndex = this.computeDayIndex();
          this.inititializeTimesPickers(dayIndex);

          this.morningIndexSelected = this.activities.findIndex(act => myplannedActivities[dayIndex]?.activity.name == act.name);
          this.nightIndexSelected = this.activities.findIndex(act => myplannedActivities[dayIndex + 5]?.activity.name == act.name);


          if(typeof this.morningIndexSelected === 'undefined') this.morningIndexSelected = 0;
          if(typeof this.nightIndexSelected === 'undefined') this.nightIndexSelected = 0;

          location.reload();

        },
        error: (e) => console.error(e)
      });
  }

  retrievePlannedActivities(): void {
    this.activityService.getPlannedActivities()
      .subscribe({
        next: (data) => {
          this.plannedActivities = data;
          console.log(data);
          let dayIndex = this.computeDayIndex();
          this.inititializeTimesPickers(dayIndex);

          this.morningIndexSelected = this.activities.findIndex(act => this.plannedActivities[dayIndex]?.activity.name == act.name);
          this.nightIndexSelected = this.activities.findIndex(act => this.plannedActivities[dayIndex + 5]?.activity.name == act.name);


          if(typeof this.morningIndexSelected === 'undefined') this.morningIndexSelected = 0;
          if(typeof this.nightIndexSelected === 'undefined') this.nightIndexSelected = 0;


        },
        error: (e) => console.error(e)
      });
  }

  updatePlannedActivities(plannedActivity: any): void {
    this.activityService.updatePlannedActivities(plannedActivity)
      .subscribe({
        next: (data) => {
          this.plannedActivities = data;
        },
        error: (e) => console.error(e)
      });
  }

  computeDayIndex(): number {
    let dayIndex = (+this.activatedRoute.snapshot.params['dayIndex']);
    if(isNaN(dayIndex)) dayIndex = 0;
    if(dayIndex < 0) dayIndex = 0;
    if(dayIndex > 4) dayIndex = 4;
    return dayIndex;
  }

  permissionPrevDay(dayIndex: number) {
    return dayIndex > 0;
  }

  permissionNextDay(dayIndex: number) {
    return dayIndex < 4;
  }

  permissionSummary(dayIndex: number) {
    return dayIndex == 4;
  }

  computeDay(index: number): string {
    return this.weekDays[index];
  }

  saveActivities(): void {


    let selectMorning = document.getElementById('morningActivity');
    // @ts-ignore
    let indexMorning = selectMorning.options[selectMorning.selectedIndex]?.value;

    let morningStartTime = new Date();
    morningStartTime.setHours(this.s_time_m.hour);
    morningStartTime.setMinutes(this.s_time_m.minute);
    morningStartTime.setSeconds(0);
    morningStartTime.setMilliseconds(0);

    let morningEndTime = new Date();
    morningEndTime.setHours(this.e_time_m.hour);
    morningEndTime.setMinutes(this.e_time_m.minute);
    morningEndTime.setSeconds(0);
    morningEndTime.setMilliseconds(0);

    const morningActivity: PlannedActivity = {
      activity: this.activities[indexMorning],
      day: this.computeDayIndex(),
      startTime: morningStartTime.getTime().toString(),
      endTime: morningEndTime.getTime().toString(),
    };

    let selectNight = document.getElementById('nightActivity');
    // @ts-ignore
    let indexNight = selectNight.options[selectNight.selectedIndex].value;

    let nightStartTime = new Date();
    nightStartTime.setHours(this.s_time_n.hour);
    nightStartTime.setMinutes(this.s_time_n.minute);
    nightStartTime.setSeconds(0);
    nightStartTime.setMilliseconds(0);

    let nightEndTime = new Date();
    nightEndTime.setHours(this.e_time_n.hour);
    nightEndTime.setMinutes(this.e_time_n.minute);
    nightEndTime.setSeconds(0);
    nightEndTime.setMilliseconds(0);

    const nightActivity: PlannedActivity = {
      activity: this.activities[indexNight],
      day: this.computeDayIndex() + 5,
      startTime: nightStartTime.getTime().toString(),
      endTime: nightEndTime.getTime().toString(),
    };


    this.updatePlannedActivities(morningActivity);
    this.updatePlannedActivities(nightActivity);
    this.retrievePlannedActivitiesWithReload();

  }

  selectedNightAct(activity: Activity): any {
    if(this.activities.indexOf(activity) == this.nightIndexSelected) return true;
    return null;
  }
  selectedMornAct(activity: Activity): any {
    if(this.activities.indexOf(activity) == this.morningIndexSelected) return true;
    return null;
  }
}
