package it.contrader.dto;

import java.util.Date;

public class NoleggioDTO implements DTO{
    private int idNoleggio;

    private String username;

    private int idAttrezzatura;

    private Date data_inizio;


    private Date data_fine;


    public NoleggioDTO(String username, int idAttrezzatura, Date data_inizio, Date data_fine) {
        this.username = username;
        this.idAttrezzatura = idAttrezzatura;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
    }

    public NoleggioDTO(String username, Date data_inizio, Date data_fine) {
        this.username = username;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
    }


    public NoleggioDTO(int idNoleggio, String username, int idAttrezzatura, Date data_inizio, Date data_fine) {
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

    public void setData_inizio(Date data_inizio) {
        this.data_inizio = data_inizio;
    }

    public Date getData_inizio() {
        return data_inizio;
    }

    public void setData_fine(Date data_fine) {
        this.data_fine = data_fine;
    }

    public Date getData_fine() {
        return data_fine;
    }

    @Override
    public String toString() {
        return  "NoleggioDTO{" +
                "idNoleggio=" + idNoleggio +
                ", username='" + username + '\'' +
                ", idAttrezzatura='" + idAttrezzatura + '\'' +
                ", data_inizio='" + data_inizio + '\'' +
                ", data_fine='" + data_fine + '\'' +
                '}';
    }
}


