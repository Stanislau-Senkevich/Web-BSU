package dao;

import entity.Dispatcher;
import exception.DaoException;
import logger.LoggerManager;
import org.apache.logging.log4j.Logger;
import storage.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static factory.DispatcherFactory.createDispatcher;

public class DispatcherDao implements Dao<Dispatcher> {
    private static final Logger logger = LoggerManager.getLogger(DispatcherDao.class.getName());
    private static final String SELECT_BY_ID = "SELECT * FROM dispatcher WHERE id = ?;";
    private static final String SELECT_ALL_DISPATCHERS = "SELECT * FROM dispatcher;";
    private static final String INSERT_DISPATCHER = "INSERT INTO dispatcher(name, surname, birthdate) VALUES(?, ?, ?);";
    private static final String DELETE_BY_ID = "DELETE FROM dispatcher WHERE id = ?";

    public Optional<Dispatcher> getById(Integer id) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapDispatcherFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing query for getById", e);
            throw new DaoException("Error executing query for getById", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return Optional.empty();
    }

    public List<Dispatcher> getAll() throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        List<Dispatcher> dispatchers = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_DISPATCHERS)) {
            while (resultSet.next()) dispatchers.add(mapDispatcherFromResultSet(resultSet));
        } catch (SQLException e) {
            logger.error("Error executing query for getAll", e);
            throw new DaoException("Error executing query for getAll", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return dispatchers;
    }

    public void insert(Dispatcher dispatcher) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DISPATCHER)) {
            preparedStatement.setString(1, dispatcher.getName());
            preparedStatement.setString(2, dispatcher.getSurname());
            preparedStatement.setString(3, dispatcher.getBirthDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing insert", e);
            throw new DaoException("Error inserting dispatcher", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    public void delete(Dispatcher dispatcher) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, dispatcher.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing delete", e);
            throw new DaoException("Error deleting dispatcher", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    private static Dispatcher mapDispatcherFromResultSet(ResultSet resultSet) throws SQLException {
        return createDispatcher(
                resultSet.getInt("id"),
                "",
                "",
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("birthdate")
        );
    }
}
