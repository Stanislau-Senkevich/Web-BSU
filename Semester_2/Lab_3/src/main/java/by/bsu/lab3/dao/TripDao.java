package by.bsu.lab3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import by.bsu.lab3.entity.Trip;
import by.bsu.lab3.metamodel.Trip_;
import by.bsu.lab3.entity.User;
import by.bsu.lab3.exception.DaoException;

import java.util.List;
import java.util.Optional;

import static by.bsu.lab3.storage.ConnectionPool.getEntityManager;
import static by.bsu.lab3.storage.ConnectionPool.releaseEntityManager;

public class TripDao implements Dao<Trip> {

    public Optional<Trip> getById(Integer id) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
            Root<Trip> root = cq.from(Trip.class);
            cq.select(root).where(cb.equal(root.get(Trip_.id), id));
            return em.createQuery(cq).getResultStream().findFirst();
        } catch (Exception e) {
            throw new DaoException("Error fetching trip by ID", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Trip> getAll() throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
            Root<Trip> root = cq.from(Trip.class);
            cq.select(root);
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new DaoException("Error fetching all trips", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Trip> getByDriverId(Integer driverId) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
            Root<Trip> root = cq.from(Trip.class);
            Join<Trip, User> driverJoin = root.join(Trip_.driver);
            cq.select(root).where(cb.equal(driverJoin.get("id"), driverId));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new DaoException("Error fetching trips by driver ID", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void insert(Trip trip) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(trip);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DaoException("Error inserting trip", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void delete(Trip trip) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Trip tripToDelete = em.find(Trip.class, trip.getId());
            if (tripToDelete != null) {
                em.remove(tripToDelete);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                throw new DaoException("Trip not found for deletion");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DaoException("Error deleting trip", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }
}
