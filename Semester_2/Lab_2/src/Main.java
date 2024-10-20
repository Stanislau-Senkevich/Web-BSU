import logger.LoggerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import storage.ConnectionPool;

import java.sql.Connection;

public class Main {
    private static final Logger logger = LoggerManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        try {
            ConnectionPool.initializePool();
        } catch (Exception e) {
            logger.fatal(e);
            System.out.println("Failed to open connection to db");
            return;
        }

        View view;
        try {
            view = new View();
        } catch (ClassNotFoundException ce) {
            System.out.println("Driver not found");
            logger.fatal(ce);
            return;
        } catch (Exception ce) {
            System.out.println("Failed to get connection");
            logger.fatal(ce);
            return;
        }
        view.start();
    }
}