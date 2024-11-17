package by.bsu.lab3.logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerManager {
    private static final HashMap<String, Logger> loggers = new HashMap<>();

    static {
        try {
            setupLogger("by.bsu.lab3.dao.TripDao", "logs/TripDao.log");
            setupLogger("by.bsu.lab3.dao.ApplicationDao", "logs/ApplicationDao.log");
            setupLogger("by.bsu.lab3.dao.CarDao", "logs/CarDao.log");
            setupLogger("by.bsu.lab3.dao.UserDao", "logs/UserDao.log");
            setupLogger("by.bsu.lab3.storage.ConnectionPool", "logs/ConnectionPool.log");
            setupLogger("by.bsu.lab3.services.View", "logs/View.log");
            setupLogger("by.bsu.lab3.services.Main", "logs/Main.log");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize loggers", e);
        }
    }

    private static void setupLogger(String className, String filePath) throws IOException {
        Logger logger = Logger.getLogger(className);

        logger.setUseParentHandlers(false);

        FileHandler fileHandler = new FileHandler(filePath, true);
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setLevel(Level.ALL);

        logger.addHandler(fileHandler);
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
