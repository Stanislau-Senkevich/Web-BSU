package by.bsu.lab3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import by.bsu.lab3.entity.Car;
import by.bsu.lab3.metamodel.Car_;
import by.bsu.lab3.exception.DaoException;

import java.util.List;
import java.util.Optional;

import static by.bsu.lab3.storage.ConnectionPool.getEntityManager;
import static by.bsu.lab3.storage.ConnectionPool.releaseEntityManager;

public class CarDao implements Dao<Car> {

    public Optional<Car> getById(Integer id) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Car> cq = cb.createQuery(Car.class);
            Root<Car> root = cq.from(Car.class);
            cq.select(root).where(cb.equal(root.get(Car_.id), id));
            return em.createQuery(cq).getResultStream().findFirst();
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
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Car> cq = cb.createQuery(Car.class);
            Root<Car> root = cq.from(Car.class);
            cq.select(root);
            return em.createQuery(cq).getResultList();
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
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Car> cq = cb.createQuery(Car.class);
            Root<Car> root = cq.from(Car.class);
            cq.select(root).where(cb.like(root.get(Car_.fixState), "%Repair%"));
            return em.createQuery(cq).getResultList();
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
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Car> cq = cb.createQuery(Car.class);
            Root<Car> root = cq.from(Car.class);
            cq.select(root).where(cb.equal(root.get(Car_.id), carId));

            Car carToFix = em.createQuery(cq).getSingleResult();
            if (carToFix != null) {
                carToFix.setFixState("Under Repair");
                em.merge(carToFix);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                throw new DaoException("No car found for the given ID");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DaoException("Error updating car fix state", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }
}
