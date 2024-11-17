package by.bsu.lab3.metamodel;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import by.bsu.lab3.entity.Car;
import by.bsu.lab3.entity.User;

@StaticMetamodel(Car.class)
public class Car_ {
    public static volatile SingularAttribute<Car, Integer> id;
    public static volatile SingularAttribute<Car, User> driver;
    public static volatile SingularAttribute<Car, String> fixState;
    public static volatile SingularAttribute<Car, String> stateNumber;
}
