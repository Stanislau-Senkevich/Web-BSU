package by.bsu.lab5.factory;

import by.bsu.lab5.entity.User;

public class UserFactory {
    public static User createUser(Integer id, String login, String password, String name, String surname, String birthdate, String access_role) {
        return new User(id, login, password, name, surname, birthdate, access_role);
    }
}
