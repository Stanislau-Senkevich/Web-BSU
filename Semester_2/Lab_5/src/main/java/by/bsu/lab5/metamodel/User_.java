package by.bsu.lab5.metamodel;

import by.bsu.lab5.entity.Car;
import by.bsu.lab5.entity.Trip;
import by.bsu.lab5.entity.User;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> login;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SingularAttribute<User, String> birthdate;
    public static volatile SingularAttribute<User, String> access_role;
    public static volatile ListAttribute<User, Car> cars;
    public static volatile ListAttribute<User, Trip> trips;
}
