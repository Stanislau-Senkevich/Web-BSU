package by.bsu.lab3.dao;

import by.bsu.lab3.entity.Application;
import by.bsu.lab3.logger.LoggerManager;
import by.bsu.lab3.metamodel.Application_;
import by.bsu.lab3.exception.DaoException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static by.bsu.lab3.storage.ConnectionPool.getEntityManager;
import static by.bsu.lab3.storage.ConnectionPool.releaseEntityManager;

public class ApplicationDao implements Dao<Application> {
    private final Logger logger = LoggerManager.getLogger(ApplicationDao.class.getName());

    @Override
    public Optional<Application> getById(Integer id) throws DaoException, InterruptedException {
        logger.info("Fetching application by ID: " + id);
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Application> cq = cb.createQuery(Application.class);
            Root<Application> root = cq.from(Application.class);
            cq.select(root).where(cb.equal(root.get(Application_.id), id));
            Optional<Application> application = em.createQuery(cq).getResultStream().findFirst();
            logger.info("Fetched application by ID: " + id);
            return application;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching application by ID: " + id, e);
            throw new DaoException("Error fetching application by ID", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    @Override
    public List<Application> getAll() throws DaoException, InterruptedException {
        logger.info("Fetching all applications");
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Application> cq = cb.createQuery(Application.class);
            Root<Application> root = cq.from(Application.class);
            cq.select(root);
            List<Application> applications = em.createQuery(cq).getResultList().stream()
                    .filter(application -> application.getClass() == Application.class)
                    .collect(Collectors.toList());
            logger.info("Fetched " + applications.size() + " applications");
            return applications;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all applications", e);
            throw new DaoException("Error fetching all applications", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Application> getByDeparture(String departure) throws DaoException, InterruptedException {
        logger.info("Fetching applications by departure: " + departure);
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Application> cq = cb.createQuery(Application.class);
            Root<Application> root = cq.from(Application.class);
            cq.select(root).where(cb.equal(root.get(Application_.departure), departure));
            List<Application> applications = em.createQuery(cq).getResultList();
            logger.info("Fetched " + applications.size() + " applications by departure: " + departure);
            return applications;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching applications by departure: " + departure, e);
            throw new DaoException("Error fetching applications by departure", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<Application> getByDestination(String destination) throws DaoException, InterruptedException {
        logger.info("Fetching applications by destination: " + destination);
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Application> cq = cb.createQuery(Application.class);
            Root<Application> root = cq.from(Application.class);
            cq.select(root).where(cb.equal(root.get(Application_.destination), destination));
            List<Application> applications = em.createQuery(cq).getResultList();
            logger.info("Fetched " + applications.size() + " applications by destination: " + destination);
            return applications;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching applications by destination: " + destination, e);
            throw new DaoException("Error fetching applications by destination", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    @Override
    public void insert(Application application) throws DaoException, InterruptedException {
        logger.info("Inserting application: " + application);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(application);
            em.getTransaction().commit();
            logger.info("Inserted application: " + application);
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error inserting application: " + application, e);
            throw new DaoException("Error inserting application", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    @Override
    public void delete(Application application) throws DaoException, InterruptedException {
        logger.info("Deleting application with ID: " + application.getId());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Application appToDelete = em.find(Application.class, application.getId());
            if (appToDelete != null) {
                em.remove(appToDelete);
                em.getTransaction().commit();
                logger.info("Deleted application with ID: " + application.getId());
            } else {
                em.getTransaction().rollback();
                String message = "Application not found for deletion with ID: " + application.getId();
                logger.warning(message);
                throw new DaoException(message);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error deleting application with ID: " + application.getId(), e);
            throw new DaoException("Error deleting application", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }
}
