package by.bsu.lab6.repository;

import by.bsu.lab6.entity.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {
}
