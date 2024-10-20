package org.lab3.services;

public class Main {
    //private static final Logger logger = LoggerManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        View view;
        try {
            view = new View();
        } catch (ClassNotFoundException ce) {
            System.out.println("Driver not found");
            //logger.fatal(ce);
            return;
        } catch (Exception ce) {
            System.out.println("Failed to get connection");
            //logger.fatal(ce);
            return;
        }
        view.start();
    }
}