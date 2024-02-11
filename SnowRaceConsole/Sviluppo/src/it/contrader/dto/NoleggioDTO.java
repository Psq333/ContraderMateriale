package it.contrader.dto;

import java.util.Date;

public class NoleggioDTO {
    private int idnoleggio;

    private String username;

        private Date data_inizio;

        private Date data_fine;


        private int idattrezzatura;



        public NoleggioDTO() {

        }

    public NoleggioDTO(String username, Date data_inizio, Date data_fine) {
        this.username = username;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
    }


    public NoleggioDTO(int idnoleggio, String username, Date data_inizio, Date data_fine, int idattrezzatura) {

        this.idnoleggio = idnoleggio;
        this.username = username;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;

        this.idattrezzatura = idattrezzatura;

    }

    public NoleggioDTO(int idNoleggio, int idAttrezzatura, String username, String dataInizio, String dataFine) {
    }

    public NoleggioDTO(int idnoleggio, int idattrezzatura, String username, Date data_inizio, Date data_fine) {
    }

    public int getIdnoleggio() {
            return this.idnoleggio;
        }
        public void setIdnoleggio(int idnoleggio) {
            this.idnoleggio = idnoleggio;
        }

    public int getIdAttrezzatura() {

        return this.idattrezzatura;
    }
    public void setIdAttrezzatura(int idAttrezzatura) {
        this.idattrezzatura = idAttrezzatura;

    }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String usertype) {
            this.username = username;
        }


        public Date getData_inizio() {
            return this.data_inizio;
        }

        public void setData_inizio(Date data_inizio) {
            this.data_inizio = data_inizio;
        }

        public void setData_fine(Date data_fine) {
            this.data_fine = data_fine;
        }

        public Date getData_fine() {
            return data_fine;
        }

        @Override
        public String toString() {

            return  idnoleggio + "\t" + + idattrezzatura +"\t\t" + data_inizio +"\t\t" +   data_fine + "\t\t" + username;
        }

}

