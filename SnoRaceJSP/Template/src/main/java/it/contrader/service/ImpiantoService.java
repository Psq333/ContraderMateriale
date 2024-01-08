package it.contrader.service;

import it.contrader.converter.ImpiantoConverter;
import it.contrader.dao.ImpiantoDAO;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Impianto;

import java.util.List;

public class ImpiantoService {

    private final ImpiantoDAO impiantoDAO;
    private final ImpiantoConverter impiantoConverter;


    public ImpiantoService(ImpiantoDAO impiantoDAO, ImpiantoConverter impiantoConverter) {
        this.impiantoDAO = impiantoDAO;
        this.impiantoConverter = impiantoConverter;
    }

    public ImpiantoService(){
        this.impiantoDAO = new ImpiantoDAO();
        this.impiantoConverter = new ImpiantoConverter();
    }

    public List<ImpiantoDTO> getAll(){
        List<Impianto> impianti = impiantoDAO.getAll();
        return impiantoConverter.DaListUserAListUserDTO(impianti);
    }

    public ImpiantoDTO read(int idImpianto){
        Impianto impianto = impiantoDAO.read(idImpianto);
        return impiantoConverter.DaModelADTO(impianto);
    }

    public boolean insert(ImpiantoDTO impiantoDTO){
       Impianto impianto = impiantoConverter.DaDTOAModel(impiantoDTO);
        return impiantoDAO.insert(impianto);
    }

    public boolean update(ImpiantoDTO impiantoDTO){
        Impianto impianto = impiantoConverter.DaDTOAModel(impiantoDTO);
        return impiantoDAO.update(impianto);
    }

    public boolean delete(int id){
        return impiantoDAO.delete(id);
    }
}
