package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.AnagraficaDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Anagrafica;
import it.contrader.model.User;

public class AnagraficaConverter {

    public AnagraficaDTO toDTO(Anagrafica anagrafica) {
        return anagrafica != null ? new AnagraficaDTO(anagrafica.getIdutenti(), anagrafica.getNome(), anagrafica.getCognome(), anagrafica.getIndirizzo(), anagrafica.getData_nascita(), anagrafica.getLuogo_nascita(), anagrafica.getUsername()) : null;
    }

    public Anagrafica toEntity(AnagraficaDTO anagraficaDTO) {
        return anagraficaDTO != null ? new Anagrafica(anagraficaDTO.getId(), anagraficaDTO.getNome(), anagraficaDTO.getCognome(), anagraficaDTO.getIndirizzo(), anagraficaDTO.getDataNascita(), anagraficaDTO.getLuogoNascita(), anagraficaDTO.getUsername()) : null;
    }

    public List<AnagraficaDTO> toDTOList(List<Anagrafica> anagraficaList) {
        List<AnagraficaDTO> anagraficaDTOList = new ArrayList<>();
        if(anagraficaList != null) {
            for (Anagrafica anagrafica : anagraficaList) {
                anagraficaDTOList.add(toDTO(anagrafica));
            }
            return anagraficaDTOList;
        } else return null;
    }
}
