import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DayPlannerComponent} from "./components/day-planner/day-planner.component";
import {SummaryComponent} from "./components/summary/summary.component";

const routes: Routes = [
  { path:"dayplanner/:dayIndex", component: DayPlannerComponent },
  { path:"summary", component: SummaryComponent },
  { path:"", redirectTo:"dayplanner/0", pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
