package it.contrader.service;
import it.contrader.dto.AttrezzatureDTO;
import it.contrader.dao.AttrezzatureDAO;
import it.contrader.converter.AttrezzatureConverter;
import it.contrader.model.Attrezzature;
import it.contrader.model.Impianto;

import java.util.*;
public class AttrezzatureService {

    private final AttrezzatureDAO attrezzatureDAO;
    private final AttrezzatureConverter attrezzatureConverter;

    public AttrezzatureService() {
        this.attrezzatureDAO = new AttrezzatureDAO();
        this.attrezzatureConverter = new AttrezzatureConverter();
    }

    public List<AttrezzatureDTO> getAll() {
        return attrezzatureConverter.toDTOList(attrezzatureDAO.getAll());
    }

    public AttrezzatureDTO read(int idAttrezzature) {
        return attrezzatureConverter.toDTO(attrezzatureDAO.read(idAttrezzature));
    }

    public boolean insert(AttrezzatureDTO dto) {
        return attrezzatureDAO.insert(attrezzatureConverter.toEntity(dto));
    }

    public boolean update(AttrezzatureDTO dto) {
        return attrezzatureDAO.update(attrezzatureConverter.toEntity(dto));
    }

    public boolean delete(int idAttrezzature) {
        return attrezzatureDAO.delete(idAttrezzature);
    }

    public AttrezzatureDTO vistaUser(int id){
        List<AttrezzatureDTO> attrezzature =   attrezzatureConverter.toDTOList(attrezzatureDAO.getAll());
        for(AttrezzatureDTO att :attrezzature){
            if (att.getIdAttrezzature()== id){
                return att;
            }
        }
        return null;
    }

}
