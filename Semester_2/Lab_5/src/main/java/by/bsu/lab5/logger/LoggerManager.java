package by.bsu.lab5.logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerManager {
    private static final HashMap<String, Logger> loggers = new HashMap<>();

    static {
        try {
            setupLogger("by.bsu.lab5.dao.TripDao", "logs/TripDao.log");
            setupLogger("by.bsu.lab5.dao.ApplicationDao", "logs/ApplicationDao.log");
            setupLogger("by.bsu.lab5.dao.CarDao", "logs/CarDao.log");
            setupLogger("by.bsu.lab5.dao.UserDao", "logs/UserDao.log");
            setupLogger("by.bsu.lab5.storage.ConnectionPool", "logs/ConnectionPool.log");
            setupLogger("by.bsu.lab5.services.View", "logs/View.log");
            setupLogger("by.bsu.lab5.services.Main", "logs/Main.log");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize loggers", e);
        }
    }

    private static void setupLogger(String className, String filePath) throws IOException {
        Logger logger = Logger.getLogger(className);
        logger.setLevel(Level.ALL);
        loggers.put(className, logger);
    }

    public static Logger getLogger(String className) {
        Logger logger = loggers.get(className);
        if (logger != null) {
            return logger;
        }
        throw new IllegalArgumentException("No logger for provided class: " + className);
    }
}
