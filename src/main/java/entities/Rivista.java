package entities;

import enumeration.Periodicita;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("RIVISTA")
public class Rivista extends DocumentoCartaceo{

    private Periodicita periodicita;

    @Enumerated(EnumType.STRING)
    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    public Rivista(String titolo, int numeroPagine, int isbnCode, LocalDate annoPublicazione, Periodicita periodicita) {
        super(titolo, numeroPagine, isbnCode, annoPublicazione);
        this.periodicita = periodicita;
    }

    public Rivista(){}
}
