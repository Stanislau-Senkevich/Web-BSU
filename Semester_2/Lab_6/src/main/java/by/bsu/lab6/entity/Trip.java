package by.bsu.lab6.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Data
@Entity
@Table(name="trip", schema = "public")
public class Trip {
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

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = false)
    private User driver;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
    private Car car;

    @Column(name = "is_completed")
    private Boolean isCompleted;
    public Trip() {}

    public Trip(
            Integer id,
            String departure,
            LocalDate departureTime,
            String destination,
            LocalDate destinationTime,
            User driver,
            Car car,
            Boolean isCompleted
    ) {
        this.id = id;
        this.departure = departure;
        this.departureTime = departureTime;
        this.destination = destination;
        this.destinationTime = destinationTime;
        this.driver = driver;
        this.car = car;
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + getId() +
                ", driverId=" + driver.getId() +
                ", carId=" + car.getId() +
                ", isCompleted=" + isCompleted +
                ", departure=" + getDeparture() +
                ", departureTime=" + getDepartureTime() +
                ", destination=" + getDestination() +
                ", destinationTime=" + getDestinationTime() +
                '}';
    }
}
