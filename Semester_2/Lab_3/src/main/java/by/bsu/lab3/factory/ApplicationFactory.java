package by.bsu.lab3.factory;

import by.bsu.lab3.entity.Application;

import java.time.LocalDate;

public class ApplicationFactory {
    public static Application createApplication(Integer id, String departure, LocalDate departure_time, String destination, LocalDate destination_time) {
        return new Application(id, departure, departure_time, destination, destination_time);
    }
}
