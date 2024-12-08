package by.bsu.lab5.metamodel;

import by.bsu.lab5.entity.Car;
import by.bsu.lab5.entity.User;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Car.class)
public class Car_ {
    public static volatile SingularAttribute<Car, Integer> id;
    public static volatile SingularAttribute<Car, User> driver;
    public static volatile SingularAttribute<Car, String> fixState;
    public static volatile SingularAttribute<Car, String> stateNumber;
}
