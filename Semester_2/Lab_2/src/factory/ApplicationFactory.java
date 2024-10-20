package factory;

import entity.Application;

public class ApplicationFactory {
    public static Application createApplication(Integer id, String departure, String departure_time, String destination, String destination_time) {
        return new Application(id, departure, departure_time, destination, destination_time);
    }
}
