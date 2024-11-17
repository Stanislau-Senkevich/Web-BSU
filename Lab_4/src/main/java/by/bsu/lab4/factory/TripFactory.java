package by.bsu.lab3.factory;

import by.bsu.lab3.entity.Car;
import by.bsu.lab3.entity.Trip;
import by.bsu.lab3.entity.User;

import java.time.LocalDate;

public class TripFactory {
    public static Trip createTrip(Integer id,
                                  String departure,
                                  LocalDate departure_time,
                                  String destination,
                                  LocalDate destination_time,
                                  User user,
                                  Car car,
                                  Boolean is_completed) {
        return new Trip(id,
                departure,
                departure_time,
                destination,
                destination_time,
                user,
                car,
                is_completed);
    }
}
