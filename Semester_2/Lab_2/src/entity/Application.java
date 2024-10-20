package entity;

import java.util.Comparator;

public class Application {
    private Integer id;
    private String departure;
    private String departure_time;
    private String destination;
    private String destination_time;

    public Application(Integer id, String departure, String departure_time, String destination, String destination_time) {
        this.id = id;
        this.departure = departure;
        this.departure_time = departure_time;
        this.destination = destination;
        this.destination_time = destination_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDepartureTime() {
        return departure_time;
    }

    public void setDepartureTime(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationTime() {
        return destination_time;
    }

    public void setDestinationTime(String destination_time) {
        this.destination_time = destination_time;
    }

    public String toString() {
        return "Application{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", destination='" + destination + '\'' +
                ", destination_time='" + destination_time + '\'' +
                '}';
    }

    public static Comparator<Application> getComparator(String field) throws IllegalArgumentException {
        switch (field.toLowerCase()) {
            case "id":
                return Comparator.comparingInt(Application::getId);
            case "departure":
                return Comparator.comparing(Application::getDeparture, Comparator.nullsLast(String::compareTo));
            case "departure_time":
                return Comparator.comparing(Application::getDepartureTime, Comparator.nullsLast(String::compareTo));
            case "destination":
                return Comparator.comparing(Application::getDestination, Comparator.nullsLast(String::compareTo));
            case "destination_time":
                return Comparator.comparing(Application::getDestinationTime, Comparator.nullsLast(String::compareTo));
            default:
                throw new IllegalArgumentException("Unknown field: " + field);
        }
    }

}
