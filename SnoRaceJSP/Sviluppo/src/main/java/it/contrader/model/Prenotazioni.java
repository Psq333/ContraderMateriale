package it.contrader.model;

import it.contrader.enums.Usertype;

import java.util.Objects;


public class Prenotazioni {
    private int id_prenotazione;

    private int id_pista;

    private  String username;

    private String data_inizio;

    private String data_fine;

    public Prenotazioni() {

    }

    public Prenotazioni (int id_pista, String username, String data_inizio, String data_fine) {
        this.id_pista = id_pista;
        this.username = username;
        this.data_inizio= data_inizio;
        this.data_fine = data_fine;
        this.username = username;
    }

    public Prenotazioni (int id_prenotazione, String username, int id_pista, String data_inizio, String data_fine) {
        this.id_prenotazione = id_prenotazione;
        this.id_pista = id_pista;
        this.username = username;
        this.data_inizio= data_inizio;
        this.data_fine = data_fine;
    }

    public int getId_prenotazione() {
        return id_prenotazione;
    }

    public void setId_prenotazione(int id_prenotazione) {
        this.id_prenotazione = id_prenotazione;
    }

    public int getId_pista() {
        return id_pista;
    }

    public void setId_pista(int id_pista) {
        this.id_pista = id_pista;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(String data_inizio) {
        this.data_inizio = data_inizio;
    }

    public String getData_fine() {
        return data_fine;
    }

    public void setData_fine(String data_fine) {
        this.data_fine = data_fine;
    }

    @Override
    public String toString() {
        return "Prenotazioni{" +
                "id_prenotazione=" + id_prenotazione +
                ", id_pista=" + id_pista +
                ", data_inizio='" + data_inizio + '\'' +
                ", data_fine='" + data_fine + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazioni that = (Prenotazioni) o;
        return id_prenotazione == that.id_prenotazione && id_pista == that.id_pista && Objects.equals(data_inizio, that.data_inizio) && Objects.equals(data_fine, that.data_fine);
    }

}
