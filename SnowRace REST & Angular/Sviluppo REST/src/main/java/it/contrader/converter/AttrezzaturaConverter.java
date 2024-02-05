package it.contrader.converter;

import it.contrader.dao.ImpiantoRepository;
import it.contrader.dto.AttrezzaturaDTO;
import it.contrader.model.Attrezzatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttrezzaturaConverter extends AbstractConverter<Attrezzatura, AttrezzaturaDTO> {

    @Autowired
    private ImpiantoConverter impiantoConverter;

    @Override
    protected Class<AttrezzaturaDTO> getDTOClass() {
        return AttrezzaturaDTO.class;
    }

    @Override
    public Attrezzatura toEntity(AttrezzaturaDTO attrezzaturaDTO) {
       Attrezzatura attrezzatura = null;
        if(attrezzaturaDTO != null){
            attrezzatura = new Attrezzatura(
                    attrezzaturaDTO.getIdAttrezzatura(),
                    attrezzaturaDTO.getNome(),
                    attrezzaturaDTO.getDescrizione(),
                    attrezzaturaDTO.getPrezzo(),
                    impiantoConverter.toEntity(attrezzaturaDTO.getIdImpianto()));

        }
        return attrezzatura;
    }

    @Override
    public AttrezzaturaDTO toDTO(Attrezzatura attrezzatura) {
        AttrezzaturaDTO attrezzaturaDTO = null;
        if(attrezzatura != null){
            attrezzaturaDTO = new AttrezzaturaDTO(
                    attrezzatura.getIdAttrezzatura(),
                    attrezzatura.getNome(),
                    attrezzatura.getDescrizione(),
                    attrezzatura.getPrezzo(),
                    impiantoConverter.toDTO(attrezzatura.getIdImpianto()));
        }
        return attrezzaturaDTO;
    }
}
