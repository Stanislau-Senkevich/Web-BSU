package by.bsu.lab3.metamodel;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import by.bsu.lab3.entity.Car;
import by.bsu.lab3.entity.Trip;
import by.bsu.lab3.entity.User;

@StaticMetamodel(Trip.class)
public class Trip_ extends Application_ {
    public static volatile SingularAttribute<Trip, User> driver;
    public static volatile SingularAttribute<Trip, Car> car;
    public static volatile SingularAttribute<Trip, Boolean> isCompleted;
}

