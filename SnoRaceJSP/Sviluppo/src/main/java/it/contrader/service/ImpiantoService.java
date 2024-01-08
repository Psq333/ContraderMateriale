package it.contrader.service;

import it.contrader.converter.ImpiantoConverter;
import it.contrader.dao.ImpiantoDAO;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PisteDTO;
import it.contrader.dto.UserDTO;
import it.contrader.enums.Usertype;
import it.contrader.model.Impianto;
import it.contrader.model.Piste;
import it.contrader.model.User;

import java.util.List;
import java.util.Map;

public class ImpiantoService extends AbstractService<Impianto, ImpiantoDTO>{

    public ImpiantoService(){
        this.dao = new ImpiantoDAO();
        this.converter = new ImpiantoConverter();
    }


    public List<ImpiantoDTO> getAll(UserDTO user) {
        if(user.getUsertype().equals(Usertype.AMMINISTRATORE) || user.getUsertype().equals(Usertype.USER))
            return converter.toDTOList(dao.getAll());
        else if(user.getUsertype().equals(Usertype.ADMIN))
            return converter.toDTOList(((ImpiantoDAO)dao).getImpiantiAmministratore(user.getUsername()));
        return null;
    }

    public boolean update(UserDTO user, ImpiantoDTO impiantoDTO) {
        if(user.getUsertype().equals(Usertype.AMMINISTRATORE))
            return dao.update(converter.toEntity(impiantoDTO));
        else if(user.getUsertype().equals(Usertype.ADMIN)){
            ImpiantoDTO impiantoDTODB = converter.toDTO(dao.read(impiantoDTO.getIdImpianto()));
            if(!impiantoDTODB.getAmministratore().equals(user.getUsername()))
                return false;
            else dao.update(converter.toEntity(impiantoDTO));
        }
        return true;
    }


    public boolean delete(UserDTO user, int idImpiantoDTO) {
        if(user.getUsertype().equals(Usertype.AMMINISTRATORE))
            return dao.delete(idImpiantoDTO);
        else if(user.getUsertype().equals(Usertype.ADMIN)){
            ImpiantoDTO impiantoDTODB = converter.toDTO(dao.read(idImpiantoDTO));
            if(!impiantoDTODB.getAmministratore().equals(user.getUsername()))
                return false;
            else dao.delete(impiantoDTODB.getIdImpianto());
        }
        return true;
    }

    public Map<Integer, List<PisteDTO>> getPisteImpianto(){
        Map<Integer, List<Piste>> mappa = ((ImpiantoDAO)dao).getPisteImpianto();
        return  ((ImpiantoConverter)converter).conversioneMappa(mappa);
    }
}
