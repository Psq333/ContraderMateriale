package it.contrader.service;

import it.contrader.converter.PisteConverter;
import it.contrader.dao.PisteDAO;
import it.contrader.dto.PisteDTO;

import it.contrader.model.Piste;

import java.util.List;

public class PisteService {

    private final PisteDAO pisteDAO;
    private final PisteConverter pisteConverter;

    public PisteService(){
        this.pisteDAO = new PisteDAO();
        this.pisteConverter = new PisteConverter();
    }

    public List<PisteDTO> getAll(){return pisteConverter.toDTOList(pisteDAO.getAll());}

    public PisteDTO read(int id) {
        return pisteConverter.toDTO(pisteDAO.read(id));
    }

    public boolean insert(PisteDTO dto) {
        return pisteDAO.insert(pisteConverter.toEntity(dto));
    }

    public boolean update(PisteDTO dto) {
        return pisteDAO.update(pisteConverter.toEntity(dto));
    }

    public boolean delete(int id) {
        return pisteDAO.delete(id);
    }

    public List<PisteDTO> getAllUsername(String username){return pisteConverter.toDTOList(pisteDAO.getAll(username));}




}
