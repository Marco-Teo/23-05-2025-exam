package entities;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_documento", discriminatorType = DiscriminatorType.STRING)
@Table(name = "documenti_cartacei")
public abstract class  DocumentoCartaceo {


    private String titolo;

    @Column(name = "numero_pagine")
    private int numeroPagine;


    @Id
    @Column(name = "isbn_code", unique = true, nullable = false)
    private int isbnCode;

    @Column(name = "anno_pubblicazioni", nullable = false)
    private LocalDate annoPublicazione;

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public int getIsbnCode() {
        return isbnCode;
    }

    public void setIsbnCode(int isbnCode) {
        this.isbnCode = isbnCode;
    }

    public LocalDate getAnnoPublicazione() {
        return annoPublicazione;
    }

    public void setAnnoPublicazione(LocalDate annoPublicazione) {
        this.annoPublicazione = annoPublicazione;
    }

    public DocumentoCartaceo(String titolo, int numeroPagine, int isbnCode, LocalDate annoPublicazione) {
        this.titolo = titolo;
        this.numeroPagine = numeroPagine;
        this.isbnCode = isbnCode;
        this.annoPublicazione = annoPublicazione;
    }

    public DocumentoCartaceo(){}

    @Override
    public String toString() {
        return "DocumentoCartaceo{" +
                "titolo='" + titolo + '\'' +
                ", numeroPagine=" + numeroPagine +
                ", isbnCode=" + isbnCode +
                ", annoPublicazione=" + annoPublicazione +
                '}';
    }
}
