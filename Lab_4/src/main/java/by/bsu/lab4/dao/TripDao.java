package by.bsu.lab3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import by.bsu.lab3.entity.Trip;
import by.bsu.lab3.logger.LoggerManager;
import by.bsu.lab3.metamodel.Trip_;
import by.bsu.lab3.entity.User;
import by.bsu.lab3.exception.DaoException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.bsu.lab3.storage.ConnectionPool.getEntityManager;
import static by.bsu.lab3.storage.ConnectionPool.releaseEntityManager;

public class TripDao implements Dao<Trip> {
    private final Logger logger = LoggerManager.getLogger(TripDao.class.getName());

    public Optional<Trip> getById(Integer id) throws DaoException, InterruptedException {
        logger.info("Fetching trip by ID: " + id);
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
            Root<Trip> root = cq.from(Trip.class);
            cq.select(root).where(cb.equal(root.get(Trip_.id), id));
            Optional<Trip> trip = em.createQuery(cq).getResultStream().findFirst();
            logger.info("Fetched trip by ID: " + id);
            return trip;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching trip by ID: " + id, e);
            throw new DaoException("Error fetching trip by ID", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Trip> getAll() throws DaoException, InterruptedException {
        logger.info("Fetching all trips");
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
            Root<Trip> root = cq.from(Trip.class);
            cq.select(root);
            List<Trip> trips = em.createQuery(cq).getResultList();
            logger.info("Fetched " + trips.size() + " trips");
            return trips;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all trips", e);
            throw new DaoException("Error fetching all trips", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Trip> getByDriverId(Integer driverId) throws DaoException, InterruptedException {
        logger.info("Fetching trips by driver ID: " + driverId);
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
            Root<Trip> root = cq.from(Trip.class);
            Join<Trip, User> driverJoin = root.join(Trip_.driver);
            cq.select(root).where(cb.equal(driverJoin.get("id"), driverId));
            List<Trip> trips = em.createQuery(cq).getResultList();
            logger.info("Fetched " + trips.size() + " trips for driver ID: " + driverId);
            return trips;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching trips by driver ID: " + driverId, e);
            throw new DaoException("Error fetching trips by driver ID", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void insert(Trip trip) throws DaoException, InterruptedException {
        logger.info("Inserting trip: " + trip);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(trip);
            em.getTransaction().commit();
            logger.info("Inserted trip: " + trip);
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error inserting trip: " + trip, e);
            throw new DaoException("Error inserting trip", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void delete(Trip trip) throws DaoException, InterruptedException {
        logger.info("Deleting trip with ID: " + trip.getId());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Trip tripToDelete = em.find(Trip.class, trip.getId());
            if (tripToDelete != null) {
                em.remove(tripToDelete);
                em.getTransaction().commit();
                logger.info("Deleted trip with ID: " + trip.getId());
            } else {
                em.getTransaction().rollback();
                String message = "Trip not found for deletion with ID: " + trip.getId();
                logger.warning(message);
                throw new DaoException(message);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error deleting trip with ID: " + trip.getId(), e);
            throw new DaoException("Error deleting trip", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }
}
