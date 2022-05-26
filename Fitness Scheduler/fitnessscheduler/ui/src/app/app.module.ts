import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ActivityService } from "./service/ActivityService/activity.service";
import { DayPlannerComponent } from './components/day-planner/day-planner.component';
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from "@angular/forms";
import { SummaryComponent } from './components/summary/summary.component';


@NgModule({
  declarations: [
    AppComponent,
    DayPlannerComponent,
    SummaryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule,
    FormsModule,
  ],
  providers: [ActivityService],
  bootstrap: [AppComponent]
})
export class AppModule { }
