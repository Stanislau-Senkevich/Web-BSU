import dao.*;
import entity.Application;
import entity.Car;
import entity.Trip;
import exception.DaoException;

import java.util.List;

import static factory.TripFactory.createTrip;

public class Controller {
    private ApplicationDao applicationDao;
    private DriverDao driverDao;
    private TripDao tripDao;
    private UserDao userDao;
    private DispatcherDao dispatcherDao;
    private CarDao carDao;

    public Controller() {
        this.applicationDao = new ApplicationDao();
        this.driverDao = new DriverDao();
        this.tripDao = new TripDao();
        this.userDao = new UserDao();
        this.dispatcherDao = new DispatcherDao();
        this.carDao = new CarDao();
    }
    public List<Application> getSortedApplications(String field) throws DaoException {
        List<Application> applications = applicationDao.getAll();
        applications.sort(Application.getComparator(field));
        return applications;
    }
    public List<Trip> getTrips(Integer driver_id) throws DaoException  {
        return tripDao.getByDriverId(driver_id);
    }

    public List<Car> getFixingCar() throws DaoException {
        return carDao.getFixingCar();
    }

    public void putDriverOnTrip(Integer driver_id, Integer application_id, Integer car_id) throws DaoException, IllegalArgumentException {
        Application application;

            if (applicationDao.getById(application_id).isPresent()) {
                application = applicationDao.getById(application_id).get();
            } else {
                throw new IllegalArgumentException("invalid application_id");
            }
            if (driverDao.getById(driver_id).isPresent()) {
                driverDao.getById(driver_id).get();
            } else {
                throw new IllegalArgumentException("invalid driver_id");
            }
            if (carDao.getById(car_id).isPresent()) {
                carDao.getById(car_id).get();
            } else {
                throw new IllegalArgumentException("invalid car_id");
            }

        Trip trip = createTrip(
                -1,
                application.getDeparture(),
                application.getDepartureTime(),
                application.getDestination(),
                application.getDestinationTime(),
                driver_id,
                car_id,
                false
        );

        tripDao.insert(trip);
    }

    public void putCarOnFix(Integer car_id) throws DaoException {
        carDao.putOnFix(car_id);
    }
}
