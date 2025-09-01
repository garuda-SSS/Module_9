package ru.cinescope.db.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.cinescope.db.domain.Movies;

public class MoviesRepository {
    private final EntityManagerFactory emf;

    public MoviesRepository() {
        this.emf = Persistence.createEntityManagerFactory("cinescopePersistenceUnit");
    }

    public Movies findById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Movies.class, id);
        }
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
