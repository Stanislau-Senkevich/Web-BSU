import { Component } from '@angular/core';
import { Trip } from '../entity/Trip';
import { TripsService } from '../services/trips.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trip-list',
  templateUrl: './trip-list.component.html',
  styleUrl: './trip-list.component.css'
})
export class TripListComponent {
  trips: Trip[] = [];
  selectedTrip: Trip | null = null;

  constructor(
    private tripsService: TripsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.trips = this.tripsService.getTrips();
  }

  onSelect(trip: Trip): void {
    this.selectedTrip = trip;
    this.router.navigate(['/trip', trip.id]);
  }
}
