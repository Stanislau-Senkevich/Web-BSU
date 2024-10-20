package dao;

import entity.User;
import exception.DaoException;
import logger.LoggerManager;
import org.apache.logging.log4j.Logger;
import storage.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static factory.UserFactory.createUser;

public class UserDao implements Dao<User> {
    private static final Logger logger = LoggerManager.getLogger(UserDao.class.getName());
    private static final String SELECT_BY_ID = "SELECT * FROM park_user WHERE id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM park_user;";
    private static final String INSERT_USER = "INSERT INTO park_user(login, password) VALUES(?, ?);";
    private static final String DELETE_BY_ID = "DELETE FROM park_user WHERE id = ?";

    public Optional<User> getById(Integer id) throws DaoException {
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
                    return Optional.of(mapUserFromResultSet(resultSet));
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

    public List<User> getAll() throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS)) {
            while (resultSet.next()) {
                users.add(mapUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error executing query for getAll", e);
            throw new DaoException("Error executing query for getAll", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return users;
    }

    public void insert(User user) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing insert", e);
            throw new DaoException("Error inserting user", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    public void delete(User user) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            logger.warn("Failed to get connection", e);
            throw new DaoException("Error getting connection", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing delete", e);
            throw new DaoException("Error deleting user", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    private static User mapUserFromResultSet(ResultSet resultSet) throws SQLException {
        return createUser(
                resultSet.getInt("id"),
                resultSet.getString("login"),
                resultSet.getString("password")
        );
    }
}
