package it.contrader.model;
import java.sql.Date;
import java.util.Objects;

public class Prenotazioni {

    private int idprenotazione;
    private int idpista;
    private String username;
    private Date dataIn = null;
    private Date dataFin = null;

    public Prenotazioni(){}

    public Prenotazioni(int idprenotazione){
        this.idprenotazione = idprenotazione;
    }

    public Prenotazioni(int idpista, String username, Date dataIn, Date dataFin){
        this.idpista = idpista;
        this.username = username;
        this.dataIn = dataIn;
        this.dataFin = dataFin;
    }

    public Prenotazioni(int idprenotazione, int idpista, String username, Date dataIn, Date dataFin){
        this.idprenotazione = idprenotazione;
        this.idpista = idpista;
        this.username = username;
        this.dataIn = dataIn;
        this.dataFin = dataFin;
    }

    public int getIdprenotazione() {
        return idprenotazione;
    }

    public void setIdprenotazione(int idprenotazione) {
        this.idprenotazione = idprenotazione;
    }

    public int getIdpista() {
        return idpista;
    }

    public void setIdpista(int idpista) {
        this.idpista = idpista;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDataIn() {
        return dataIn;
    }

    public void setDataIn(Date dataIn) {
        this.dataIn = dataIn;
    }

    public Date getDataFin() {
        return dataFin;
    }

    public void setDataFin(Date dataFin) {
        this.dataFin = dataFin;
    }

    @Override
    public String toString() {
        return "Prenotazioni{" +
                "idprenotazione=" + idprenotazione +
                ", idpista=" + idpista +
                ", username='" + username + '\'' +
                ", dataIn=" + dataIn +
                ", dataFin=" + dataFin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazioni that = (Prenotazioni) o;
        return idprenotazione == that.idprenotazione && idpista == that.idpista && Objects.equals(username, that.username) && Objects.equals(dataIn, that.dataIn) && Objects.equals(dataFin, that.dataFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idprenotazione, idpista, username, dataIn, dataFin);
    }
}
