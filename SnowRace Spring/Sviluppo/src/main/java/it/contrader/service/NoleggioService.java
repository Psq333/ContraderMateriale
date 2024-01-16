package it.contrader.service;

import it.contrader.converter.NoleggioConverter;
import it.contrader.dao.NoleggioRepository;
import it.contrader.dto.NoleggioDTO;
import it.contrader.model.Attrezzatura;
import it.contrader.model.Noleggio;
import it.contrader.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NoleggioService extends AbstractService<Noleggio, NoleggioDTO> {

    @Autowired
    private NoleggioConverter converter;
    @Autowired
    private NoleggioRepository repository;

    public NoleggioDTO findByUserAndStartDateBetweenAndIdattrezzatura(User user, Date startDate, Date endDate, Attrezzatura idattrezzatura) {
        return converter.toDTO(repository.findByUserAndStartDateBetweenAndIdattrezzatura(user, startDate, endDate, idattrezzatura));
    }


}
