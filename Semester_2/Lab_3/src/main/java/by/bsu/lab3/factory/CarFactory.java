package by.bsu.lab3.factory;

import by.bsu.lab3.entity.Car;
import by.bsu.lab3.entity.User;

public class CarFactory {
    public static Car createCar(Integer id, User driver, String fix_state, String state_number) {
        return new Car(id, driver, fix_state, state_number);
    }
}
