package it.contrader.service;

import it.contrader.dao.PrenotazioneRepository;
import it.contrader.dto.PrenotazioneDTO;
import it.contrader.model.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import java.util.List;


import java.util.Date;
import java.util.List;

@Service
public class PrenotazioneService extends AbstractService<Prenotazione, PrenotazioneDTO> {
@Autowired
    private PrenotazioneRepository repository;


    @Override
    public Page<PrenotazioneDTO> getAllPaginata(Pageable pageable) {
        Page<Prenotazione> page = repository.findAll(pageable);
        return converter.convertToDTOPage(page);
    }


    public Page<PrenotazioneDTO> findByDataInizioIsGreaterThanEqual(Pageable pageable, Date dataInizio){
        Page<Prenotazione> pag = repository.findByDataInizioIsGreaterThanEqual(pageable, dataInizio);
        return converter.convertToDTOPage(pag);
    }

    public Page<PrenotazioneDTO> findByPista_IdPista(Pageable pageable, Long idPista) {
        Page<Prenotazione> pag = repository.findByPista_IdPista(pageable, idPista);
        return converter.convertToDTOPage(pag);
    }

    public List<PrenotazioneDTO> findByUser_Id(Long id){
        return converter.toDTOList(repository.findByUser_id(id));
    }
    public List<PrenotazioneDTO> findByPista_IdPista(Long idPista){
        return converter.toDTOList(repository.findByPista_idPista(idPista));
    }

    @Override
    public Page<PrenotazioneDTO> getAllPaginataCustom(Pageable pageable, String id) {
        return null;
    }

    @Override
    public Page<PrenotazioneDTO> getAllPaginataAdmin(Pageable pageable, String id) {
        return null;
    }
}
