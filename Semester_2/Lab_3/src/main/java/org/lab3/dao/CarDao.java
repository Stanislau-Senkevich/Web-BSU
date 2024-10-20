package org.lab3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.lab3.entity.Car;
import org.lab3.exception.DaoException;

import java.util.List;
import java.util.Optional;

import static org.lab3.storage.ConnectionPool.getEntityManager;
import static org.lab3.storage.ConnectionPool.releaseEntityManager;

public class CarDao implements Dao<Car> {
    public Optional<Car> getById(Integer id) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Car> query = em.createNamedQuery("Car.findById", Car.class);
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst();
        } catch (Exception e) {
            throw new DaoException("Error fetching car by ID", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Car> getAll() throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Car> query = em.createNamedQuery("Car.findAll", Car.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException("Error fetching all cars", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Car> getFixingCar() throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Car> query = em.createNamedQuery("Car.findFixingCars", Car.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException("Error fetching cars under repair", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void insert(Car car) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DaoException("Error inserting car", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void delete(Car car) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Car carToDelete = em.find(Car.class, car.getId());
            if (carToDelete != null) {
                em.remove(carToDelete);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                throw new DaoException("Car not found for deletion");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DaoException("Error deleting car", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void putCarOnFix(Integer carId) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Car.putOnFix");
            query.setParameter("carId", carId);
            int updated = query.executeUpdate();
            if (updated > 0) {
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                throw new DaoException("No car found for the given ID");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DaoException("Error updating car fix state", e);
        } finally {
            releaseEntityManager(em);
        }
    }

}
