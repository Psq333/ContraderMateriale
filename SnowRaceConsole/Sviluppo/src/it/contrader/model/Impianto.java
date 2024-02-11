package it.contrader.model;

import java.util.Objects;

public class Impianto {
    private int idImpianto;
    private String nome;
    private String descrizione;
    private String luogo;
    private String amministratore;

    public Impianto(int idImpianto){
        this.idImpianto = idImpianto;
    }

    public Impianto(String nome, String descrizione, String luogo, String amministratore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.amministratore = amministratore;
    }

    public Impianto(int idImpianto, String nome, String descrizione, String luogo, String amministratore) {
        this.idImpianto = idImpianto;
        this.nome = nome;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.amministratore = amministratore;
    }

    public int getIdImpianto() {
        return idImpianto;
    }

    public void setIdImpianto(int idImpianto) {
        this.idImpianto = idImpianto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getAmministratore() {
        return amministratore;
    }

    public void setAmministratore(String amministratore) {
        this.amministratore = amministratore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Impianto impianto = (Impianto) o;
        return idImpianto == impianto.idImpianto && Objects.equals(nome, impianto.nome) && Objects.equals(descrizione, impianto.descrizione) && Objects.equals(luogo, impianto.luogo) && Objects.equals(amministratore, impianto.amministratore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idImpianto, nome, descrizione, luogo, amministratore);
    }

    @Override
    public String toString() {
        return "Impianti{" +
                "idImpianti=" + idImpianto +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", luogo='" + luogo + '\'' +
                ", amministratore='" + amministratore + '\'' +
                '}';
    }
}
