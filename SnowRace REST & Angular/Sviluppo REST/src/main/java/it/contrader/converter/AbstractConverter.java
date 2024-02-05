package it.contrader.converter;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractConverter<Entity, DTO> implements Converter<Entity, DTO> {

    public List<Entity> toEntityList(List<DTO> listDTO) {
        List<Entity> list = new ArrayList<>();


        if (listDTO != null) {
            list = listDTO.stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
        }
        return list;
    }

    public List<DTO> toDTOList(List<Entity> listEntity) {
        List<DTO> list = new ArrayList<DTO>();

        if (listEntity != null) {
            list = listEntity.stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        }
        return list;
    }
    private final ModelMapper modelMapper = new ModelMapper();

    public Page<DTO> convertToDTOPage(Page<Entity> page) {
        return page.map(entity -> modelMapper.map(entity, getDTOClass()));
    }

    protected abstract Class<DTO> getDTOClass();
}
