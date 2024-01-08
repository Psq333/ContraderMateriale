package it.contrader.converter;
import java.util.ArrayList;
import java.util.List;



import it.contrader.dto.AttrezzatureDTO;
import it.contrader.dto.UserDTO;

import it.contrader.model.Attrezzature;
import it.contrader.model.User;
public class AttrezzatureConverter {

    public AttrezzatureDTO toDTO(Attrezzature attrezzature) {
        return attrezzature != null ? new AttrezzatureDTO(attrezzature.getIdattrezzatura(),attrezzature.getPrezzo(), attrezzature.getDescrizione(), attrezzature.getNome(),attrezzature.getIdimpianto()) : null;
    }

    public Attrezzature toEntity(AttrezzatureDTO attrezzatureDTO) {
        return attrezzatureDTO != null ? new Attrezzature(attrezzatureDTO.getIdAttrezzature(),attrezzatureDTO.getPrezzo(),attrezzatureDTO.getDescrizione(), attrezzatureDTO.getNome() , attrezzatureDTO.getIdImpianto()) : null;
    }

    public List<AttrezzatureDTO> toDTOList(List<Attrezzature> attrezzatureList) {
        List<AttrezzatureDTO> attrezzatureDTOList = new ArrayList<>();
        if(attrezzatureList != null) {
            for (Attrezzature attrezzature : attrezzatureList) {
                attrezzatureDTOList.add(toDTO(attrezzature));
            }
            return attrezzatureDTOList;
        } else return null;
    }


}
