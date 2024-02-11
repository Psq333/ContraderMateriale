package it.contrader.dto;

import java.sql.Date;

public class PrenotazioniDTO {
    private int idprenotazione;
    private int idpista;
    private String username;
    private Date dataIn = null;
    private Date dataFin = null;

    public PrenotazioniDTO(){}

    public PrenotazioniDTO(int idprenotazione){
        this.idprenotazione = idprenotazione;
    }

    public PrenotazioniDTO(int idpista, String username, Date dataIn, Date dataFin){
        this.idpista = idpista;
        this.username = username;
        this.dataIn = dataIn;
        this.dataFin = dataFin;
    }

    public PrenotazioniDTO(int idprenotazione, int idpista, String username, Date dataIn, Date dataFin){
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
        return "PrenotazioniDTO{" +
                "idprenotazione=" + idprenotazione +
                ", idpista=" + idpista +
                ", username='" + username + '\'' +
                ", dataIn=" + dataIn +
                ", dataFin=" + dataFin +
                '}';
    }
}
