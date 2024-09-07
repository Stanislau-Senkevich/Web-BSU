import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripCenterComponent } from './trip-center.component';

describe('TripCenterComponent', () => {
  let component: TripCenterComponent;
  let fixture: ComponentFixture<TripCenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TripCenterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TripCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
