package by.bsu.lab6.repository;

import by.bsu.lab6.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
