package it.contrader.service;
import it.contrader.converter.AttrezzaturaConverter;
import it.contrader.dao.AttrezzaturaRepository;
import it.contrader.dto.AttrezzaturaDTO;
import it.contrader.model.Attrezzatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AttrezzaturaService extends AbstractService<Attrezzatura, AttrezzaturaDTO> {

    @Autowired
    private AttrezzaturaRepository crudRepository;

    @Autowired
    private AttrezzaturaConverter converter;

}
