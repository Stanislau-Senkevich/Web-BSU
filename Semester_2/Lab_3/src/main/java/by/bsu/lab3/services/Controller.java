package by.bsu.lab3.services;

import by.bsu.lab3.dao.ApplicationDao;
import by.bsu.lab3.dao.CarDao;
import by.bsu.lab3.dao.TripDao;
import by.bsu.lab3.dao.UserDao;
import by.bsu.lab3.entity.Application;
import by.bsu.lab3.entity.Car;
import by.bsu.lab3.entity.Trip;
import by.bsu.lab3.entity.User;
import by.bsu.lab3.exception.DaoException;
import java.util.List;

import static by.bsu.lab3.factory.TripFactory.createTrip;

public class Controller {
    private ApplicationDao applicationDao;
    private TripDao tripDao;
    private UserDao userDao;
    private CarDao carDao;

    public Controller() {
        this.applicationDao = new ApplicationDao();
        this.tripDao = new TripDao();
        this.userDao = new UserDao();
        this.carDao = new CarDao();
    }
    public List<Application> getSortedApplications(String field) throws DaoException, InterruptedException {
        List<Application> applications = applicationDao.getAll();
        applications.sort(Application.getComparator(field));
        return applications;
    }
    public List<Trip> getTrips(Integer driver_id) throws DaoException, InterruptedException  {
        return tripDao.getByDriverId(driver_id);
    }

    public List<Car> getFixingCar() throws DaoException, InterruptedException {
        return carDao.getFixingCar();
    }

    public void putDriverOnTrip(Integer driver_id, Integer application_id, Integer car_id) throws DaoException, IllegalArgumentException, InterruptedException {
        Application application;
        User driver;
        Car car;

            if (applicationDao.getById(application_id).isPresent()) {
                application = applicationDao.getById(application_id).get();
            } else {
                throw new IllegalArgumentException("invalid application_id");
            }
            if (userDao.getById(driver_id).isPresent()) {
                driver = userDao.getById(driver_id).get();
            } else {
                throw new IllegalArgumentException("invalid driver_id");
            }
            if (carDao.getById(car_id).isPresent()) {
                car = carDao.getById(car_id).get();
            } else {
                throw new IllegalArgumentException("invalid car_id");
            }

        Trip trip = createTrip(
                0,
                application.getDeparture(),
                application.getDepartureTime(),
                application.getDestination(),
                application.getDestinationTime(),
                driver,
                car,
                false
        );

        tripDao.insert(trip);
    }

    public void putCarOnFix(Integer car_id) throws DaoException, InterruptedException {
        carDao.putCarOnFix(car_id);
    }
}
