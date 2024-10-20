package factory;

import entity.Car;

public class CarFactory {
    public static Car createCar(Integer id, Integer driver_id, String fix_state, String state_number) {
        return new Car(id, driver_id, fix_state, state_number);
    }
}
