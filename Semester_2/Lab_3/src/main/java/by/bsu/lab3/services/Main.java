package by.bsu.lab3.services;

import by.bsu.lab3.logger.LoggerManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = LoggerManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        View view;
        try {
            view = new View();
            logger.info("View initialized successfully.");
        } catch (ClassNotFoundException ce) {
            System.out.println("Driver not found");
            logger.log(Level.SEVERE, "Database driver not found.", ce);
            return;
        } catch (Exception ce) {
            System.out.println("Failed to get connection: " + ce.getMessage());
            logger.log(Level.SEVERE, "Failed to establish database connection.", ce);
            return;
        }

        view.start();
        logger.info("Application started successfully.");
    }
}
