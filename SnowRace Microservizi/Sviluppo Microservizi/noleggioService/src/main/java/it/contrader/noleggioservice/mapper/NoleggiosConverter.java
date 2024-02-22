package it.contrader.noleggioservice.mapper;

import it.contrader.noleggioservice.dto.NoleggiosDTO;
import it.contrader.noleggioservice.dto.UserDTO;


import it.contrader.noleggioservice.dto.AttrezzaturaDTO;
import it.contrader.noleggioservice.dto.NoleggioDTO;
import it.contrader.noleggioservice.feignClient.AttrezzaturaFeignClient;
import it.contrader.noleggioservice.feignClient.UserFeignClient;
import it.contrader.noleggioservice.model.Noleggio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoleggiosConverter {

    @Autowired
    UserFeignClient userFC;

    @Autowired
    AttrezzaturaFeignClient attFC;

    public Noleggio toNoleggios(NoleggiosDTO noleggioDTO){
        return noleggioDTO != null ? Noleggio.builder()
                .idnoleggio(noleggioDTO.getIdnoleggio())
                .idattrezzatura(noleggioDTO.getIdattrezzatura())
                .username(noleggioDTO.getUsername())
                .startDate(noleggioDTO.getStartDate())
                .endDate(noleggioDTO.getEndDate())
                .build() : null;
    };

    public NoleggiosDTO toNoleggiosDTO(Noleggio noleggio){
        if (noleggio != null) {
                return NoleggiosDTO.builder()
                        .idnoleggio(noleggio.getIdnoleggio())
                        .idattrezzatura(noleggio.getIdattrezzatura())
                        .username(noleggio.getUsername())
                        .startDate(noleggio.getStartDate())
                        .endDate(noleggio.getEndDate())
                        .build();
        }
        return null;
    }

    public List<Noleggio> toListNoleggio(List<NoleggiosDTO> noleggioDTOList){
        return noleggioDTOList.stream()
                .map(this::toNoleggios)
                .collect(Collectors.toList());
    };
    public List<NoleggiosDTO> toListNoleggioDTO(List<Noleggio> noleggioList){
        return noleggioList.stream()
                .map(this::toNoleggiosDTO)
                .collect(Collectors.toList());
    };

    public  Page<NoleggioDTO> convertToPage(List<NoleggioDTO> noleggioDTOS, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), noleggioDTOS.size());

        return new PageImpl<>(noleggioDTOS.subList(start, end), pageable, noleggioDTOS.size());
    }
}
