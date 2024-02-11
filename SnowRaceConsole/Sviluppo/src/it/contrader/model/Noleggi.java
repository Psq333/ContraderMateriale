package it.contrader.model;

import java.util.Date;
import java.util.Objects;

public class Noleggi {
    int idnoleggio, idattrezzatura;
    String username;
    Date data_inizio, data_fine;

    public Noleggi() {

    }

    public Noleggi(int idattrezzatura,String username, Date data_inizio, Date data_fine) {
        this.idattrezzatura=idattrezzatura;
        this.username=username;
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;
    }

    public Noleggi(int idnoleggio, int idattrezzatura,String username, Date data_inizio, Date data_fine) {
        this.idnoleggio=idnoleggio;
        this.idattrezzatura=idattrezzatura;
        this.username=username;
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;
    }

    public int getIdNoleggio() {
        return idnoleggio;
    }

    public void setIdNoleggio(int idNoleggio) {
        this.idnoleggio = idnoleggio;
    }

    public int getIdAttrezzatura() {
        return idattrezzatura;
    }

    public void setIdAttrezzatura(int idattrezzatura) {
        this.idattrezzatura = idattrezzatura;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDataInizio() {
        return data_inizio;
    }

    public void setDataInizio(Date data_inizio) {
        this.data_inizio = data_inizio;
    }

    public Date getDataFine() {
        return data_fine;
    }

    public void setDataFine(Date dataFine) {
        this.data_fine = data_fine;
    }

    @Override
    public String toString() {
        return "Noleggi{" +
                "idnoleggio=" + idnoleggio +
                ", idattrezzatura=" + idattrezzatura +
                ", username='" + username + '\'' +
                ", data_inizio=" + data_inizio +
                ", data_fine=" + data_fine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noleggi noleggi = (Noleggi) o;
        return idnoleggio == noleggi.idnoleggio && idattrezzatura == noleggi.idattrezzatura && Objects.equals(username, noleggi.username) && Objects.equals(data_inizio, noleggi.data_inizio) && Objects.equals(data_fine, noleggi.data_fine);
    }

}
