package by.bsu.lab4.dao;

import by.bsu.lab4.entity.Car;
import by.bsu.lab4.exception.DaoException;
import by.bsu.lab4.logger.LoggerManager;
import by.bsu.lab4.metamodel.Car_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.bsu.lab4.storage.ConnectionPool.getEntityManager;
import static by.bsu.lab4.storage.ConnectionPool.releaseEntityManager;

public class CarDao implements Dao<Car> {
    private final Logger logger = LoggerManager.getLogger(CarDao.class.getName());

    public Optional<Car> getById(Integer id) throws DaoException, InterruptedException {
        logger.info("Fetching car by ID: " + id);
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Car> cq = cb.createQuery(Car.class);
            Root<Car> root = cq.from(Car.class);
            cq.select(root).where(cb.equal(root.get(Car_.id), id));
            Optional<Car> car = em.createQuery(cq).getResultStream().findFirst();
            logger.info("Fetched car by ID: " + id);
            return car;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching car by ID: " + id, e);
            throw new DaoException("Error fetching car by ID", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Car> getAll() throws DaoException, InterruptedException {
        logger.info("Fetching all cars");
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Car> cq = cb.createQuery(Car.class);
            Root<Car> root = cq.from(Car.class);
            cq.select(root);
            List<Car> cars = em.createQuery(cq).getResultList();
            logger.info("Fetched " + cars.size() + " cars");
            return cars;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all cars", e);
            throw new DaoException("Error fetching all cars", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Car> getFixingCar() throws DaoException, InterruptedException {
        logger.info("Fetching cars under repair");
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Car> cq = cb.createQuery(Car.class);
            Root<Car> root = cq.from(Car.class);
            cq.select(root).where(cb.like(root.get(Car_.fixState), "%Repair%"));
            List<Car> cars = em.createQuery(cq).getResultList();
            logger.info("Fetched " + cars.size() + " cars under repair");
            return cars;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching cars under repair", e);
            throw new DaoException("Error fetching cars under repair", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void insert(Car car) throws DaoException, InterruptedException {
        logger.info("Inserting car: " + car);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
            logger.info("Inserted car: " + car);
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error inserting car: " + car, e);
            throw new DaoException("Error inserting car", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void delete(Car car) throws DaoException, InterruptedException {
        logger.info("Deleting car with ID: " + car.getId());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Car carToDelete = em.find(Car.class, car.getId());
            if (carToDelete != null) {
                em.remove(carToDelete);
                em.getTransaction().commit();
                logger.info("Deleted car with ID: " + car.getId());
            } else {
                em.getTransaction().rollback();
                String message = "Car not found for deletion with ID: " + car.getId();
                logger.warning(message);
                throw new DaoException(message);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error deleting car with ID: " + car.getId(), e);
            throw new DaoException("Error deleting car", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void putCarOnFix(Integer carId) throws DaoException, InterruptedException {
        logger.info("Updating car fix state to 'Under Repair' for car ID: " + carId);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Car> cq = cb.createQuery(Car.class);
            Root<Car> root = cq.from(Car.class);
            cq.select(root).where(cb.equal(root.get(Car_.id), carId));

            Car carToFix = em.createQuery(cq).getSingleResult();
            if (carToFix != null) {
                carToFix.setFixState("Under Repair");
                em.merge(carToFix);
                em.getTransaction().commit();
                logger.info("Updated car fix state to 'Under Repair' for car ID: " + carId);
            } else {
                em.getTransaction().rollback();
                String message = "No car found for the given ID: " + carId;
                logger.warning(message);
                throw new DaoException(message);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error updating car fix state for car ID: " + carId, e);
            throw new DaoException("Error updating car fix state", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }
}
