package dao;

import entities.Prestito;
import entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class PrestitoDAO {

    private EntityManager em;

    public PrestitoDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
    }

    public void save(Prestito prestito) {
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
    }
    public void update(Prestito prestito) {
        em.getTransaction().begin();
        em.merge(prestito);
        em.getTransaction().commit();
    }

    public Utente getById(int id) {
        return em.find(Utente.class, id);
    }

    public void deleteById(int id) {
        Prestito p = em.find(Prestito.class, id);
        if (p != null) {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } else {
            System.out.println("Prestito non trovato con ID: " + id);
        }
    }


    public List<Prestito> cercaPrestitiAttivi() {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL", Prestito.class);
        return query.getResultList();
    }

    public List<Prestito> cercaPrestitiScaduti() {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < CURRENT_DATE", Prestito.class);
        return query.getResultList();
    }

    public List<Prestito> findByUtente(int numeroTessera) {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :tessera", Prestito.class);
        query.setParameter("tessera", numeroTessera);
        return query.getResultList();
    }

}
