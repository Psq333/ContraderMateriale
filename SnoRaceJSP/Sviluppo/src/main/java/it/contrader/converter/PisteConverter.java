package it.contrader.converter;

import it.contrader.dao.PisteDAO;
import it.contrader.dto.PisteDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Piste;
import it.contrader.model.User;

import java.util.ArrayList;
import java.util.List;

public class PisteConverter {

    public PisteDTO toDTO(Piste piste) {
        return piste != null ? new PisteDTO(piste.getIdPista(), piste.getIdImpianto(), piste.getDifficolta(), piste.getPrezzo(), piste.getPrenMax()) : null;
    }


    public Piste toEntity(PisteDTO pisteDTO) {
        return pisteDTO != null ? new Piste(pisteDTO.getIdPista(), pisteDTO.getIdImpianto(), pisteDTO.getDifficolta(), pisteDTO.getPrezzo(), pisteDTO.getPrenMax()) : null;
    }

    public List<PisteDTO> toDTOList(List<Piste> pisteList) {
        List<PisteDTO> pisteDTOList = new ArrayList<>();
        if(pisteList != null) {
            for (Piste piste : pisteList) {
                pisteDTOList.add(toDTO(piste));
            }
            return pisteDTOList;
        } else return null;
    }

}