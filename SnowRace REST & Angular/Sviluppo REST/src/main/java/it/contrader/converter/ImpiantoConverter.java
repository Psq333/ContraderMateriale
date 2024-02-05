package it.contrader.converter;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PistaDTO;
import it.contrader.model.Impianto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImpiantoConverter extends AbstractConverter<Impianto, ImpiantoDTO> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PistaConverter pistaConverter;

    @Override
    public Impianto toEntity(ImpiantoDTO impiantoDTO) {
        Impianto impianto = null;
        if(impiantoDTO != null){
            impianto = new Impianto(impiantoDTO.getIdImpianto(),impiantoDTO.getNome(),impiantoDTO.getDescrizione(),impiantoDTO.getLuogo(), userConverter.toEntity(impiantoDTO.getAmministratore()), pistaConverter.toEntityList(impiantoDTO.getPiste()));
        }
        return impianto;
    }

    @Override
    public ImpiantoDTO toDTO(Impianto impianto) {
        ImpiantoDTO impiantoDTO = null;
        if(impianto != null){
            List<PistaDTO> list = pistaConverter.toDTOList(impianto.getPiste());
            impiantoDTO = new ImpiantoDTO(impianto.getIdImpianto(),impianto.getNome(),impianto.getDescrizione(),impianto.getLuogo(), userConverter.toDTO(impianto.getAmministratore()), list);
        }
        return impiantoDTO;
    }

    @Override
    protected Class<ImpiantoDTO> getDTOClass() {
        return ImpiantoDTO.class;
    }
}