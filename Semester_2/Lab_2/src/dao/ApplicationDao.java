package dao;

import entity.Application;
import exception.DaoException;
import logger.LoggerManager;
import storage.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Logger;

import static factory.ApplicationFactory.createApplication;

public class ApplicationDao implements Dao<Application>{
    private static final Logger logger = LoggerManager.getLogger(ApplicationDao.class.getName());
    private static final String SELECT_BY_ID = "SELECT * FROM application WHERE id = ?;";
    private static final String SELECT_ALL_APPLICATIONS = "SELECTTTT * FROM application;";
    private static final String INSERT_APPLICATION = "INSERT INTO application(departure, departure_time, destination, destination_time) VALUES(?, ?, ?, ?);";
    private static final String DELETE_BY_ID = "DELETE FROM application WHERE id = ?";

    public Optional<Application> getById(Integer id) throws DaoException {
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
                    return Optional.of(mapApplicationFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing query", e);
            throw new DaoException("Error executing query for getById", e);
        }
        finally {
            ConnectionPool.releaseConnection(connection);
        }

        return Optional.empty();
    }

    public List<Application> getAll() throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        List<Application> applications = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_APPLICATIONS)) {
            while (resultSet.next()) applications.add(mapApplicationFromResultSet(resultSet));
        } catch (SQLException e) {
            logger.error("Error executing query", e);
            throw new DaoException("Error executing query for getAll", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return applications;
    }

    public void insert(Application t) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPLICATION);
            preparedStatement.setString(1, t.getDeparture());
            preparedStatement.setDate(2, Date.valueOf(t.getDepartureTime()));
            preparedStatement.setString(3, t.getDestination());
            preparedStatement.setDate(4, Date.valueOf(t.getDestinationTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing insert", e);
            throw new DaoException("Error inserting application", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    public void delete(Application t) throws DaoException {
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
            throw new DaoException("Error deleting application", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    private static Application mapApplicationFromResultSet(ResultSet resultSet) throws SQLException {
        return createApplication(
                resultSet.getInt("id"),
                resultSet.getString("departure"),
                resultSet.getString("departure_time"),
                resultSet.getString("destination"),
                resultSet.getString("destination_time")
        );
    }
}
