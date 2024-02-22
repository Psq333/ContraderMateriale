package it.contrader.pistaservice.mapper;

import it.contrader.pistaservice.dto.PistaDTO;
import it.contrader.pistaservice.model.Pista;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PistaMapper {

    Pista toPista(PistaDTO pistaDTO);

    PistaDTO toPistaDTO(Pista pista);

    List<PistaDTO>  toPistaDTOList (List<Pista> pista);

    List<Pista> toPistaList(List<PistaDTO> pistaDTO);

    //Page<PistaDTO> convertToDTOPage(Page<Pista> page);
}
