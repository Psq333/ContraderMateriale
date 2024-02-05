package it.contrader.converter;

import it.contrader.dao.ImpiantoRepository;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PistaDTO;
import it.contrader.model.Pista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PistaConverter extends AbstractConverter<Pista, PistaDTO> {


    @Autowired
    private ImpiantoRepository impiantoRepository;

    @Override
    public Pista toEntity(PistaDTO pistaDTO) {
        Pista pista = null;
        if(pistaDTO != null){
            pista = new Pista(pistaDTO.getIdPista(), pistaDTO.getNome(), pistaDTO.getDifficolta(), pistaDTO.getPrezzo(), pistaDTO.getPrenotazioniMax(), impiantoRepository.findById(pistaDTO.getImpianto()).get());
        }
        return pista;
    }

    @Override
    public PistaDTO toDTO(Pista pista) {
        PistaDTO pistaDTO = null;
        if(pista != null){
            Long id = pista.getImpianto().getIdImpianto();
            pistaDTO = new PistaDTO(pista.getIdPista(), pista.getNome(), pista.getDifficolta(), pista.getPrezzo(), pista.getPrenotazioniMax(), id);
        }
        return pistaDTO;
    }


    @Override
    protected Class<PistaDTO> getDTOClass() {
        return PistaDTO.class;
    }
}