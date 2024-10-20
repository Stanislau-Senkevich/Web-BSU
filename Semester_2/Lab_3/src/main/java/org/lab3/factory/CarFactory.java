package org.lab3.factory;

import org.lab3.entity.Car;

public class CarFactory {
    public static Car createCar(Integer id, Integer driver_id, String fix_state, String state_number) {
        return new Car(id, driver_id, fix_state, state_number);
    }
}
