package factory;

import entity.Driver;

public class DriverFactory {
    public static Driver createDriver(Integer id, String login, String password, String name, String surname, String birthDate) {
        return new Driver(id, login, password, name, surname, birthDate);
    }
}
