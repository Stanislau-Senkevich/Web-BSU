package by.bsu.lab4.metamodel;

import by.bsu.lab4.entity.Car;
import by.bsu.lab4.entity.Trip;
import by.bsu.lab4.entity.User;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Trip.class)
public class Trip_ extends Application_ {
    public static volatile SingularAttribute<Trip, User> driver;
    public static volatile SingularAttribute<Trip, Car> car;
    public static volatile SingularAttribute<Trip, Boolean> isCompleted;
}

