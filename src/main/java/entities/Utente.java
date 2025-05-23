package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "numero_tessera", nullable = false, unique = true)
    private int numeroTessera;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(name = "data_nascita")
    private LocalDate dataDiNascita;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestito> prestiti = new ArrayList<>();

    public Utente() {}

    public Utente(int numeroTessera, String nome, String cognome, LocalDate dataDiNascita) {
        this.numeroTessera = numeroTessera;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(int numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void addPrestito(Prestito p) {
        prestiti.add(p);
        p.setUtente(this);
    }

    public void removePrestito(Prestito p) {
        prestiti.remove(p);
        p.setUtente(null);
    }

    @Override
    public String toString() {
        return "Utente{" +
                "tessera=" + numeroTessera +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", prestitiCount=" + prestiti.size() +
                '}';
    }
}
