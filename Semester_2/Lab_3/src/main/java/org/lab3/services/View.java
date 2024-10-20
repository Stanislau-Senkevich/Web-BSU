package org.lab3.services;

import org.lab3.entity.Application;
import org.lab3.entity.Car;
import org.lab3.entity.Trip;
import org.lab3.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class View {
    //private static final Logger logger = LogManager.getLogger(View.class);
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
                //logger.error("Invalid command input: " + input, e);
                continue;
            }

            if (command < 0 || command > 5) {
                System.out.println("Invalid command\n");
                //logger.warn("Invalid command selection: " + command);
                continue;
            }

            switch (command) {
                case 0 -> {
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
            //logger.warn("Invalid field selection input: " + input, e);
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
                //logger.warn("Invalid field command: " + command);
                return;
            }
        }

        List<Application> applications;
        try {
            applications = controller.getSortedApplications(field);
        } catch (DaoException | InterruptedException e) {
            System.out.println("Error retrieving sorted applications by field: " + field);
            //logger.error("Error retrieving sorted applications by field: " + field, e);
            return;
        }

        for (Application application : applications) {
            System.out.println(application);
        }
    }

    public void getTrips(Scanner in) {
        System.out.println("Enter driver_id: ");
        int id;
        String input = in.nextLine().trim();
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            //logger.warn("Invalid driver ID input: " + input, e);
            return;
        }
        try {
            List<Trip> trips = controller.getTrips(id);
            for (Trip t : trips) {
                System.out.println(t);
            }
        } catch (DaoException | InterruptedException e) {
            System.out.println("Error retrieving trips for driver ID: " + id);
            //logger.error("Error retrieving trips for driver ID: " + id, e);
        }
    }

    public void getFixingCars() {
        try {
            List<Car> cars = controller.getFixingCar();
            for (Car c : cars) {
                System.out.println(c);
            }
        } catch (DaoException | InterruptedException e) {
            System.out.println("Error retrieving fixing cars");
            //logger.error("Error retrieving fixing cars", e);
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
            //logger.warn("Invalid driver ID input: " + input, e);
            return;
        }
        System.out.println("Enter car id: ");
        input = in.nextLine().trim();
        try {
            car_id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            //logger.warn("Invalid car ID input: " + input, e);
            return;
        }
        System.out.println("Enter application id: ");
        input = in.nextLine().trim();
        try {
            application_id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            //logger.warn("Invalid application ID input: " + input, e);
            return;
        }

        try {
            controller.putDriverOnTrip(driver_id, application_id, car_id);
        } catch (DaoException | InterruptedException e) {
            System.out.println("Error assigning driver to trip with driver ID: " + driver_id + ", application ID: " + application_id + ", car ID: " + car_id);
            //logger.error("Error assigning driver to trip with driver ID: " + driver_id + ", application ID: " + application_id + ", car ID: " + car_id, e);
        }

        System.out.printf("Trip from application (id = %d) was created with driver (id = %d) and car (id = %d)", application_id, driver_id, car_id);
        //logger.info(String.format("Trip created with driver ID: %d, application ID: %d, car ID: %d", driver_id, application_id, car_id));
    }

    public void putCarOnFix(Scanner in) {
        System.out.println("Enter car id: ");
        int car_id;
        String input = in.nextLine().trim();
        try {
            car_id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            //logger.warn("Invalid car ID input: " + input, e);
            return;
        }

        try {
            controller.putCarOnFix(car_id);
        } catch (DaoException | InterruptedException e) {
            System.out.println("Error assigning car to fix with car ID: ");
            //logger.warn("Error assigning car to fix with car ID: " + car_id, e);
        }
        System.out.printf("Car with id = %d was successfully put on fix.", car_id);
        //logger.info("Car with ID " + car_id + " was successfully put on fix.");
    }
}
