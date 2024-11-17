package by.bsu.lab4.dao;

import by.bsu.lab4.entity.User;
import by.bsu.lab4.exception.DaoException;
import by.bsu.lab4.logger.LoggerManager;
import by.bsu.lab4.metamodel.User_;
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

public class UserDao implements Dao<User> {
    private final Logger logger = LoggerManager.getLogger(UserDao.class.getName());

    public Optional<User> getById(Integer id) throws DaoException, InterruptedException {
        logger.info("Fetching user by ID: " + id);
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get(User_.id), id));
            Optional<User> user = em.createQuery(cq).getResultStream().findFirst();
            logger.info("Fetched user by ID: " + id);
            return user;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching user by ID: " + id, e);
            throw new DaoException("Error fetching user by ID", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<User> getAll() throws DaoException, InterruptedException {
        logger.info("Fetching all users");
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);
            List<User> users = em.createQuery(cq).getResultList();
            logger.info("Fetched " + users.size() + " users");
            return users;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all users", e);
            throw new DaoException("Error fetching all users", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public Optional<User> getByLogin(String login) throws DaoException, InterruptedException {
        logger.info("Fetching user by login: " + login);
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get(User_.login), login));
            Optional<User> user = em.createQuery(cq).getResultStream().findFirst();
            logger.info("Fetched user by login: " + login);
            return user;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching user by login: " + login, e);
            throw new DaoException("Error fetching user by login", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void insert(User user) throws DaoException, InterruptedException {
        logger.info("Inserting user: " + user);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            logger.info("Inserted user: " + user);
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error inserting user: " + user, e);
            throw new DaoException("Error inserting user", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void delete(User user) throws DaoException, InterruptedException {
        logger.info("Deleting user with ID: " + user.getId());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            User userToDelete = em.find(User.class, user.getId());
            if (userToDelete != null) {
                em.remove(userToDelete);
                em.getTransaction().commit();
                logger.info("Deleted user with ID: " + user.getId());
            } else {
                em.getTransaction().rollback();
                String message = "User not found for deletion with ID: " + user.getId();
                logger.warning(message);
                throw new DaoException(message);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error deleting user with ID: " + user.getId(), e);
            throw new DaoException("Error deleting user", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }
}
