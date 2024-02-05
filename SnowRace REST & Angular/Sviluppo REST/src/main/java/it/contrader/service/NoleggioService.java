package it.contrader.service;

import it.contrader.converter.NoleggioConverter;
import it.contrader.dao.NoleggioRepository;
import it.contrader.dto.NoleggioDTO;
import it.contrader.model.Noleggio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoleggioService extends AbstractService<Noleggio, NoleggioDTO> {

    @Autowired
    private NoleggioRepository repository;

    @Autowired
    private NoleggioConverter converter;


    public Page<NoleggioDTO> getAllPaginata(Pageable pageable) {
        Page<Noleggio> page = repository.findAll(pageable);
        return converter.convertToDTOPage(page);
    }

    public Page<NoleggioDTO> getAllPaginataUser(Pageable pageable, String username) {
        Page<Noleggio> page = repository.findByUsername_username(pageable, username);
        return converter.convertToDTOPage(page);
    }







    @Override
    public Page<NoleggioDTO> getAllPaginataCustom(Pageable pageable, String id) {
        return null;
    }

    @Override
    public Page<NoleggioDTO> getAllPaginataAdmin(Pageable pageable, String id) {
        return null;
    }

    public List<NoleggioDTO> findByUser_Id(Long id){
        return converter.toDTOList(repository.findByUsername_Id(id));
    }
    public List<NoleggioDTO> findByIdattrezzatura_idAttrezzatura(Long id){
        return converter.toDTOList(repository.findByIdattrezzatura_idAttrezzatura(id));
    }

}
