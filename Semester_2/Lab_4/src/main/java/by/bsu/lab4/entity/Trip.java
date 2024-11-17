package by.bsu.lab4.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "trip")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
        @NamedQuery(name = "Trip.findById", query = "SELECT t FROM Trip t WHERE t.id = :id"),
        @NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t"),
        @NamedQuery(name = "Trip.findByDriverId", query = "SELECT t FROM Trip t WHERE t.driver.id = :driverId")
})
public class Trip extends Application {
    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
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
        super(id, departure, departureTime, destination, destinationTime);
        this.driver = driver;
        this.car = car;
        this.isCompleted = isCompleted;
    }

    public Integer getDriverId() {
        return driver.getId();
    }

    public Integer getCarId() {
        return car.getId();
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
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
