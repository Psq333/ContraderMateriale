package it.contrader.impiantoservice.mapper;

import it.contrader.impiantoservice.dto.ImpiantoDTO;
import it.contrader.impiantoservice.model.Impianto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImpiantoMapper {

    Impianto toImpianto(ImpiantoDTO impiantoDTO);

    ImpiantoDTO toImpiantoDTO(Impianto impianto);

    List<Impianto> toListImpianto(List<ImpiantoDTO> impiantoDTOList);

    List<ImpiantoDTO> toListImpiantoDTO(List<Impianto> impiantoList);
}
