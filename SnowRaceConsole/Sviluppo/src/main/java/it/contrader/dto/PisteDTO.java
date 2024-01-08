package it.contrader.dto;

public class PisteDTO {

    private String difficolta;
    private int idPista, idImpianto, prenMax;
    private double prezzo;

    public PisteDTO(){}

    public PisteDTO(int idPista){
        this.idPista = idPista;
    }

    public PisteDTO(int idImpianto, String difficolta, double prezzo, int prenMax){
        this.idImpianto = idImpianto;
        this.prezzo = prezzo;
        this.difficolta = difficolta;
        this.prenMax = prenMax;
    }
    public PisteDTO(int idPista, int idImpianto, String difficolta, double prezzo,  int prenMax){
        this.idPista = idPista;
        this.idImpianto = idImpianto;
        this.prezzo = prezzo;
        this.difficolta = difficolta;
        this.prenMax = prenMax;
    }

    public String getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(String difficolta) {
        this.difficolta = difficolta;
    }

    public int getPrenMax() {
        return prenMax;
    }

    public void setPrenMax(int prenMax) {
        this.prenMax = prenMax;
    }

    public int getIdPista() {
        return idPista;
    }

    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    public int getIdImpianto() {
        return idImpianto;
    }

    public void setIdImpianto(int idImpianto) {
        this.idImpianto = idImpianto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "PisteDTO{" +
                "difficolta='" + difficolta + '\'' +
                ", prenMax='" + prenMax + '\'' +
                ", idPista=" + idPista +
                ", idImpianto=" + idImpianto +
                ", prezzo=" + prezzo +
                '}';
    }
}
