package it.contrader.model;

import java.util.Objects;

public class Attrezzature {
    private int idattrezzatura;

    private double prezzo;
    private String descrizione,nome;

    private int idimpianto;
    public Attrezzature() {
    }

    public Attrezzature(int idattrezzatura, double prezzo, String descrizione, String nome,int idimpianto) {
        this.idattrezzatura = idattrezzatura;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.nome = nome;
        this.idimpianto = idimpianto;
    }

    public Attrezzature(double prezzo, String descrizione, String nome) {
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.nome = nome;
    }

    public int getIdattrezzatura() {
        return idattrezzatura;
    }

    public void setIdattrezzatura(int idattrezzatura) {
        this.idattrezzatura = idattrezzatura;
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

    public int getIdimpianto() {
        return idimpianto;
    }

    public void setIdimpianto(int idimpianto) {
        this.idimpianto = idimpianto;
    }




    @Override
    public String toString() {
        return "Attrezzature{" +
                "idattrezzatura=" + idattrezzatura +
                ", prezzo=" + prezzo +
                ", descrizione='" + descrizione + '\'' +
                ", nome='" + nome + '\'' +
                ", idimpianto='" + idimpianto + '\'' +
                '}';


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attrezzature that = (Attrezzature) o;
        return idattrezzatura == that.idattrezzatura && prezzo == that.prezzo && Objects.equals(descrizione, that.descrizione) && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return 0;
    }


}


