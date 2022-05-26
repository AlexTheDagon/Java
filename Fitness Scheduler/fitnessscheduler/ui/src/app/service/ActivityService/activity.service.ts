import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {PlannedActivity} from "../../model/planned-activity";
const baseUrl = 'http://localhost:8080/activity';

@Injectable({
  providedIn: 'root'
})

export class ActivityService {


  constructor(private http: HttpClient) { }

  getActivities(): Observable<any[]> {
    return this.http.get<any[]>("http://localhost:8080/activity/getActivities");
  }

  getPlannedActivities(): Observable<any[]> {
    return this.http.get<any[]>("http://localhost:8080/activity/getPlannedActivities");
  }

  updatePlannedActivities(data: PlannedActivity): Observable<any[]> {
    return this.http.post<any[]>("http://localhost:8080/activity/updatePlannedActivities", data);
  }

}
