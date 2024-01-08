package it.contrader.model;

import java.util.Objects;

public class Piste {
  private String difficolta;
  private int idPista, idImpianto, prenMax;
  private double prezzo;

  public Piste(){}

    public Piste(int idPista, int idImpianto, String difficolta, double prezzo, int prenMax){
      this.idPista = idPista;
      this.difficolta = difficolta;
      this.prenMax = prenMax;
      this.idImpianto = idImpianto;
      this.prezzo = prezzo;
  }

    public Piste(int idImpianto, String difficolta, double prezzo, int prenMax) {
          this.difficolta = difficolta;
          this.prenMax = prenMax;
          this.idImpianto = idImpianto;
          this.prezzo = prezzo;

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
        return "Piste{" +
                "difficolta='" + difficolta + '\'' +
                ", prenMax='" + prenMax + '\'' +
                ", idPista=" + idPista +
                ", idImpianto=" + idImpianto +
                ", prezzo=" + prezzo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piste piste = (Piste) o;
        return idPista == piste.idPista && idImpianto == piste.idImpianto && Double.compare(prezzo, piste.prezzo) == 0 && Objects.equals(difficolta, piste.difficolta) && Objects.equals(prenMax, piste.prenMax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(difficolta, prenMax, idPista, idImpianto, prezzo);
    }
}
