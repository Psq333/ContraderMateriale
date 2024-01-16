package it.contrader.converter;

import it.contrader.dao.ImpiantoRepository;
import it.contrader.dto.PistaDTO;
import it.contrader.model.Impianto;
import it.contrader.model.Pista;
import it.contrader.service.ImpiantoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PistaConverter extends AbstractConverter<Pista, PistaDTO> {

    @Autowired
    private ImpiantoConverter impiantoConverter;

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
}
