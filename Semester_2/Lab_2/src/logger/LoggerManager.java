package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class LoggerManager {
    private static final HashMap<String, Logger> loggers = new HashMap<>();

    static {
        loggers.put("dao.TripDao", LogManager.getLogger("dao.TripDao"));
        loggers.put("dao.ApplicationDao", LogManager.getLogger("dao.ApplicationDao"));
        loggers.put("dao.CarDao", LogManager.getLogger("dao.CarDao"));
        loggers.put("dao.DispatcherDao", LogManager.getLogger("dao.DispatcherDao"));
        loggers.put("dao.DriverDao", LogManager.getLogger("dao.DriverDao"));
        loggers.put("dao.UserDao", LogManager.getLogger("dao.UserDao"));
        loggers.put("storage.JdbcConnector", LogManager.getLogger("storage.JdbcConnector"));
        loggers.put("storage.ConnectionPool", LogManager.getLogger("storage.ConnectionPool"));
        loggers.put("View", LogManager.getLogger("View"));
        loggers.put("Main", LogManager.getLogger("Main"));
    }

    public static Logger getLogger(String className) {
        if (loggers.containsKey(className)) {
            return loggers.get(className);
        }
        throw new IllegalArgumentException("No logger for provided class: " + className);
    }
}
