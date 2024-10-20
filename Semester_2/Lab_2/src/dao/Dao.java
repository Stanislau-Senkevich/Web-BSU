package dao;

import exception.DaoException;

import java.util.List;
import java.util.Optional;
public interface Dao<T> {
    Optional<T> getById(Integer id) throws DaoException;
    List<T> getAll() throws DaoException;
    void insert(T t) throws DaoException;
    void delete(T t) throws DaoException;
}

