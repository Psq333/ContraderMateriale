package it.contrader.service;

import it.contrader.converter.ImpiantoConverter;
import it.contrader.dao.ImpiantoRepository;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Impianto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import it.contrader.model.User.Usertype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ImpiantoService extends AbstractService<Impianto, ImpiantoDTO> {

    @Autowired
    private ImpiantoRepository crudRepository;

    @Autowired
    private ImpiantoConverter converter;

    @Override
    public List<ImpiantoDTO> getAll() {
        return converter.toDTOList((List<Impianto>) (crudRepository.findAll()));
    }

    public List<ImpiantoDTO> getAll(UserDTO user) {
        if(user.getUsertype().equals(Usertype.AMMINISTRATORE) || user.getUsertype().equals(Usertype.USER))
            return converter.toDTOList(crudRepository.findAll());
        else if(user.getUsertype().equals(Usertype.ADMIN))
            return converter.toDTOList(crudRepository.findByAmministratore_id(user.getId()));
        return null;
    }
}
