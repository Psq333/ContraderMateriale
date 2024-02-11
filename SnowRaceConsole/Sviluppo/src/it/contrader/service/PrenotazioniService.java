package it.contrader.service;

import it.contrader.converter.PrenotazioniConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dao.PrenotazioniDAO;
import it.contrader.dao.UserDAO;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.dto.UserDTO;

import java.util.List;
import java.util.Map;

public class PrenotazioniService {
    private final PrenotazioniDAO prenotazioniDAO;
    private final PrenotazioniConverter prenotazioniConverter;

    public PrenotazioniService() {
        this.prenotazioniDAO = new PrenotazioniDAO();
        this.prenotazioniConverter = new PrenotazioniConverter();
    }
    public List<PrenotazioniDTO> getAll() {
        return prenotazioniConverter.toDTOList(prenotazioniDAO.getAll());
    }
    public List<PrenotazioniDTO> getStorico(String username) {
        return prenotazioniConverter.toDTOList(prenotazioniDAO.getStorico(username));
    }

    public PrenotazioniDTO read(int id) {
        return prenotazioniConverter.toDTO(prenotazioniDAO.read(id));
    }

    public boolean insert(PrenotazioniDTO dto) {
        return prenotazioniDAO.insert(prenotazioniConverter.toEntity(dto));
    }

    public boolean update(PrenotazioniDTO dto) {
        return prenotazioniDAO.update(prenotazioniConverter.toEntity(dto));
    }

    public boolean delete(int id) {
        return prenotazioniDAO.delete(id);
    }
    public List<PrenotazioniDTO> search(Map<String, String> quest){
        return prenotazioniConverter.toDTOList(prenotazioniDAO.search(quest));
    }

}