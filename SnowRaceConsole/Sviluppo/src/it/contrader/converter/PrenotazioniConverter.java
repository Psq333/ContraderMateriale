package it.contrader.converter;

import it.contrader.dto.PrenotazioniDTO;
import it.contrader.model.Prenotazioni;
import it.contrader.model.User;

import java.util.ArrayList;
import java.util.List;

public class PrenotazioniConverter {
    public PrenotazioniDTO toDTO(Prenotazioni prenotazione) {
        return prenotazione != null ? new PrenotazioniDTO(prenotazione.getIdprenotazione(), prenotazione.getIdpista(), prenotazione.getUsername(), prenotazione.getDataIn(), prenotazione.getDataFin()) : null;
    }

    public Prenotazioni toEntity(PrenotazioniDTO prenotazioniDTO) {
        return prenotazioniDTO != null ? new Prenotazioni(prenotazioniDTO.getIdprenotazione(),prenotazioniDTO.getIdpista(), prenotazioniDTO.getUsername(), prenotazioniDTO.getDataIn(), prenotazioniDTO.getDataFin()) : null;
    }

    public List<PrenotazioniDTO> toDTOList(List<Prenotazioni> prenotazioniList) {
        List<PrenotazioniDTO> prenotazioniDTOList = new ArrayList<>();
        if(prenotazioniList != null) {
            for (Prenotazioni prenotazioni : prenotazioniList) {
                prenotazioniDTOList.add(toDTO(prenotazioni));
            }
            return prenotazioniDTOList;
        } else return null;
    }
}
