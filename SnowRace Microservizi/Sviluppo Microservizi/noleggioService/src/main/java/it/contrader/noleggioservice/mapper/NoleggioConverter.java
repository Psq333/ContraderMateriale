package it.contrader.noleggioservice.mapper;

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
public class NoleggioConverter {

    @Autowired
    UserFeignClient userFC;

    @Autowired
    AttrezzaturaFeignClient attFC;

    public Noleggio toNoleggio(NoleggioDTO noleggioDTO){
        return noleggioDTO != null ? Noleggio.builder()
                .idnoleggio(noleggioDTO.getIdnoleggio())
                .idattrezzatura(noleggioDTO.getIdattrezzatura().getIdAttrezzatura())
                .username(noleggioDTO.getUsername().getUsername())
                .startDate(noleggioDTO.getStartDate())
                .endDate(noleggioDTO.getEndDate())
                .build() : null;
    };

    public NoleggioDTO toNoleggioDTO(Noleggio noleggio){
        if (noleggio != null) {
            UserDTO u = null;
            if (noleggio.getUsername()!=null)
                u = userFC.readusername(noleggio.getUsername());
            if (u!=null){
                if (noleggio.getIdattrezzatura() != null){
                    AttrezzaturaDTO a = null;
                    try {
                        a = attFC.read(noleggio.getIdattrezzatura());
                    }
                    catch (Exception ex){
                        return new NoleggioDTO();
                    }
                if (a != null)
                    return NoleggioDTO.builder()
                        .idnoleggio(noleggio.getIdnoleggio())
                        .idattrezzatura(a)
                        .username(u)
                        .startDate(noleggio.getStartDate())
                        .endDate(noleggio.getEndDate())
                        .build();
        }}}
        return new NoleggioDTO();
    }

    public List<Noleggio> toListNoleggio(List<NoleggioDTO> noleggioDTOList){
        return noleggioDTOList.stream()
                .map(this::toNoleggio)
                .collect(Collectors.toList());
    };
    public List<NoleggioDTO> toListNoleggioDTO(List<Noleggio> noleggioList){
        return noleggioList.stream()
                .map(this::toNoleggioDTO)
                .collect(Collectors.toList());
    };

    public  Page<NoleggioDTO> convertToPage(List<NoleggioDTO> noleggioDTOS, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), noleggioDTOS.size());

        return new PageImpl<>(noleggioDTOS.subList(start, end), pageable, noleggioDTOS.size());
    }

    public Page<NoleggioDTO> convertToDTOPage(Page<Noleggio> page) {
        List<NoleggioDTO> dtoList = page.getContent().stream()
                .map(this::toNoleggioDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
