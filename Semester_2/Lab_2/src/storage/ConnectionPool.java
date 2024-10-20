package storage;

import logger.LoggerManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final int POOL_SIZE = 10;
    private static BlockingQueue<Connection> connectionQueue;
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;
    private static final String PROPERTIES_FILE = "storage/database.properties";

    static {
        connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);
    }

    private static Logger logger = LoggerManager.getLogger(ConnectionPool.class.getName());

    public static void initializePool() {
        Properties properties = new Properties();
        try (InputStream input = ConnectionPool.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (Exception e) {
            System.out.println("Failed to open db properties");
            logger.fatal(e);
            return;
        }

        dbUrl = properties.getProperty("db.url");
        dbUser = properties.getProperty("db.username");
        dbPassword = properties.getProperty("db.password");

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                connectionQueue.offer(connection);
            } catch (SQLException e) {
                logger.error(e);
                throw new RuntimeException("Error creating database connections.");
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            return connectionQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error(e);
            throw new SQLException("Interrupted while waiting for a connection.");
        }
    }

    public static void releaseConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                connectionQueue.offer(connection);
            }
        } catch (SQLException e) {
            return;
        }
    }
}