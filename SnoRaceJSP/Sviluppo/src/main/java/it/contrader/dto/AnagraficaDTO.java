package it.contrader.dto;

import java.util.Date;

public class AnagraficaDTO implements DTO{

    private String nome, cognome, indirizzo, luogoNascita, username;
    private Date dataNascita;
    private int id;
    public AnagraficaDTO(){}

    public AnagraficaDTO(int id){
        this.id = id;
    }

    public AnagraficaDTO(int id, String nome, String cognome, String indirizzo, Date dataNascita, String luogoNascita, String username){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.username = username;
        this.luogoNascita = luogoNascita;
        this.dataNascita = dataNascita;
    }

    public AnagraficaDTO(String nome, String cognome, String indirizzo, String luogoNascita, String username, Date dataNascita){
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.username = username;
        this.luogoNascita = luogoNascita;
        this.dataNascita = dataNascita;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public String getUsername() {
        return username;
    }

    public void setUsertype(String username) {
        this.username = username;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    @Override
    public String toString() {
        return
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", luogoNascita='" + luogoNascita + '\'' +
                ", username='" + username + '\'' +
                ", dataNascita=" + dataNascita +
                ", id=" + id;
    }
}
