import { Injectable } from '@angular/core';
import { mockTrips } from '../entity/mock-trip-list';
import { Trip } from '../entity/Trip';

@Injectable({
  providedIn: 'root'
})
export class TripsService {
  private trips = mockTrips;

  getTrips() {
    return this.trips;
  }

  getTripById(id: number): Trip | null {
    return this.trips.find((trip) => trip.id === id) || null;
  }
}
