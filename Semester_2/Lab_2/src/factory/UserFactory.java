package factory;

import entity.User;

public class UserFactory {
    public static User createUser(Integer id, String login, String password) {
        return new User(id, login, password);
    }
}
