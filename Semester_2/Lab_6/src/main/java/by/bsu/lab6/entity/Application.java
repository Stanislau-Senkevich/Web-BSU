package by.bsu.lab6.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Comparator;

@Setter
@Getter
@Data
@Entity
@Table(name="application", schema = "public")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "departure", nullable = false)
    private String departure;

    @Column(name = "departure_time", nullable = false)
    private LocalDate departureTime;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "destination_time", nullable = false)
    private LocalDate destinationTime;

    public Application() {
    }

    public Application(Integer id, String departure, LocalDate departureTime, String destination, LocalDate destinationTime) {
        this.id = id;
        this.departure = departure;
        this.departureTime = departureTime;
        this.destination = destination;
        this.destinationTime = destinationTime;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationTime='" + destinationTime + '\'' +
                '}';
    }

    public static Comparator<Application> getComparator(String field) throws IllegalArgumentException {
        switch (field.toLowerCase()) {
            case "id":
                return Comparator.comparingInt(Application::getId);
            case "departure":
                return Comparator.comparing(Application::getDeparture, Comparator.nullsLast(String::compareTo));
            case "departure_time":
                return Comparator.comparing(Application::getDepartureTime, Comparator.nullsLast(LocalDate::compareTo));
            case "destination":
                return Comparator.comparing(Application::getDestination, Comparator.nullsLast(String::compareTo));
            case "destination_time":
                return Comparator.comparing(Application::getDestinationTime, Comparator.nullsLast(LocalDate::compareTo));
            default:
                throw new IllegalArgumentException("Unknown field: " + field);
        }
    }
}
