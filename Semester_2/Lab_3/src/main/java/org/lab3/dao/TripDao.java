package org.lab3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.lab3.entity.Trip;
import org.lab3.exception.DaoException;

import java.util.List;
import java.util.Optional;

import static org.lab3.storage.ConnectionPool.getEntityManager;
import static org.lab3.storage.ConnectionPool.releaseEntityManager;

public class TripDao implements Dao<Trip> {

    public Optional<Trip> getById(Integer id) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Trip> query = em.createNamedQuery("Trip.findById", Trip.class);
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst();
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
            TypedQuery<Trip> query = em.createNamedQuery("Trip.findAll", Trip.class);
            return query.getResultList();
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
            TypedQuery<Trip> query = em.createNamedQuery("Trip.findByDriverId", Trip.class);
            query.setParameter("driverId", driverId);
            return query.getResultList();
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
