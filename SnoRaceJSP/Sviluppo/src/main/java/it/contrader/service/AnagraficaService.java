package it.contrader.service;

import it.contrader.dao.AnagraficaDAO;
import it.contrader.dto.AnagraficaDTO;
import it.contrader.converter.AnagraficaConverter;
import it.contrader.model.Anagrafica;

import java.util.List;

public class AnagraficaService extends AbstractService<Anagrafica, AnagraficaDTO>{


    public AnagraficaService() {
        this.dao = new AnagraficaDAO();
        this.converter = new AnagraficaConverter();
    }

    public AnagraficaDTO read(String username) {
        return converter.toDTO(((AnagraficaDAO)dao).read(username));
    }

    public boolean delete(String username) {
        return ((AnagraficaDAO)dao).delete(username);
    }


}
