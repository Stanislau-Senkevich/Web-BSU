package by.bsu.lab5.metamodel;

import by.bsu.lab5.entity.Application;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.time.LocalDate;

@StaticMetamodel(Application.class)
public class Application_ {
    public static volatile SingularAttribute<Application, Integer> id;
    public static volatile SingularAttribute<Application, String> departure;
    public static volatile SingularAttribute<Application, LocalDate> departureTime;
    public static volatile SingularAttribute<Application, String> destination;
    public static volatile SingularAttribute<Application, LocalDate> destinationTime;
}
