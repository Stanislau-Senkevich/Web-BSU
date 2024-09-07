import { Component, Input } from '@angular/core';
import { Trip } from '../entity/Trip';
import { ActivatedRoute } from '@angular/router';
import { TripsService } from '../services/trips.service';

@Component({
  selector: 'app-trip-details',
  templateUrl: './trip-details.component.html',
  styleUrl: './trip-details.component.css'
})
export class TripDetailsComponent {
  @Input() trip: Trip | null = null;

  constructor(
    private route: ActivatedRoute,
    private tripsService: TripsService
  ) {}

  ngOnInit(): void {
    const tripId = this.route.snapshot.paramMap.get('id');
    if (tripId) {
      this.trip = this.tripsService.getTripById(+tripId);
    }
  }
}
