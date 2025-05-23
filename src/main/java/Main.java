import dao.ArchivioDAO;
import dao.LibroDao;
import dao.RivistaDAO;
import dao.UtenteDAO;
import entities.*;
import enumeration.Periodicita;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        ArchivioDAO archivioDAO = new ArchivioDAO();
        UtenteDAO utenteDAO = new UtenteDAO();

        Utente utente= new Utente(1008,"Gino","Parmigino", LocalDate.of(2005,05,06));
        utenteDAO.save(utente);

        Utente utente1= new Utente(1007,"Franco","Ghiande", LocalDate.of(2000,07,2));
        utenteDAO.save(utente1);



        Libro libro = new Libro("Formaggioso",200,47823750,LocalDate.of(1900,05,6
                ),"Ghiando il merlo","fantasy");
        archivioDAO.aggiungiDocumento(libro);

        Libro libro1 = new Libro("Harry Potter",700,23453452,LocalDate.of(1900,05,6
        ),"JK","fantasy");
        archivioDAO.aggiungiLibro(libro1);




        System.out.println(archivioDAO.cercaLibriPerAutore("Ghiando il merlo"));
        System.out.println(archivioDAO.cercaDocumentoPerIsbn(47823750));


        Rivista rivista = new Rivista("Il Formaggioso",80,200,LocalDate.of(2025,05,12),
               Periodicita.SETTIMANALE );

        archivioDAO.aggiungiDocumento(rivista);

        System.out.println(archivioDAO.cercaDocumentoPerIsbn(200));
        System.out.println(archivioDAO.cercaDocumentiPerAnno(2025));
        System.out.println(archivioDAO.cercaDocumentiPerAnno(1900));
        System.out.println(archivioDAO.cercaLibriPerAutore("Ghiando il merlo"));
        System.out.println(archivioDAO.cercaDocumentiPerTitolo("Formaggioso"));
        System.out.println(archivioDAO.cercaLibriPerGenere("fantasy"));

        Prestito prestitoInOrario = new Prestito(utente,libro,LocalDate.of(2025,05,23));

        prestitoInOrario.setDataRestituzioneEffettiva(LocalDate.of(2025,06,19));

        Prestito prestitoInRitardo = new Prestito(utente,libro1,LocalDate.of(2025,05,23));
        prestitoInRitardo.setDataRestituzioneEffettiva(LocalDate.of(2025,07,30));

        Prestito prestitoNonRitornato = new Prestito(utente,rivista,LocalDate.of(2024,05,23));
        prestitoNonRitornato.setDataRestituzioneEffettiva(null);

        Prestito prestitoNonRestituito = new Prestito(utente1,libro,LocalDate.of(2024,05,23));
        prestitoNonRestituito.setDataRestituzioneEffettiva(null);

        archivioDAO.aggiungiPrestito(prestitoInOrario);
        archivioDAO.aggiungiPrestito(prestitoInRitardo);
        archivioDAO.aggiungiPrestito(prestitoNonRitornato);
        archivioDAO.aggiungiPrestito(prestitoNonRestituito);

        System.out.println(archivioDAO.cercaPrestitiAttivi());
        System.out.println(archivioDAO.cercaPrestitiScaduti());

        archivioDAO.rimuoviDocumentoPerIsbn(47823750);
        archivioDAO.rimuoviDocumentoPerIsbn(23453452);
        archivioDAO.rimuoviDocumentoPerIsbn(200);
    }
}
