package by.bsu.lab3.storage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static BlockingQueue<EntityManager> entityManagerPool;
    private static final int POOL_SIZE = 20;
    private static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerPool = new ArrayBlockingQueue<>(POOL_SIZE);
        initializePool();
    }

    private static void initializePool() {
        entityManagerFactory = Persistence.createEntityManagerFactory("web_bsu");
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                entityManagerPool.add(entityManager);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка при создании экземпляров EntityManager.", e);
            }
        }
    }

    public static EntityManager getEntityManager() throws InterruptedException {
        return entityManagerPool.take();
    }

    public static boolean releaseEntityManager(EntityManager entityManager) {
        if (entityManager != null) {
            if (entityManager.isOpen()) entityManager.close();
            return entityManagerPool.offer(entityManager);
        }
        return false;
    }

    public static void closeFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
