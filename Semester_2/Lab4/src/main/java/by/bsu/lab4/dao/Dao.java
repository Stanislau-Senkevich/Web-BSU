package by.bsu.dao;

import by.bsu.lab4.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> getById(Integer id) throws DaoException, InterruptedException;
    List<T> getAll() throws DaoException, InterruptedException;
    void insert(T t) throws DaoException, InterruptedException;
    void delete(T t) throws DaoException, InterruptedException;
}

