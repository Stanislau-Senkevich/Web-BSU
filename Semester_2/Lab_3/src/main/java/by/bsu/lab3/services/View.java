package by.bsu.lab3.services;

import by.bsu.lab3.entity.Application;
import by.bsu.lab3.entity.Car;
import by.bsu.lab3.entity.Trip;
import by.bsu.lab3.exception.DaoException;
import by.bsu.lab3.logger.LoggerManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class View {
    private static final Logger logger = LoggerManager.getLogger(View.class.getName());
    private final Controller controller;
    private static final String COMMANDS_TEXT = "\nChoose action:" +
            "\n1 - Get sorted applications" +
            "\n2 - Get driver's trips" +
            "\n3 - Get fixing car" +
            "\n4 - Put driver on a trip" +
            "\n5 - Put car on a fix";

    public View() throws ClassNotFoundException, SQLException {
        controller = new Controller();
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println(COMMANDS_TEXT);
            String input = in.nextLine().trim();
            int command;
            try {
                command = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                logger.warning("Invalid command input: " + input);
                continue;
            }

            if (command < 0 || command > 5) {
                System.out.println("Invalid command\n");
                logger.warning("Invalid command selection: " + command);
                continue;
            }

            switch (command) {
                case 0 -> {
                    logger.info("Exiting the application.");
                    return;
                }
                case 1 -> getSortedApplications(in);
                case 2 -> getTrips(in);
                case 3 -> getFixingCars();
                case 4 -> putDriverOnTrip(in);
                case 5 -> putCarOnFix(in);
            }
        }
    }

    public void getSortedApplications(Scanner in) {
        System.out.print("\nChoose field to sort:\n1 - id\n2 - departure\n3 - destination\n4 - departure_time\n5 - destination_time\n");
        int command;
        String input = in.nextLine().trim();
        String field;
        try {
            command = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            logger.warning("Invalid field selection input: " + input);
            return;
        }
        switch (command) {
            case 1 -> field = "id";
            case 2 -> field = "departure";
            case 3 -> field = "destination";
            case 4 -> field = "departure_time";
            case 5 -> field = "destination_time";
            default -> {
                System.out.println("Invalid field");
                logger.warning("Invalid field command: " + command);
                return;
            }
        }

        List<Application> applications;
        try {
            applications = controller.getSortedApplications(field);
        } catch (DaoException | InterruptedException e) {
            System.out.println("Error retrieving sorted applications by field: " + field);
            logger.log(Level.SEVERE, "Error retrieving sorted applications by field: " + field, e);
            return;
        }

        for (Application application : applications) {
            System.out.println(application);
        }
        logger.info("Sorted applications retrieved by field: " + field);
    }

    public void getTrips(Scanner in) {
        System.out.println("Enter driver_id: ");
        int id;
        String input = in.nextLine().trim();
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            logger.warning("Invalid driver ID input: " + input);
            return;
        }
        try {
            List<Trip> trips = controller.getTrips(id);
            for (Trip t : trips) {
                System.out.println(t);
            }
            logger.info("Fetched trips for driver ID: " + id);
        } catch (DaoException | InterruptedException e) {
            System.out.println("Error retrieving trips for driver ID: " + id);
            logger.log(Level.SEVERE, "Error retrieving trips for driver ID: " + id, e);
        }
    }

    public void getFixingCars() {
        try {
            List<Car> cars = controller.getFixingCar();
            for (Car c : cars) {
                System.out.println(c);
            }
            logger.info("Fetched fixing cars.");
        } catch (DaoException | InterruptedException e) {
            System.out.println("Error retrieving fixing cars");
            logger.log(Level.SEVERE, "Error retrieving fixing cars", e);
        }
    }

    public void putDriverOnTrip(Scanner in) {
        String input;
        int driver_id, car_id, application_id;
        System.out.println("Enter driver id: ");
        input = in.nextLine().trim();
        try {
            driver_id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            logger.warning("Invalid driver ID input: " + input);
            return;
        }
        System.out.println("Enter car id: ");
        input = in.nextLine().trim();
        try {
            car_id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            logger.warning("Invalid car ID input: " + input);
            return;
        }
        System.out.println("Enter application id: ");
        input = in.nextLine().trim();
        try {
            application_id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            logger.warning("Invalid application ID input: " + input);
            return;
        }

        try {
            controller.putDriverOnTrip(driver_id, application_id, car_id);
            System.out.printf("Trip from application (id = %d) was created with driver (id = %d) and car (id = %d)%n", application_id, driver_id, car_id);
            logger.info(String.format("Trip created with driver ID: %d, application ID: %d, car ID: %d", driver_id, application_id, car_id));
        } catch (DaoException | InterruptedException e) {
            System.out.println("Error assigning driver to trip with driver ID: " + driver_id + ", application ID: " + application_id + ", car ID: " + car_id);
            logger.log(Level.SEVERE, "Error assigning driver to trip with driver ID: " + driver_id + ", application ID: " + application_id + ", car ID: " + car_id, e);
        }
    }

    public void putCarOnFix(Scanner in) {
        System.out.println("Enter car id: ");
        int car_id;
        String input = in.nextLine().trim();
        try {
            car_id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            logger.warning("Invalid car ID input: " + input);
            return;
        }

        try {
            controller.putCarOnFix(car_id);
            System.out.printf("Car with id = %d was successfully put on fix.%n", car_id);
            logger.info("Car with ID " + car_id + " was successfully put on fix.");
        } catch (DaoException | InterruptedException e) {
            System.out.println("Error assigning car to fix with car ID: " + car_id);
            logger.log(Level.SEVERE, "Error assigning car to fix with car ID: " + car_id, e);
        }
    }
}
