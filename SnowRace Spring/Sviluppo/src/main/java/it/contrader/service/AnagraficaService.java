package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.converter.UserConverter;
import it.contrader.dao.UserRepository;
import it.contrader.converter.AnagraficaConverter;
import it.contrader.dao.AnagraficaRepository;
import it.contrader.dto.AnagraficaDTO;
import it.contrader.model.Anagrafica;

@Service
public class AnagraficaService extends AbstractService<Anagrafica, AnagraficaDTO> {

    @Autowired
    private AnagraficaConverter converter;
    @Autowired
    private AnagraficaRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    public AnagraficaDTO findByUserId(Long userId) {
        Anagrafica a = repository.findByUserId(userId);
        return converter.toDTO(a);
    }

}
