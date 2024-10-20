package dao;

import entity.Driver;
import exception.DaoException;
import logger.LoggerManager;
import org.apache.logging.log4j.Logger;
import storage.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static factory.DriverFactory.createDriver;

public class DriverDao implements Dao<Driver> {
    private static final Logger logger = LoggerManager.getLogger(DriverDao.class.getName());
    private static final String SELECT_BY_ID = "SELECT * FROM driver WHERE id = ?;";
    private static final String SELECT_ALL_DRIVERS = "SELECT * FROM driver;";
    private static final String INSERT_DRIVER = "INSERT INTO driver(name, surname, birthdate) VALUES(?, ?, ?);";
    private static final String DELETE_BY_ID = "DELETE FROM driver WHERE id = ?";

    public Optional<Driver> getById(Integer id) throws DaoException {
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
                    return Optional.of(mapDriverFromResultSet(resultSet));
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

    public List<Driver> getAll() throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        List<Driver> drivers = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_DRIVERS)) {
            while (resultSet.next()) drivers.add(mapDriverFromResultSet(resultSet));
        } catch (SQLException e) {
            logger.error("Error executing query for getAll", e);
            throw new DaoException("Error executing query for getAll", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return drivers;
    }

    public void insert(Driver driver) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRIVER)) {
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getSurname());
            preparedStatement.setString(3, driver.getBirthDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing insert", e);
            throw new DaoException("Error inserting driver", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    public void delete(Driver driver) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, driver.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing delete", e);
            throw new DaoException("Error deleting driver", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    private static Driver mapDriverFromResultSet(ResultSet resultSet) throws SQLException {
        return createDriver(
                resultSet.getInt("id"),
                "",
                "",
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("birthdate")
        );
    }
}
