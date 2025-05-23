package dao;

import entities.Rivista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RivistaDAO {
    private EntityManager em;

    public RivistaDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
    }

    public void save(Rivista rivista) {
        em.getTransaction().begin();
        em.persist(rivista);
        em.getTransaction().commit();
    }

    public Rivista getById(int id) {
        return em.find(Rivista.class, id);
    }

    public void deleteById(int id) {
        Rivista e = getById(id);

        if (e != null) {
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        } else {
            System.out.println("La rivista con id: " + id  + " non è stato trovata");
        }
    }
}
