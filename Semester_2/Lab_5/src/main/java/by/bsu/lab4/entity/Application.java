package by.bsu.lab4.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Comparator;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name = "application")
@NamedQueries({
        @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a"),
        @NamedQuery(name = "Application.findById", query = "SELECT a FROM Application a WHERE a.id = :id"),
        @NamedQuery(name = "Application.findByDeparture", query = "SELECT a FROM Application a WHERE a.departure = :departure"),
        @NamedQuery(name = "Application.findByDestination", query = "SELECT a FROM Application a WHERE a.destination = :destination")
})
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

    // Getters and Setters
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

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(LocalDate destinationTime) {
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

    // Comparator
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
