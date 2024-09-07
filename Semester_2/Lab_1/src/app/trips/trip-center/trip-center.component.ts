import { Component } from '@angular/core';
import { Trip } from '../entity/Trip';
import { TripsService } from '../services/trips.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trip-center',
  templateUrl: './trip-center.component.html',
  styleUrl: './trip-center.component.css'
})
export class TripCenterComponent {
  trips: Trip[] = [];

  constructor(private tripsService: TripsService){}

  ngOnInit(): void {
    this.trips = this.tripsService.getTrips();
  }
}
