import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TripCenterComponent } from './trip-center/trip-center.component';
import { TripListComponent } from './trip-list/trip-list.component';
import { TripDetailsComponent } from './trip-details/trip-details.component';



@NgModule({
  declarations: [
    TripCenterComponent,
    TripListComponent,
    TripDetailsComponent
  ],
  imports: [
    CommonModule
  ]
})
export class TripsModule { }
