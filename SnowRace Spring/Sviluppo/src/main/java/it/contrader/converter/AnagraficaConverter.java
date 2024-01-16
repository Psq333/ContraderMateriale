package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.contrader.dto.AnagraficaDTO;
import it.contrader.model.Anagrafica;

@Component
public class AnagraficaConverter extends AbstractConverter<Anagrafica, AnagraficaDTO> {

    @Autowired
    private UserConverter userConverter;
    @Override
    public Anagrafica toEntity(AnagraficaDTO anagraficaDTO) {
        Anagrafica anagrafica = null;
        if (anagraficaDTO != null) {
            anagrafica = new Anagrafica(anagraficaDTO.getId(), anagraficaDTO.getNome(), anagraficaDTO.getCognome(), anagraficaDTO.getIndirizzo(), anagraficaDTO.getDataNascita(), anagraficaDTO.getLuogoNascita(), userConverter.toEntity(anagraficaDTO.getUser()));
        }
        return anagrafica;
    }

    @Override
    public AnagraficaDTO toDTO(Anagrafica anagrafica) {
        AnagraficaDTO anagraficaDTO = null;
        if (anagrafica != null) {
            anagraficaDTO = new AnagraficaDTO(anagrafica.getId(), anagrafica.getNome(), anagrafica.getCognome(), anagrafica.getIndirizzo(), anagrafica.getDataNascita(), anagrafica.getLuogoNascita(), userConverter.toDTO(anagrafica.getUser()));

        }
        return anagraficaDTO;
    }
}