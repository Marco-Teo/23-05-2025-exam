package dao;

import entities.DocumentoCartaceo;
import jakarta.persistence.*;

import java.util.List;

public class DocumentoCartaceoDAO {

    private EntityManager em;

    public DocumentoCartaceoDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
    }

    public void aggiungiElemento(DocumentoCartaceo documento) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(documento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void rimuoviPerIsbn(int isbnCode) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<DocumentoCartaceo> query = em.createQuery(
                    "SELECT d FROM DocumentoCartaceo d WHERE d.isbn_code = :isbn", DocumentoCartaceo.class);
            query.setParameter("isbn", isbnCode);
            List<DocumentoCartaceo> risultati = query.getResultList();
            if (!risultati.isEmpty()) {
                em.remove(risultati.get(0));
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void save(DocumentoCartaceo documentoCartaceo) {
        em.getTransaction().begin();
        em.persist(documentoCartaceo);
        em.getTransaction().commit();
    }


    public DocumentoCartaceo getByIsbn(int isbn) {
        return em.find(DocumentoCartaceo.class, isbn);
    }

    public void deleteByIsbn(int isbn) {
        DocumentoCartaceo doc = getByIsbn(isbn);
        if (doc != null) {
            em.getTransaction().begin();
            em.remove(doc);
            em.getTransaction().commit();
        } else {
            System.out.println("Documento con ISBN " + isbn + " non trovato.");
        }
    }


    public List<DocumentoCartaceo> cercaPerTitolo(String titoloParziale) {
        TypedQuery<DocumentoCartaceo> query = em.createQuery(
                "SELECT d FROM DocumentoCartaceo d WHERE d.titolo LIKE :titolo", DocumentoCartaceo.class);
        query.setParameter("titolo", "%" + titoloParziale + "%");
        return query.getResultList();
    }

    public List<DocumentoCartaceo> cercaPerAnno(int anno) {
        TypedQuery<DocumentoCartaceo> query = em.createQuery(
                "SELECT d FROM DocumentoCartaceo d WHERE EXTRACT(YEAR FROM d.annoPublicazione) = :anno", DocumentoCartaceo.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }
}
