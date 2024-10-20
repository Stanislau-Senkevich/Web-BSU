package factory;

import entity.Trip;

public class TripFactory {
    public static Trip createTrip(Integer id,
                                  String departure,
                                  String departure_time,
                                  String destination,
                                  String destination_time,
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
