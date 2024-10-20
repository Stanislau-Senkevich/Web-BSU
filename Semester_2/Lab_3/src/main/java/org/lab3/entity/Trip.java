package org.lab3.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "trip")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
        @NamedQuery(name = "Trip.findById", query = "SELECT t FROM Trip t WHERE t.id = :id"),
        @NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t"),
        @NamedQuery(name = "Trip.findByDriverId", query = "SELECT t FROM Trip t WHERE t.driverId = :driverId")
})
public class Trip extends Application {
    @Column(name = "driver_id")
    private Integer driverId;

    @Column(name = "car_id")
    private Integer carId;

    @Column(name = "is_completed")
    private Boolean isCompleted;
    public Trip() {}

    public Trip(
            Integer id,
            String departure,
            LocalDate departureTime,
            String destination,
            LocalDate destinationTime,
            Integer driverId,
            Integer carId,
            Boolean isCompleted
    ) {
        super(id, departure, departureTime, destination, destinationTime);
        this.driverId = driverId;
        this.carId = carId;
        this.isCompleted = isCompleted;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
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
                ", driverId=" + driverId +
                ", carId=" + carId +
                ", isCompleted=" + isCompleted +
                ", departure=" + getDeparture() +
                ", departureTime=" + getDepartureTime() +
                ", destination=" + getDestination() +
                ", destinationTime=" + getDestinationTime() +
                '}';
    }
}
