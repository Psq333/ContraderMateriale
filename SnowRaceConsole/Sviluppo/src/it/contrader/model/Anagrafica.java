package it.contrader.model;

import java.util.Date;
import java.util.Objects;

public class Anagrafica {

    private int idutenti;
    private String nome;
    private String cognome;
    private String indirizzo;
    private Date data_nascita;
    private String luogo_nascita;
    private String username;

    // Constructors

    public Anagrafica() {}

    public Anagrafica(int idutenti, String nome, String cognome, String indirizzo, Date data_nascita, String luogo_nascita, String username) {
        // Parameterized constructor
        this.idutenti = idutenti;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.data_nascita = data_nascita;
        this.luogo_nascita = luogo_nascita;
        this.username = username;
    }

    public Anagrafica(String nome, String cognome, String indirizzo, Date data_nascita, String luogo_nascita, String username) {
        // Constructor without idutenti
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.data_nascita = data_nascita;
        this.luogo_nascita = luogo_nascita;
        this.username = username;
    }

    public int getIdutenti() {
        return idutenti;
    }

    public void setIdutenti(int idutenti) {
        this.idutenti = idutenti;
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

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Date getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(Date data_nascita) {
        this.data_nascita = data_nascita;
    }

    public String getLuogo_nascita() {
        return luogo_nascita;
    }

    public void setLuogo_nascita(String luogo_nascita) {
        this.luogo_nascita = luogo_nascita;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Anagrafica{" +
                "idutenti=" + idutenti +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", data_nascita=" + data_nascita +
                ", luogo_nascita='" + luogo_nascita + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anagrafica that = (Anagrafica) o;
        return idutenti == that.idutenti && Objects.equals(nome, that.nome) && Objects.equals(cognome, that.cognome) && Objects.equals(indirizzo, that.indirizzo) && Objects.equals(data_nascita, that.data_nascita) && Objects.equals(luogo_nascita, that.luogo_nascita) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idutenti, nome, cognome, indirizzo, data_nascita, luogo_nascita, username);
    }
}
