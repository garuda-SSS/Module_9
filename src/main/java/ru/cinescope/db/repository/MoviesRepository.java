package ru.cinescope.db.repository;


import jakarta.persistence.*;
import ru.cinescope.db.domain.Movies;

public class MoviesRepository {
    private final EntityManagerFactory emf;

    public MoviesRepository() {
        this.emf = Persistence.createEntityManagerFactory("cinescopePersistenceUnit");
    }

    public Movies findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Movies.class, id);
        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public Movies findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movies> query = em.createQuery(
                    "SELECT g FROM Movies g WHERE g.name = :name", Movies.class);
            query.setParameter("name", name);
            return query.getSingleResult(); // или getResultList() для нескольких
        } catch (NoResultException e) {
            return null; // если не найдено
        } finally {
            em.close();
        }
    }


    public Movies findByDescription(String description) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movies> query = em.createQuery(
                    "SELECT g FROM Movies g WHERE g.description = :description", Movies.class);
            query.setParameter("description", description);
            return query.getSingleResult(); // или getResultList() для нескольких
        } catch (NoResultException e) {
            return null; // если не найдено
        } finally {
            em.close();
        }
    }

}
