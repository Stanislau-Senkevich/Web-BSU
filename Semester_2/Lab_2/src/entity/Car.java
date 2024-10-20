package entity;

public class Car {
    public Car(Integer id, Integer driver_id, String fix_state, String state_number) {
        this.id = id;
        this.driver_id = driver_id;
        this.fix_state = fix_state;
        this.state_number = state_number;
    }
    private Integer id;
    private Integer driver_id;
    private String fix_state;
    private String state_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverId() {
        return driver_id;
    }

    public void setDriverId(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public String getFixState() {
        return fix_state;
    }

    public void setFixState(String fix_state) {
        this.fix_state = fix_state;
    }

    public String getStateNumber() {
        return state_number;
    }

    public void setStateNumber(String state_number) {
        this.state_number = state_number;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", driver_id=" + driver_id +
                ", fix_state='" + fix_state + '\'' +
                ", state_number='" + state_number + '\'' +
                '}';
    }
}
