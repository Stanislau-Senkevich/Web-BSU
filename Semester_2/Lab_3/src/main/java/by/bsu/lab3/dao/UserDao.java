package by.bsu.lab3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import by.bsu.lab3.entity.User;
import by.bsu.lab3.metamodel.User_;
import by.bsu.lab3.exception.DaoException;

import java.util.List;
import java.util.Optional;

import static by.bsu.lab3.storage.ConnectionPool.getEntityManager;
import static by.bsu.lab3.storage.ConnectionPool.releaseEntityManager;

public class UserDao implements Dao<User> {

    public Optional<User> getById(Integer id) throws DaoException, InterruptedException {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get(User_.id), id));
            return em.createQuery(cq).getResultStream().findFirst();
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
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);
            return em.createQuery(cq).getResultList();
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
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get(User_.login), login));
            return em.createQuery(cq).getResultStream().findFirst();
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
