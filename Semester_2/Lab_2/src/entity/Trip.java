package entity;

public class Trip extends Application {
    private Integer driver_id;

    public Integer getDriverId() {
        return driver_id;
    }

    public void setDriverId(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public Integer getCarId() {
        return car_id;
    }

    public void setCarId(Integer car_id) {
        this.car_id = car_id;
    }

    public Boolean getIsCompleted() {
        return is_completed;
    }

    public void setIsCompleted(Boolean is_completed) {
        this.is_completed = is_completed;
    }

    private Integer car_id;
    private Boolean is_completed;

    public Trip(
            Integer id,
            String departure,
            String departure_time,
            String destination,
            String destination_time,
            Integer driver_id,
            Integer car_id,
            Boolean is_completed
            ) {
        super(id, departure, departure_time, destination, destination_time);
        this.driver_id = driver_id;
        this.car_id = car_id;
        this.is_completed = is_completed;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "driver_id=" + driver_id +
                ", car_id=" + car_id +
                ", is_completed=" + is_completed +
                ", departure=" + getDeparture() +
                ", departure_time=" + getDepartureTime() +
                ", destination=" + getDestination() +
                ", destination_time=" + getDestinationTime() +
                '}';
    }

}
