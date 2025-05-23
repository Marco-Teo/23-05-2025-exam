package entities;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("LIBRO")
public class Libro extends DocumentoCartaceo{

    private String autore;

    private String genere;

    public void setAutore(String autore) {
        this.autore = autore;
    }

    @Column(nullable = false)
    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Column(nullable = false)
    public String getAutore() {
        return autore;
    }

    public Libro(String titolo, int numeroPagine, int isbnCode, LocalDate annoPublicazione, String autore, String genere) {
        super(titolo, numeroPagine, isbnCode, annoPublicazione);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro(){}

}
