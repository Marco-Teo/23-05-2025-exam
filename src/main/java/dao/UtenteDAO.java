package dao;

import entities.Rivista;
import entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
    }

    public void save(Utente utente) {
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }

    public Utente getByIid(int id) {
        return em.find(Utente.class, id);
    }

    public Utente cercaUtenteByTessera(int numeroTessera) {
        return em.find(Utente.class, numeroTessera);
    }

    public void deleteById(int id) {
        Utente e = getByIid(id);

        if (e != null) {
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        } else {
            System.out.println("L'utente con id: " + id  + " non è stato trovato");
        }
    }
}
