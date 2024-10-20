package org.lab3.factory;

import org.lab3.entity.Trip;

import java.time.LocalDate;

public class TripFactory {
    public static Trip createTrip(Integer id,
                                  String departure,
                                  LocalDate departure_time,
                                  String destination,
                                  LocalDate destination_time,
                                  Integer driver_id,
                                  Integer car_id,
                                  Boolean is_completed) {
        return new Trip(id,
                departure,
                departure_time,
                destination,
                destination_time,
                driver_id,
                car_id,
                is_completed);
    }
}
