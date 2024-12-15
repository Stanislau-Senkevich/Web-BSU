package by.bsu.lab6.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Entity
@Table(name="car", schema = "public")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private User driver;

    @Column(name = "fix_state", nullable = false)
    private String fix_state;

    @Column(name = "state_number", nullable = false)
    private String state_number;

    public Car() {}

    public Car(Integer id, User driver, String fix_state, String state_number) {
        this.id = id;
        this.driver = driver;
        this.fix_state = fix_state;
        this.state_number = state_number;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", driverId=" + driver.getId() +
                ", fixState='" + fix_state + '\'' +
                ", stateNumber='" + state_number + '\'' +
                '}';
    }
}
