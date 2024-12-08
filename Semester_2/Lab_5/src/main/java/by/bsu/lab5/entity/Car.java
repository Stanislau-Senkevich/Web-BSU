package by.bsu.lab5.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
@NamedQueries({
        @NamedQuery(name = "Car.findById", query = "SELECT c FROM Car c WHERE c.id = :id"),
        @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c"),
        @NamedQuery(name = "Car.findFixingCars", query = "SELECT c FROM Car c WHERE c.fixState LIKE '%Repair%'"),
        @NamedQuery(name = "Car.putOnFix", query = "UPDATE Car c SET c.fixState = 'Under Repair' WHERE c.id = :carId"),
        @NamedQuery(name = "Car.deleteById", query = "DELETE FROM Car c WHERE c.id = :carId")
})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @Column(name = "fix_state", nullable = false)
    private String fixState;

    @Column(name = "state_number", nullable = false)
    private String stateNumber;

    public Car() {}

    public Car(Integer id, User driver, String fixState, String stateNumber) {
        this.id = id;
        this.driver = driver;
        this.fixState = fixState;
        this.stateNumber = stateNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverId() {
        return driver.getId();
    }

    public String getFixState() {
        return fixState;
    }

    public void setFixState(String fix_state) {
        this.fixState = fix_state;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String state_number) {
        this.stateNumber = stateNumber;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", driverId=" + driver.getId() +
                ", fixState='" + fixState + '\'' +
                ", stateNumber='" + stateNumber + '\'' +
                '}';
    }
}
