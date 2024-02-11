package it.contrader.dto;

import java.util.Objects;

public class AttrezzatureDTO {


    private int idAttrezzature, idimpianto;

    private double prezzo;

    private String descrizione, nome;

    public AttrezzatureDTO() {

    }

    public AttrezzatureDTO(int idAttrezzature) {
        this.idAttrezzature = idAttrezzature;
    }

    public <idimpianto> AttrezzatureDTO(int idAttrezzature, double prezzo, String descrizione, String nome,int idimpianto) {
        this.idAttrezzature = idAttrezzature;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.nome = nome;
        this.idimpianto = idimpianto;
    }

    public AttrezzatureDTO(double prezzo, String descrizione, String nome,int idimpianto) {
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.nome = nome;
        this.idimpianto = idimpianto;
    }

    public int getIdAttrezzature() {
        return idAttrezzature;
    }

    public void setIdAttrezzature(int idAttrezzature) {
        this.idAttrezzature = idAttrezzature;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "AttrezzatureDTO{" +
                "idAttrezzature=" + idAttrezzature +
                ", prezzo=" + prezzo +
                ", descrizione='" + descrizione + '\'' +
                ", nome='" + nome + '\'' +
                "idimpianto=" + idimpianto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttrezzatureDTO that = (AttrezzatureDTO) o;
        return idAttrezzature == that.idAttrezzature && Double.compare(prezzo, that.prezzo) == 0 && Objects.equals(descrizione, that.descrizione) && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAttrezzature, prezzo, descrizione, nome, idimpianto);
    }

    public int getIdimpianto() {
        return idimpianto;
    }

    public void setIdimpianto(int idimpianto) {
        this.idimpianto = idimpianto;
    }

    public int getidimpianto() {
        return idimpianto;
    }
}