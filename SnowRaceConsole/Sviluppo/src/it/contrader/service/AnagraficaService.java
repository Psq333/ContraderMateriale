package it.contrader.service;

import it.contrader.dao.AnagraficaDAO;
import it.contrader.dto.AnagraficaDTO;
import it.contrader.converter.AnagraficaConverter;

import java.util.List;

public class AnagraficaService {

    private final AnagraficaDAO anagraficaDAO;
    private final AnagraficaConverter anagraficaConverter;

    public AnagraficaService() {
        this.anagraficaDAO = new AnagraficaDAO();
        this.anagraficaConverter = new AnagraficaConverter();
    }

    public List<AnagraficaDTO> getAll() {
        return anagraficaConverter.toDTOList(anagraficaDAO.getAll());
    }

    public AnagraficaDTO read(String username) {
        return anagraficaConverter.toDTO(anagraficaDAO.read(username));
    }

    public boolean insert(AnagraficaDTO dto, String username) {
        return anagraficaDAO.insert(anagraficaConverter.toEntity(dto), username);
    }

    public boolean update(AnagraficaDTO dto, String username) {
        return anagraficaDAO.update(anagraficaConverter.toEntity(dto), username);
    }

    public boolean delete(String username) {
        return anagraficaDAO.delete(username);
    }
}
