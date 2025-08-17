package ru.cinescope.db.repository;

import jakarta.persistence.*;
import ru.cinescope.db.domain.Genres;

import java.util.List;

public class GenresRepository {
    private final EntityManagerFactory emf;

    // 1. Инициализация при создании репозитория
    public GenresRepository() {
        this.emf = Persistence.createEntityManagerFactory("cinescopePersistenceUnit");
    }

    // 2. Метод для поиска по ID
    public Genres findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Genres.class, id);
        } finally {
            em.close(); // Важно закрывать EntityManager
        }
    }

    // 3. Закрытие фабрики при завершении работы
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public Genres findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Genres> query = em.createQuery(
                    "SELECT g FROM Genres g WHERE g.name = :name", Genres.class);
            query.setParameter("name", name);
            return query.getSingleResult(); // или getResultList() для нескольких
        } catch (NoResultException e) {
            return null; // если не найдено
        } finally {
            em.close();
        }
    }


    public List<Genres> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Genres> query = em.createQuery(
                    "SELECT g FROM Genres g", Genres.class);  // Без WHERE!
            return query.getResultList();  // Вернёт все записи
        } finally {
            em.close();
        }
    }

}
