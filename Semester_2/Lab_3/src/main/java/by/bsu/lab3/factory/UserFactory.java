package by.bsu.lab3.factory;

import by.bsu.lab3.entity.User;

public class UserFactory {
    public static User createUser(Integer id, String login, String password, String name, String surname, String birthdate) {
        return new User(id, login, password, name, surname, birthdate);
    }
}
