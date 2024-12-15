package by.bsu.lab6.repository;

import by.bsu.lab6.entity.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
}
