package it.contrader.service;
import it.contrader.model.Prenotazioni;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.dao.PrenotazioniDAO;
import it.contrader.converter.PrenotazioniConverter;

import java.util.List;


public class PrenotazioniService extends AbstractService<Prenotazioni, PrenotazioniDTO>{

    public PrenotazioniService(){
        this.dao = new PrenotazioniDAO();
        this.converter = new PrenotazioniConverter();
    }

    public List<PrenotazioniDTO> Search(String what, String which, String who, String by){
        java.sql.Date whichData;
        if (who != null){
            if (what != null){
                if (what.equals("data")) {
                whichData = dateToSQL(which);
                return converter.toDTOList(((PrenotazioniDAO)dao).search(whichData, who));
                }
                else {
                    return converter.toDTOList(((PrenotazioniDAO)dao).search(which, who));
                }
            } else return converter.toDTOList(((PrenotazioniDAO)dao).getPrenotazioniUser(who));
        }
        else {
            if (by.equals("AMMINISTRATORE")){
                return converter.toDTOList(dao.getAll());}
        }
        return null;
    }
}
