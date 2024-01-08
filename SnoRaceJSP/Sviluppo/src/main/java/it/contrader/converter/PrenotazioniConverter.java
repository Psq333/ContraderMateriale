package it.contrader.converter;

import it.contrader.dto.PrenotazioniDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Prenotazioni;
import it.contrader.model.User;

import java.util.ArrayList;
import java.util.List;

public class PrenotazioniConverter  implements Converter <Prenotazioni, PrenotazioniDTO> {
    public PrenotazioniDTO toDTO(Prenotazioni prenotazione) {
        PrenotazioniDTO prenotazioneDTO = new PrenotazioniDTO(prenotazione.getId_prenotazione(), prenotazione.getUsername(), prenotazione.getId_pista(), prenotazione.getData_inizio(), prenotazione.getData_fine());
        return prenotazioneDTO;
    }

    public Prenotazioni toEntity(PrenotazioniDTO prenotazioneDTO) {
        Prenotazioni prenotazione = new Prenotazioni(prenotazioneDTO.getId_prenotazione(), prenotazioneDTO.getUsername(), prenotazioneDTO.getId_pista(), prenotazioneDTO.getData_inizio(), prenotazioneDTO.getData_fine());
        return prenotazione;
    }

    public List<PrenotazioniDTO> toDTOList(List<Prenotazioni> prenotazioneList) {
        //Crea una lista vuota.
        List<PrenotazioniDTO> prenotazioneDTOList = new ArrayList<PrenotazioniDTO>();
        if (prenotazioneList != null){
        //Cicla tutti gli elementi della lista e li converte uno a uno
        for(Prenotazioni prenotazione : prenotazioneList) {
            //Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
            //e lo aggiunge adda lista di DTO
            prenotazioneDTOList.add(toDTO(prenotazione));
        }
        return prenotazioneDTOList;}
        else return null;
    }

}
