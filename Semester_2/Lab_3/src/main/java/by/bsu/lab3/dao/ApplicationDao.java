package by.bsu.lab3.dao;

import by.bsu.lab3.entity.Application;
import by.bsu.lab3.metamodel.Application_;
import by.bsu.lab3.exception.DaoException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.bsu.lab3.storage.ConnectionPool.getEntityManager;
import static by.bsu.lab3.storage.ConnectionPool.releaseEntityManager;

public class ApplicationDao implements Dao<Application> {

    @Override
    public Optional<Application> getById(Integer id) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Application> cq = cb.createQuery(Application.class);
            Root<Application> root = cq.from(Application.class);
            cq.select(root).where(cb.equal(root.get(Application_.id), id));
            return em.createQuery(cq).getResultStream().findFirst();
        } catch (Exception e) {
            throw new DaoException("Error fetching application by ID", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    @Override
    public List<Application> getAll() throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Application> cq = cb.createQuery(Application.class);
            Root<Application> root = cq.from(Application.class);
            cq.select(root);
            return em.createQuery(cq).getResultList().stream().
                    filter(application -> application.getClass() == Application.class).
                    collect(Collectors.toList());
        } catch (Exception e) {
            throw new DaoException("Error fetching all applications", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Application> getByDeparture(String departure) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Application> cq = cb.createQuery(Application.class);
            Root<Application> root = cq.from(Application.class);
            cq.select(root).where(cb.equal(root.get(Application_.departure), departure));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new DaoException("Error fetching applications by departure", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Application> getByDestination(String destination) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Application> cq = cb.createQuery(Application.class);
            Root<Application> root = cq.from(Application.class);
            cq.select(root).where(cb.equal(root.get(Application_.destination), destination));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new DaoException("Error fetching applications by destination", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    @Override
    public void insert(Application application) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(application);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DaoException("Error inserting application", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    @Override
    public void delete(Application application) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Application appToDelete = em.find(Application.class, application.getId());
            if (appToDelete != null) {
                em.remove(appToDelete);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                throw new DaoException("Application not found for deletion");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DaoException("Error deleting application", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }
}
