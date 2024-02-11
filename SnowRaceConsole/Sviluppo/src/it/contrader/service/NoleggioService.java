package it.contrader.service;

import it.contrader.converter.NoleggioConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dao.NoleggioDAO;
import it.contrader.dao.UserDAO;
import it.contrader.dto.NoleggioDTO;
import it.contrader.dto.UserDTO;

import java.util.List;

public class NoleggioService {
    private final NoleggioDAO noleggioDAO;
    private final NoleggioConverter noleggioConverter;

    /**
     * Constructor to initialize the specific DAO and Converter.
     */
    public NoleggioService() {
        this.noleggioDAO = new NoleggioDAO();
        this.noleggioConverter = new NoleggioConverter();
    }


    public List<NoleggioDTO> getAll() {
        return noleggioConverter.toDTOList(noleggioDAO.getAll());
    }


    public NoleggioDTO read(int idnoleggio) {
        return noleggioConverter.toDTO(noleggioDAO.read(idnoleggio));
    }


    public boolean insert(NoleggioDTO dto) {
        return noleggioDAO.insert(noleggioConverter.toEntity(dto));
    }


    public boolean update(NoleggioDTO dto) {
        return noleggioDAO.update(noleggioConverter.toEntity(dto));
    }


    public boolean delete(int idnoleggio) {
        return noleggioDAO.delete(idnoleggio);
    }
}
