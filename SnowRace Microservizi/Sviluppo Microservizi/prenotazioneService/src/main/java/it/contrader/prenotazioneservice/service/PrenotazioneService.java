package it.contrader.prenotazioneservice.service;

import it.contrader.prenotazioneservice.dto.PistaDTO;
import it.contrader.prenotazioneservice.dto.PrenotazioneDTO;
import it.contrader.prenotazioneservice.dto.UserDTO;
import it.contrader.prenotazioneservice.feignClient.PistaFeignClient;
import it.contrader.prenotazioneservice.feignClient.UserFeignClient;
import it.contrader.prenotazioneservice.mapper.PrenotazioneConverter;
import it.contrader.prenotazioneservice.model.Prenotazione;
import it.contrader.prenotazioneservice.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository repository;

    @Autowired
    private PrenotazioneConverter converter;

    @Autowired
    private UserFeignClient userFg;

    @Autowired
    private PistaFeignClient pistaFg;

    public PrenotazioneDTO save(PrenotazioneDTO prenotazioneDTO) {
        //System.out.println(prenotazioneDTO.toString());

        PrenotazioneDTO p = converter.toPrenotazioneDTO(repository.save(converter.toPrenotazione(prenotazioneDTO)));

        p.setIdPista(pistaFg.read(p.getIdPista().getId())); //riempio tutto l'oggetto tramite il feign client (IdPista è tutto l'oggetto Pista non solo l'id)
        p.setIdUser(userFg.read(p.getIdUser().getId()));

        return p;
    }

    public PrenotazioneDTO read(Long id) {
        PrenotazioneDTO p = converter.toPrenotazioneDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Prenotazione non trovata")));
        p.setIdPista(pistaFg.read(p.getIdPista().getId()));
        p.setIdUser(userFg.read(p.getIdUser().getId()));
        return p;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    //getall generica
    public List<PrenotazioneDTO> listaPrenotazioni() {
        List<Prenotazione> prenotazioni = repository.findAll(); //reupero le Prenotazioni
        return converter.toListPrenotazioneDTO(prenotazioni);
    }

    //ALTRIMENTI, avrei potuto usare lo stream se non avessi avuto il metodo toListPrenotazione e convertire ogni elemento della lista uno ad uno.

//    prenotazioni.stream()
//            .map(prenotazione -> mapper.toPrenotazioneDTO(prenotazione))//per ogni elemento della lista Prenotazione convertilo in PrenotazioneDTO
//            .collect(Collectors.toList()); //Collezionali in una List (DTO)


    //get all Pageable non usata
    public Page<PrenotazioneDTO> getallPageable(Pageable pageable) {
        List<PrenotazioneDTO> p = repository.findAll(pageable).stream()
                .map(converter::toPrenotazioneDTO)
                .collect(Collectors.toList());
        PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return converter.convertToPage(p, pageable);
    }

    //get all Pageable usata
    public Page<PrenotazioneDTO> findAllPageable(Pageable pageable) {
        List<PrenotazioneDTO> lista = repository.findAll(pageable).stream()
                .map(prenotazione -> converter.toPrenotazioneDTO(prenotazione))
                .collect(Collectors.toList());

        //for each non usato perchè è stato fatto nel service
        for (PrenotazioneDTO p : lista) { //per ogni prenotazione nella lista
            p.setIdPista(pistaFg.read(p.getIdPista().getId())); //riempio tutto l'oggetto tramite il feign client (IdPista è tutto l'oggetto Pista non solo l'id)
            p.setIdUser(userFg.read(p.getIdUser().getId()));
        }

        return converter.convertToPage(lista, pageable);
    }


    public Page<PrenotazioneDTO> findByIdUserAndIdPista(Pageable pageable, Long idUser, Long idPista) {
        List<PrenotazioneDTO> lista = repository.findByIdUserAndIdPista(pageable, idUser, idPista).stream()
                .map(prenotazione -> converter.toPrenotazioneDTO(prenotazione))
                .collect(Collectors.toList());
        for (PrenotazioneDTO p : lista) {
            p.setIdPista(pistaFg.read(p.getIdPista().getId()));
            p.setIdUser(userFg.read(p.getIdUser().getId()));
        }

        return converter.convertToPage(lista, pageable);
    }

    public Page <PrenotazioneDTO> findByIdUserAndDataInizioIsGreaterThanEqual(Pageable pageable, Long idUser, Date dataInizio){
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        Date date = null;
//        try {
//            date = sdf.parse(dataInizio);
//            System.out.println(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        Page <Prenotazione> list = repository.findAllByIdUserAndDataInizioIsGreaterThanEqual(pageable, idUser, dataInizio);
                List<PrenotazioneDTO> lista = list.stream()
                .map(prenotazione -> converter.toPrenotazioneDTO(prenotazione))
                .collect(Collectors.toList());
        for (PrenotazioneDTO p : lista) {
            p.setIdPista(pistaFg.read(p.getIdPista().getId()));
            p.setIdUser(userFg.read(p.getIdUser().getId()));
        }

        return converter.convertToPage(lista, pageable);
    }
}
