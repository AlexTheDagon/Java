import {Activity} from "./activity";

export interface PlannedActivity {
  activity: Activity,
  day: number,
  startTime: string,
  endTime: string,
}
