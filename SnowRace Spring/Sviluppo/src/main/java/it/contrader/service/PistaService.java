package it.contrader.service;

import it.contrader.converter.PistaConverter;
import it.contrader.dao.PistaRepository;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PistaDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Pista;
import it.contrader.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PistaService extends AbstractService<Pista, PistaDTO> {

    @Autowired
    private PistaRepository crudRepository;

    @Autowired
    private PistaConverter converter;

    public List<PistaDTO> findByImpianto_Amministratore_Id(Long amministratoreId) {
        return converter.toDTOList(crudRepository.findByImpianto_Amministratore_Id(amministratoreId));
    }

    public PistaDTO findByNome(String nome){
        return converter.toDTO(crudRepository.findFirstByNome(nome));
    } //todo da gestire se più piste con stesso nome

}
