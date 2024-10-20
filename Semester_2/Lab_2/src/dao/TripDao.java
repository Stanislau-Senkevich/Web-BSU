package dao;

import entity.Trip;
import exception.DaoException;
import logger.LoggerManager;
import org.apache.logging.log4j.Logger;
import storage.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static factory.TripFactory.createTrip;

public class TripDao implements Dao<Trip> {
    private static final Logger logger = LoggerManager.getLogger(TripDao.class.getName());
    private static final String SELECT_BY_ID = "SELECT * FROM trip WHERE id = ?;";
    private static final String SELECT_ALL_TRIPS = "SELECT * FROM trip;";
    private static final String SELECT_BY_DRIVER_ID = "SELECT * FROM trip WHERE driver_id = ?;";
    private static final String INSERT_TRIP = "INSERT INTO trip(departure, departure_time, destination, destination_time, driver_id, car_id, is_completed) VALUES(?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_BY_ID = "DELETE FROM trip WHERE id = ?";

    public Optional<Trip> getById(Integer id) throws DaoException {
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
                    return Optional.of(mapTripFromResultSet(resultSet));
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

    public List<Trip> getByDriverId(Integer driverId) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        List<Trip> trips = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DRIVER_ID)) {
            preparedStatement.setInt(1, driverId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    trips.add(mapTripFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing query for getByDriverId", e);
            throw new DaoException("Error executing query for getByDriverId", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return trips;
    }

    public List<Trip> getAll() throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        List<Trip> trips = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_TRIPS)) {
            while (resultSet.next()) {
                trips.add(mapTripFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error executing query for getAll", e);
            throw new DaoException("Error executing query for getAll", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return trips;
    }

    public void insert(Trip trip) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRIP)) {
            preparedStatement.setString(1, trip.getDeparture());
            preparedStatement.setDate(2, Date.valueOf(trip.getDepartureTime()));
            preparedStatement.setString(3, trip.getDestination());
            preparedStatement.setDate(4, Date.valueOf(trip.getDestinationTime()));
            preparedStatement.setInt(5, trip.getDriverId());
            preparedStatement.setInt(6, trip.getCarId());
            preparedStatement.setBoolean(7, trip.getIsCompleted());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing insert", e);
            throw new DaoException("Error inserting trip", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    public void delete(Trip trip) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, trip.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing delete", e);
            throw new DaoException("Error deleting trip", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    private static Trip mapTripFromResultSet(ResultSet resultSet) throws SQLException {
        return createTrip(
                resultSet.getInt("id"),
                resultSet.getString("departure"),
                resultSet.getString("departure_time"),
                resultSet.getString("destination"),
                resultSet.getString("destination_time"),
                resultSet.getInt("driver_id"),
                resultSet.getInt("car_id"),
                resultSet.getBoolean("is_completed")
        );
    }
}
