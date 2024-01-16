package it.contrader.converter;
import it.contrader.dto.AttrezzaturaDTO;
import org.springframework.stereotype.Component;
import it.contrader.model.Attrezzatura;


@Component
public class AttrezzaturaConverter extends AbstractConverter<Attrezzatura, AttrezzaturaDTO>  {


    @Override
    public Attrezzatura toEntity(AttrezzaturaDTO attrezzaturaDTO) {
        Attrezzatura attrezzatura = null;
        if(attrezzaturaDTO != null){
            attrezzatura = new Attrezzatura(attrezzaturaDTO.getIdAttrezzatura(), attrezzaturaDTO.getNome(), attrezzaturaDTO.getDescrizione(), attrezzaturaDTO.getPrezzo(),attrezzaturaDTO.getIdImpianto());
        }
        return attrezzatura;

    }

    @Override
    public AttrezzaturaDTO toDTO(Attrezzatura attrezzatura) {
        AttrezzaturaDTO attrezzaturaDTO = null;
        if(attrezzatura != null){
            attrezzaturaDTO = new AttrezzaturaDTO(attrezzatura.getIdAttrezzatura(),attrezzatura.getNome(),attrezzatura.getDescrizione(),attrezzatura.getPrezzo(),attrezzatura.getIdImpianto());
        }
        return attrezzaturaDTO;
    }
    }

