package it.contrader.dto;

public class ImpiantoDTO implements DTO {
    private int idImpianto;
    private String nome;
    private String descrizione;
    private String luogo;
    private String amministratore;

    public ImpiantoDTO(){ //Il costruttore vuoto lo uso quando voglio modificare solo qualche valore

    }

    public ImpiantoDTO(int idImpianto, String nome, String descrizione, String luogo, String amministratore) {
        this.idImpianto = idImpianto;
        this.nome = nome;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.amministratore = amministratore;
    }

    public ImpiantoDTO(String nome, String descrizione, String luogo, String amministratore) {
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
    public String toString() {
        return "ImpiantiDTO{" +
                "idImpianti=" + idImpianto +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", luogo='" + luogo + '\'' +
                ", amministratore='" + amministratore + '\'' +
                '}';
    }
}
