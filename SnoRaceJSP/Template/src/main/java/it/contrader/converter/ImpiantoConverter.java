package it.contrader.converter;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.model.Impianto;

import java.util.ArrayList;
import java.util.List;

public class ImpiantoConverter {
    public ImpiantoDTO DaModelADTO(Impianto impianto){
        return impianto != null ? new ImpiantoDTO(impianto.getIdImpianto(), impianto.getNome(), impianto.getDescrizione(), impianto.getLuogo(), impianto.getAmministratore()) : null;
    }

    public Impianto DaDTOAModel(ImpiantoDTO impiantoDTO){
        return impiantoDTO != null ? new Impianto(impiantoDTO.getIdImpianto(), impiantoDTO.getNome(), impiantoDTO.getDescrizione(), impiantoDTO.getLuogo(), impiantoDTO.getAmministratore()) : null;
    }

    public List<ImpiantoDTO> DaListUserAListUserDTO(List<Impianto> impiantoList){
        List<ImpiantoDTO> impiantoDTOList = new ArrayList<>();
        if(impiantoList != null){
            for (Impianto impianto : impiantoList){
                impiantoDTOList.add(DaModelADTO(impianto));
            }
            return impiantoDTOList;
        } else  return  null;
    }

}
