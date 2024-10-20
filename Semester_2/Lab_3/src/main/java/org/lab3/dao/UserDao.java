package org.lab3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.lab3.entity.User;
import org.lab3.exception.DaoException;

import java.util.List;
import java.util.Optional;

import static org.lab3.storage.ConnectionPool.getEntityManager;
import static org.lab3.storage.ConnectionPool.releaseEntityManager;

public class UserDao implements Dao<User> {

    public Optional<User> getById(Integer id) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<User> query = em.createNamedQuery("User.findById", User.class);
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst();
        } catch (Exception e) {
            throw new DaoException("Error fetching user by ID", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public List<User> getAll() throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException("Error fetching all users", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public Optional<User> getByLogin(String login) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<User> query = em.createNamedQuery("User.findByLogin", User.class);
            query.setParameter("login", login);
            return query.getResultList().stream().findFirst();
        } catch (Exception e) {
            throw new DaoException("Error fetching user by login", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void insert(User user) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DaoException("Error inserting user", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }

    public void delete(User user) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            User userToDelete = em.find(User.class, user.getId());
            if (userToDelete != null) {
                em.remove(userToDelete);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                throw new DaoException("User not found for deletion");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DaoException("Error deleting user", e);
        } finally {
            em.close();
            releaseEntityManager(em);
        }
    }
}
