package it.contrader.service;

import it.contrader.converter.ImpiantoConverter;
import it.contrader.converter.NoleggioConverter;
import it.contrader.dao.ImpiantoDAO;
import it.contrader.dao.NoleggioDAO;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.NoleggioDTO;
import it.contrader.model.Impianto;
import it.contrader.model.Noleggio;

import java.util.List;

public class NoleggioService extends AbstractService<Noleggio, NoleggioDTO>{


    public final NoleggioConverter noleggioConverter;
    public final NoleggioDAO noleggioDAO;

    public NoleggioService() {
        this.dao = new NoleggioDAO();
        this.converter = new NoleggioConverter();
        this.noleggioConverter = new NoleggioConverter();
        this.noleggioDAO = new NoleggioDAO();
    }

    public List<NoleggioDTO> getAllAttrezzature(String username){
        return noleggioConverter.toDTOList(noleggioDAO.getAll(username));
    }


}


