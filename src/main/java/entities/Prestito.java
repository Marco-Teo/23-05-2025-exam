package entities;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestiti")
public class Prestito {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "utente_id", referencedColumnName = "numero_tessera")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "documento_id", referencedColumnName = "isbn_code", nullable = false)
    private DocumentoCartaceo documento;

    @Column(name = "data_inizio_prestito", nullable = false)
    private LocalDate dataInizioPrestito;

    @Column(name = "data_restituzione_prevista", nullable = false)
    private LocalDate dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private LocalDate dataRestituzioneEffettiva;

    public Prestito() {}

    public Prestito(Utente utente, DocumentoCartaceo documento, LocalDate dataInizioPrestito) {
        this.utente = utente;
        this.documento = documento;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public DocumentoCartaceo getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoCartaceo documento) {
        this.documento = documento;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                ", utente=" + utente.getNumeroTessera() +
                ", documento=" + documento.getIsbnCode() +
                ", inizio=" + dataInizioPrestito +
                ", prevista=" + dataRestituzionePrevista +
                ", effettiva=" + dataRestituzioneEffettiva +
                '}';
    }
}
