package factory;

import entity.Dispatcher;

public class DispatcherFactory {
    public static Dispatcher createDispatcher(Integer id, String login, String password, String name, String surname, String birthDate) {
        return new Dispatcher(id, login, password, name, surname, birthDate);
    }
}
