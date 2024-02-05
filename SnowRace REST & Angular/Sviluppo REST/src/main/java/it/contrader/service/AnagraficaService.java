package it.contrader.service;

import it.contrader.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.contrader.converter.UserConverter;
import it.contrader.converter.AnagraficaConverter;
import it.contrader.dao.AnagraficaRepository;
import it.contrader.dto.AnagraficaDTO;
import it.contrader.model.Anagrafica;

import java.util.List;

@Service
public class AnagraficaService extends AbstractService<Anagrafica, AnagraficaDTO> {

    @Autowired
    private AnagraficaConverter converter;
    @Autowired
    private AnagraficaRepository repository;
    @Autowired
    private UserConverter userConverter;

    public AnagraficaDTO findByUserId(Long userId) {
        Anagrafica a = repository.findByUserId(userId);
        return converter.toDTO(a);
    }

    public Page<AnagraficaDTO> getAllPaginata(Pageable pageable) {
        Page<Anagrafica> page = repository.findAll(pageable);
        return converter.convertToDTOPage(page);
    }

    @Override
    public Page<AnagraficaDTO> getAllPaginataCustom(Pageable pageable, String id) {
        return null;
    }

    @Override
    public Page<AnagraficaDTO> getAllPaginataAdmin(Pageable pageable, String id) {
        return null;
    }


    public List<UserDTO> getUserWoAnag(){
        return userConverter.toDTOList(repository.findUsersWithoutAnagrafica());
    }

}
