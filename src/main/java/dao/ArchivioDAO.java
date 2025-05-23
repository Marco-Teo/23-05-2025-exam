package dao;

import entities.DocumentoCartaceo;
import entities.Libro;
import entities.Prestito;

import java.util.List;

public class ArchivioDAO {

    private DocumentoCartaceoDAO documentoDAO;
    private LibroDao libroDAO;
    private PrestitoDAO prestitoDAO;

    public ArchivioDAO() {
        documentoDAO = new DocumentoCartaceoDAO();
        libroDAO = new LibroDao();
        prestitoDAO = new PrestitoDAO();
    }

    public void aggiungiDocumento(DocumentoCartaceo doc) {
        documentoDAO.save(doc);
    }

    public void rimuoviDocumentoPerIsbn(int isbn) {
        documentoDAO.deleteByIsbn(isbn);
    }

    public List<DocumentoCartaceo> cercaDocumentiPerTitolo(String titolo) {
        return documentoDAO.cercaPerTitolo(titolo);
    }

    public List<DocumentoCartaceo> cercaDocumentiPerAnno(int anno) {
        return documentoDAO.cercaPerAnno(anno);
    }

    public void aggiungiLibro(Libro libro) {
        libroDAO.save(libro);
    }

    public void aggiungiPrestito(Prestito prestito) {
        prestitoDAO.save(prestito);
    }

    public List<Libro> cercaLibriPerAutore(String autore) {
        return libroDAO.cercaPerAutore(autore);
    }

    public List<Libro> cercaLibriPerGenere(String genere) {
        return libroDAO.getByGenere(genere);
    }

    public DocumentoCartaceo cercaDocumentoPerIsbn(int isbn) {
        return documentoDAO.getByIsbn(isbn);
    }

    public List<Prestito> cercaPrestitiAttivi() {
        return prestitoDAO.cercaPrestitiAttivi();
    }

    public List<Prestito> cercaPrestitiScaduti() {
        return prestitoDAO.cercaPrestitiScaduti();
    }

    public List<Prestito> findPrestitiByUtente(int numeroTessera) {
        return prestitoDAO.findByUtente(numeroTessera);
    }

}

