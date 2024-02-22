package it.contrader.prenotazioneservice.mapper;

import it.contrader.prenotazioneservice.dto.PistaDTO;
import it.contrader.prenotazioneservice.dto.PrenotazioneDTO;
import it.contrader.prenotazioneservice.dto.UserDTO;
import it.contrader.prenotazioneservice.feignClient.PistaFeignClient;
import it.contrader.prenotazioneservice.feignClient.UserFeignClient;
import it.contrader.prenotazioneservice.model.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrenotazioneConverter {
//   @Autowired
//    private UserFeignClient userFg;
//
//    @Autowired
//    private  PistaFeignClient pistaFg;

    public Prenotazione toPrenotazione(PrenotazioneDTO prenotazioneDTO){
        return prenotazioneDTO != null ? Prenotazione.builder()
                .id(prenotazioneDTO.getId())
                .dataInizio(prenotazioneDTO.getDataInizio())
                .dataFine(prenotazioneDTO.getDataFine())
                .idPista(prenotazioneDTO.getIdPista().getId())
                .idUser(prenotazioneDTO.getIdUser().getId())
                .build() : null;
    };

    public PrenotazioneDTO toPrenotazioneDTO(Prenotazione prenotazione){
        PistaDTO p = new PistaDTO(); //creo un oggetto vuoto
        p.setId(prenotazione.getIdPista()); //setto solo l'id e il resto null

        UserDTO u = new UserDTO();
        u.setId(prenotazione.getIdUser());
        return prenotazione != null ? PrenotazioneDTO.builder()
                .id(prenotazione.getId())
                .dataInizio(prenotazione.getDataInizio())
                .dataFine(prenotazione.getDataFine())
                .idPista(p)
                .idUser(u)
                .build() : null;
    };

    public List<Prenotazione> toListPrenotazione(List<PrenotazioneDTO> prenotazioneDTOList){
        return prenotazioneDTOList.stream()
                .map(this::toPrenotazione)
                .collect(Collectors.toList());
    };
    public List<PrenotazioneDTO> toListPrenotazioneDTO(List<Prenotazione> prenotazioneList){
        return prenotazioneList.stream()
                .map(this::toPrenotazioneDTO)
                .collect(Collectors.toList());
    };

    public  Page<PrenotazioneDTO> convertToPage(List<PrenotazioneDTO> prenotazioni, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), prenotazioni.size());

        return new PageImpl<>(prenotazioni.subList(start, end), pageable, prenotazioni.size());
    }
}
