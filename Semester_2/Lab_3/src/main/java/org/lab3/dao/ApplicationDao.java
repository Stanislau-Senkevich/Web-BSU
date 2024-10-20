package org.lab3.dao;

import org.lab3.entity.Application;
import org.lab3.exception.DaoException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.lab3.storage.ConnectionPool.getEntityManager;
import static org.lab3.storage.ConnectionPool.releaseEntityManager;

public class ApplicationDao implements Dao<Application> {

    @Override
    public Optional<Application> getById(Integer id) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Application> query = em.createNamedQuery("Application.findById", Application.class);
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst();
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
            TypedQuery<Application> query = em.createNamedQuery("Application.findAll", Application.class);
            List<Application> applications = query.getResultList().stream()
                    .filter(application -> application.getClass() == Application.class)
                    .collect(Collectors.toList());
            return applications;
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
            TypedQuery<Application> query = em.createNamedQuery("Application.findByDeparture", Application.class);
            query.setParameter("departure", departure);
            return query.getResultList();
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
            TypedQuery<Application> query = em.createNamedQuery("Application.findByDestination", Application.class);
            query.setParameter("destination", destination);
            return query.getResultList();
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
