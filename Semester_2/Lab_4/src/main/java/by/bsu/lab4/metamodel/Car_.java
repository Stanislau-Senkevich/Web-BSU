package by.bsu.lab4.metamodel;

import by.bsu.lab4.entity.Car;
import by.bsu.lab4.entity.User;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Car.class)
public class Car_ {
    public static volatile SingularAttribute<Car, Integer> id;
    public static volatile SingularAttribute<Car, User> driver;
    public static volatile SingularAttribute<Car, String> fixState;
    public static volatile SingularAttribute<Car, String> stateNumber;
}
