package by.bsu.lab6.repository;

import by.bsu.lab6.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
