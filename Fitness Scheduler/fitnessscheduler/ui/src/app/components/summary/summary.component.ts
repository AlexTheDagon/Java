import { Component, OnInit } from '@angular/core';
import {ActivityService} from "../../service/ActivityService/activity.service";
import {ActivatedRoute} from "@angular/router";
import {PlannedActivity} from "../../model/planned-activity";

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.scss']
})
export class SummaryComponent implements OnInit {

  plannedActivities: any[] = [];

  weekDays: string[] = [
    "Luni",
    "Marti",
    "Miercuri",
    "Joi",
    "Vineri",
  ]

  constructor(
    private activityService: ActivityService,
  ) { }

  ngOnInit(): void {
    this.retrievePlannedActivities();
  }

  retrievePlannedActivities(): void {
    this.activityService.getPlannedActivities()
      .subscribe({
        next: (data) => {
          this.plannedActivities = data;
          //console.log(this.plannedActivities);
        },
        error: (e) => console.error(e)
      });
  }

  tt(t: number): string {
    let tt = t.toString();
    if(tt.length == 1) tt = '0' + tt;
    return tt;
  }

  convertPlannedActivityToString(plannedActivity: PlannedActivity): string {
    let s = "";
    let startTime: Date = new Date();
    let endTime: Date = new Date();
    startTime.setTime(+plannedActivity?.startTime);
    endTime.setTime(+plannedActivity?.endTime);
    s += this.tt(startTime.getHours());
    s += ':';
    s += this.tt(startTime.getMinutes());
    s += '-';
    s += this.tt(endTime.getHours());
    s += ':';
    s += this.tt(endTime.getMinutes());
    s += "          ";
    s += plannedActivity?.activity.name;
    return s;
  }

  totalTime(): number {
    let totalTime:number = 0;
    this.plannedActivities.forEach(pA => {
      let startTime: Date = new Date();
      let endTime: Date = new Date();
      startTime.setTime(+pA?.startTime);
      endTime.setTime(+pA?.endTime);
      totalTime += Math.abs(endTime.getTime() - startTime.getTime());
    });
    return totalTime / 1000 / 60;
  }

  totalTimeAsString():string {
    let s = "";
    let totalTime = this.totalTime();
    s += Math.floor(totalTime/60).toString();
    s += " ore "
    if((totalTime%60) != 0) {
      s += "si ";
      s += (totalTime%60).toString();
      s += " minut"
      if((totalTime%60) > 1) s+= 'e';
    }

    return s;
  }

  totalCalories(): number {
    let calories:number = 0;
    this.plannedActivities.forEach(pA => {
      let startTime: Date = new Date();
      let endTime: Date = new Date();
      startTime.setTime(+pA?.startTime);
      endTime.setTime(+pA?.endTime);
      calories += Math.floor(Math.abs(endTime.getTime() - startTime.getTime()) * pA.activity.kcalH / 1000 / 3600);
    });
    return calories;
  }
}


