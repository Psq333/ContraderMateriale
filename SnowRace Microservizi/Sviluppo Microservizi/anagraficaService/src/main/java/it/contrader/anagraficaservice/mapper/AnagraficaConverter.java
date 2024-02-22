package it.contrader.anagraficaservice.mapper;


import it.contrader.anagraficaservice.dto.AnagraficaDTO;
import it.contrader.anagraficaservice.feignClient.AuthenticationFeignClient;
import it.contrader.anagraficaservice.model.Anagrafica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import lombok.Builder;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnagraficaConverter{
    @Autowired
    public AuthenticationFeignClient authFC;

    public Anagrafica toAnagrafica(AnagraficaDTO anagraficaDTO) {
        return anagraficaDTO != null ? Anagrafica.builder()
                .id(anagraficaDTO.getId())
                .nome(anagraficaDTO.getNome())
                .cognome(anagraficaDTO.getCognome())
                .indirizzo(anagraficaDTO.getIndirizzo())
                .dataDiNascita(anagraficaDTO.getDataNascita())
                .nazionalita(anagraficaDTO.getLuogoNascita())
                .userId(anagraficaDTO.getUser().getId())
                .build() : null;
    }


    public AnagraficaDTO toAnagraficaDTO(Anagrafica anagrafica) {
        return anagrafica != null ? AnagraficaDTO.builder()
                .id(anagrafica.getId())
                .nome(anagrafica.getNome())
                .cognome(anagrafica.getCognome())
                .dataNascita(anagrafica.getDataDiNascita())
                .luogoNascita(anagrafica.getNazionalita())
                .indirizzo(anagrafica.getIndirizzo())
                .user(authFC.read(anagrafica.getUserId()) != null ? authFC.read(anagrafica.getUserId()) : null)
                .build() : null;
    }

    public List<Anagrafica> toAnagraficaList(List<AnagraficaDTO> list) {
        return list.stream()
                .map(this::toAnagrafica)
                .collect(Collectors.toList());
    }

    public List<AnagraficaDTO> toAnagraficaDTOList(List<Anagrafica> list) {
        return list.stream()
                .map(this::toAnagraficaDTO)
                .collect(Collectors.toList());
    }
}