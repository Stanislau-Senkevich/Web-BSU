package dao;

import entity.Car;
import exception.DaoException;
import logger.LoggerManager;
import org.apache.logging.log4j.Logger;
import storage.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static factory.CarFactory.createCar;

public class CarDao implements Dao<Car> {
    private static final Logger logger = LoggerManager.getLogger(CarDao.class.getName());
    private static final String SELECT_BY_ID = "SELECT * FROM car WHERE id = ?;";
    private static final String SELECT_ALL_CARS = "SELECT * FROM car;";
    private static final String SELECT_FIXING_CAR = "SELECT * FROM car WHERE fix_state like '%Repair%'";
    private static final String INSERT_CAR = "INSERT INTO car(driver_id, fix_state, state_number) VALUES(?, ?, ?);";
    private static final String PUT_CAR_ON_FIX = "UPDATE car SET fix_state = 'Under Repair' WHERE id = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM car WHERE id = ?";

    public Optional<Car> getById(Integer id) throws DaoException {
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
                    return Optional.of(mapCarFromResultSet(resultSet));
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

    public List<Car> getAll() throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        List<Car> cars = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_CARS)) {
            while (resultSet.next()) {
                cars.add(mapCarFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error executing query for getAll", e);
            throw new DaoException("Error executing query for getAll", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return cars;
    }

    public List<Car> getFixingCar() throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        List<Car> cars = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_FIXING_CAR)) {
            while (resultSet.next()) {
                cars.add(mapCarFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error executing query for getFixingCar", e);
            throw new DaoException("Error executing query for getFixingCar", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return cars;
    }

    public void insert(Car t) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAR);
            preparedStatement.setInt(1, t.getDriverId());
            preparedStatement.setString(2, t.getFixState());
            preparedStatement.setString(3, t.getStateNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing insert", e);
            throw new DaoException("Error inserting car", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    public void putOnFix(Integer carId) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(PUT_CAR_ON_FIX);
            preparedStatement.setInt(1, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing putOnFix", e);
            throw new DaoException("Error putting car on fix", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    public void delete(Car t) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, t.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing delete", e);
            throw new DaoException("Error deleting car", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    private static Car mapCarFromResultSet(ResultSet resultSet) throws SQLException {
        return createCar(
                resultSet.getInt("id"),
                resultSet.getInt("driver_id"),
                resultSet.getString("fix_state"),
                resultSet.getString("state_number")
        );
    }
}
