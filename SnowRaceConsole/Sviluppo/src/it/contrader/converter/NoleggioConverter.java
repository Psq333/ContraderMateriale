package it.contrader.converter;

import it.contrader.dao.NoleggioDAO;
import it.contrader.dto.NoleggioDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Noleggi;
import it.contrader.model.User;

import java.util.ArrayList;
import java.util.List;

public class NoleggioConverter {
    public NoleggioDTO toDTO(Noleggi noleggi) {
        return noleggi != null ? new NoleggioDTO(noleggi.getIdNoleggio(), noleggi.getIdAttrezzatura(), noleggi.getUsername(), noleggi.getDataInizio(), noleggi.getDataFine()) : null;
    }


    public Noleggi toEntity(NoleggioDTO noleggioDTO) {
        return noleggioDTO != null ? new Noleggi(noleggioDTO.getIdnoleggio(), noleggioDTO.getIdAttrezzatura(), noleggioDTO.getUsername(), noleggioDTO.getData_inizio(), noleggioDTO.getData_fine()) : null;
    }


    public List<NoleggioDTO> toDTOList(List<Noleggi> NoleggiList) {
        List<NoleggioDTO> NoleggiDTOList = new ArrayList<>();
        if(NoleggiList != null) {
            for (Noleggi noleggi : NoleggiList) {
                NoleggiDTOList.add(toDTO(noleggi));
            }
            return NoleggiDTOList;
        } else return null;
    }
}
