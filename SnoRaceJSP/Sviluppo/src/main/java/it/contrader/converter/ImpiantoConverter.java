package it.contrader.converter;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PisteDTO;
import it.contrader.model.Impianto;
import it.contrader.model.Piste;
import it.contrader.service.PisteService;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImpiantoConverter implements Converter<Impianto, ImpiantoDTO>{

    @Override
    public ImpiantoDTO toDTO(Impianto impianto) {
        return DaModelADTO(impianto);
    }

    @Override
    public Impianto toEntity(ImpiantoDTO impiantoDTO) {
        return DaDTOAModel(impiantoDTO);
    }

    @Override
    public List<ImpiantoDTO> toDTOList(List<Impianto> impiantos) {
        return DaListUserAListUserDTO(impiantos);
    }

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

    public Map<Integer,List<PisteDTO>> conversioneMappa(Map<Integer, List<Piste>> pisteMap){
        Map<Integer,List<PisteDTO>> mappaNuova = new HashMap<Integer, List<PisteDTO>>();
        for (Integer key : pisteMap.keySet()) {
            List<Piste> piste = (List<Piste>) pisteMap.get(key);
            PisteConverter service = new PisteConverter();
            mappaNuova.put(key,service.toDTOList(piste));
        }
        return mappaNuova;
    }

}
