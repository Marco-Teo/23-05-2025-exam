package dao;

import entities.DocumentoCartaceo;
import entities.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LibroDao {

    private EntityManager em;

    public LibroDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
    }

    public void save(Libro libro) {
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    public void deleteById(int id) {
        Libro libro = em.find(Libro.class, id);
        if (libro != null) {
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
            System.out.println("Libro con ID " + id + " eliminato con successo.");
        } else {
            System.out.println("Libro con ID " + id + " non trovato.");
        }
    }


    public List<Libro> cercaPerAutore(String autore) {
        TypedQuery<Libro> query = em.createQuery(
                "SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<Libro> getByGenere(String genere) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.genere = :genere", Libro.class)
                .setParameter("genere", genere)
                .getResultList();
    }

}
