package it.contrader.converter;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.NoleggioDTO;
import it.contrader.model.Impianto;
import it.contrader.model.Noleggio;

import java.util.ArrayList;
import java.util.List;

public class NoleggioConverter implements Converter<Noleggio, NoleggioDTO>{
    public Noleggio toEntity(NoleggioDTO noleggioDTO) {
        return noleggioDTO != null ? new Noleggio(noleggioDTO.getIdNoleggio(), noleggioDTO.getUsername(), noleggioDTO.getIdAttrezzatura(), noleggioDTO.getData_inizio(), noleggioDTO.getData_fine()) : null;
    }

    public NoleggioDTO toDTO(Noleggio noleggio) {
        return noleggio != null ? new NoleggioDTO(noleggio.getIdNoleggio(), noleggio.getUsername(), noleggio.getIdAttrezzatura(), noleggio.getData_inizio(), noleggio.getData_fine()) : null;
    }

    public List<NoleggioDTO> toDTOList(List<Noleggio> noleggioList) {
        List<NoleggioDTO> noleggioDTOList = new ArrayList<>();
        if (noleggioList != null) {
            for (Noleggio noleggio : noleggioList) {
                noleggioDTOList.add(toDTO(noleggio));
            }
            return noleggioDTOList;
        } else return null;
    }

}

