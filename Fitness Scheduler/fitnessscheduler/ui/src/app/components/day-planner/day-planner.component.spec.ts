import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DayPlannerComponent } from './day-planner.component';

describe('DayPlannerComponent', () => {
  let component: DayPlannerComponent;
  let fixture: ComponentFixture<DayPlannerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DayPlannerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DayPlannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
