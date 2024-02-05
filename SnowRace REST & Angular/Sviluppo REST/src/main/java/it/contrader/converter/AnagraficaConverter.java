package it.contrader.converter;

import it.contrader.dto.AnagraficaDTO;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PistaDTO;
import it.contrader.model.Anagrafica;
import it.contrader.model.Impianto;
import it.contrader.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnagraficaConverter extends AbstractConverter<Anagrafica, AnagraficaDTO>{
    @Autowired
    public UserConverter userConverter;
    @Override
    public Anagrafica toEntity(AnagraficaDTO anagraficaDTO) {
        return anagraficaDTO != null ? Anagrafica.builder()
                .id(anagraficaDTO.getId())
                .nome(anagraficaDTO.getNome())
                .cognome(anagraficaDTO.getCognome())
                .indirizzo(anagraficaDTO.getIndirizzo())
                .dataNascita(anagraficaDTO.getDataNascita())
                .luogoNascita(anagraficaDTO.getLuogoNascita())
                .user(userConverter.toEntity(anagraficaDTO.getUser()))
                .build() : null;
    }


    @Override
    public AnagraficaDTO toDTO(Anagrafica anagrafica) {
        return anagrafica != null ? AnagraficaDTO.builder()
                .id(anagrafica.getId())
                .nome(anagrafica.getNome())
                .cognome(anagrafica.getCognome())
                .dataNascita(anagrafica.getDataNascita())
                .luogoNascita(anagrafica.getLuogoNascita())
                .indirizzo(anagrafica.getIndirizzo())
                .user(userConverter.toDTO(anagrafica.getUser()))
                .build() : null;
    }

    @Override
    protected Class<AnagraficaDTO> getDTOClass() {
        return AnagraficaDTO.class;
    }
}
