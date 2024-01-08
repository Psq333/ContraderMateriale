package it.contrader.model;

import java.util.Date;
import java.util.Objects;

public class Noleggio {
    private int idNoleggio;
    private String username;
    private int idAttrezzatura;
    private Date data_inizio;
    private Date data_fine;

    public Noleggio(){

    }

    public Noleggio(int idNoleggio){
        this.idNoleggio = idNoleggio;
    }

    public Noleggio(String username, int idAttrezzatura, Date data_inizio, Date data_fine) {
        this.username = username;
        this.idAttrezzatura = idAttrezzatura;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
    }

    public Noleggio(int idNoleggio, String username, int idAttrezzatura, Date data_inizio, Date data_fine) {
        this.idNoleggio = idNoleggio;
        this.username = username;
        this.idAttrezzatura = idAttrezzatura;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
    }

    public int getIdNoleggio() {
        return idNoleggio;
    }

    public void setIdNoleggio(int idNoleggio) {
        this.idNoleggio = idNoleggio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdAttrezzatura() {
        return idAttrezzatura;
    }

    public void setIdAttrezzatura(int idAttrezzatura) {
        this.idAttrezzatura = idAttrezzatura;
    }

    public java.sql.Date getData_inizio() {
        return (java.sql.Date) data_inizio;
    }

    public void setData_inizio(Date data_inizio) {
        this.data_inizio = data_inizio;
    }

    public java.sql.Date getData_fine() {
        return (java.sql.Date) data_fine;
    }

    public void setData_fine(Date data_fine) {
        this.data_fine = data_fine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noleggio noleggio = (Noleggio) o;
        return idNoleggio == noleggio.idNoleggio && Objects.equals(username, noleggio.username) && Objects.equals(idAttrezzatura, noleggio.idAttrezzatura) && Objects.equals(data_inizio, noleggio.data_inizio) && Objects.equals(data_fine, noleggio.data_fine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNoleggio, username, idAttrezzatura, data_inizio, data_fine);
    }

    @Override
    public String toString() {
        return "Noleggi{" +
                "idNoleggi=" + idNoleggio +
                ", username='" + username + '\'' +
                ", idAttrezzature='" + idAttrezzatura + '\'' +
                ", data_inizio='" + data_inizio + '\'' +
                ", data_fine='" + data_fine + '\'' +
                '}';
    }
}


