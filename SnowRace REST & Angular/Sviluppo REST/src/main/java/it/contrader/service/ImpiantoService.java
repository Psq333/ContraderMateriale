package it.contrader.service;

import it.contrader.converter.AttrezzaturaConverter;
import it.contrader.converter.ImpiantoConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dao.AttrezzaturaRepository;
import it.contrader.dao.ImpiantoRepository;
import it.contrader.dao.UserRepository;
import it.contrader.dto.AttrezzaturaDTO;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Attrezzatura;
import it.contrader.model.Impianto;
import it.contrader.model.User;
import it.contrader.model.User.Usertype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImpiantoService extends AbstractService<Impianto, ImpiantoDTO> {

    @Autowired
    private ImpiantoRepository repository;

    @Autowired
    private ImpiantoConverter converter;



    public Page<ImpiantoDTO> getAllPaginata(Pageable pageable) {
        Page<Impianto> page = repository.findAll(pageable);
        return converter.convertToDTOPage(page);
    }

    public List<ImpiantoDTO> getAllAdmin(String username) {
        List<Impianto> list = repository.findByAmministratore_Username(username);
        return converter.toDTOList(list);
    }

    public Page<ImpiantoDTO> getAllPaginataCustom(Pageable pageable, String idAmministratore) {
        Page<Impianto> page = repository.findByAmministratore_Username(pageable, idAmministratore);
        return converter.convertToDTOPage(page);
    }

    @Override
    public Page<ImpiantoDTO> getAllPaginataAdmin(Pageable pageable, String id) {
        return null;
    }


    @Autowired
    private AttrezzaturaService attrezzaturaService;
    @Autowired
    private PistaService pistaService;
    @Override
    public void delete(long id) {
        attrezzaturaService.setImpiantoNull(id);
        pistaService.deletePisteImpianto(id);
        repository.deleteById(id);
    }


    public List<ImpiantoDTO> findByAmministratore_Id(Long id){
        return converter.toDTOList(repository.findByAmministratore_Id(id));
    }


}