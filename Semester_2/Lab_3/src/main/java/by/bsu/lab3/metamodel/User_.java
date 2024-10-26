package by.bsu.lab3.metamodel;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import by.bsu.lab3.entity.Car;
import by.bsu.lab3.entity.Trip;
import by.bsu.lab3.entity.User;

@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> login;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SingularAttribute<User, String> birthdate;
    public static volatile ListAttribute<User, Car> cars;
    public static volatile ListAttribute<User, Trip> trips;
}
