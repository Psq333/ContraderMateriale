package it.contrader.pistaservice.service;

import it.contrader.pistaservice.dto.ImpiantoDTO;
import it.contrader.pistaservice.dto.PistaDTO;
import it.contrader.pistaservice.dto.UserDTO;
import it.contrader.pistaservice.feignClient.ImpiantoFeignClient;
import it.contrader.pistaservice.feignClient.UserFeignClient;
import it.contrader.pistaservice.mapper.PistaMapper;
import it.contrader.pistaservice.repository.PistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PistaService {

    @Autowired
    private UserFeignClient userFC;
    @Autowired
    private ImpiantoFeignClient impiantoFC;
    @Autowired
    private PistaRepository repository;
    @Autowired
    private PistaMapper mapper;

    public Page<PistaDTO> getallpisteimpianto(Pageable pageable, String username){
        UserDTO user = userFC.readusername(username);
        List<ImpiantoDTO> impianti = impiantoFC.listaimpiantiadmin(user.getId());
        List<PistaDTO> piste = new ArrayList<>();
        for(ImpiantoDTO imp : impianti){
            piste.addAll(mapper.toPistaDTOList(repository.findByImpianto(imp.getId())));
        }
        return this.convertToPage(piste, pageable);
    }

    public List<ImpiantoDTO> getallpisteimpiantolist(String username){
        UserDTO user = userFC.readusername(username);
        List<ImpiantoDTO> impianti = impiantoFC.listaimpiantiadmin(user.getId());
        return impianti;
    }

    public Page<PistaDTO> getall(Pageable pageable) {
        List<PistaDTO> p = repository.findAll(pageable).stream()
                .map(mapper::toPistaDTO)
                .collect(Collectors.toList());
        PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return this.convertToPage(p, pageable);
    }

    public PistaDTO insert(PistaDTO pistaDTO){
        return mapper.toPistaDTO(repository.save(mapper.toPista(pistaDTO)));
    }

    public PistaDTO read(long id){
        return mapper.toPistaDTO(repository.findById(id).get());
    }
    public PistaDTO update(PistaDTO pistaDTO){
        return mapper.toPistaDTO(repository.save(mapper.toPista(pistaDTO)));
    }

    public void delete(long id){
        repository.deleteById(id);
    }

    public Page<PistaDTO> convertToPage(List<PistaDTO> piste, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), piste.size());

        return new PageImpl<>(piste.subList(start, end), pageable, piste.size());
    }

    public List<PistaDTO> getallPiste() {
        return mapper.toPistaDTOList(repository.findAll());
    }


    public Page<PistaDTO> getallPisteImpianto(Pageable pageable, Long idImpianto) {
        List<PistaDTO> page = repository.findAllByImpianto(pageable, idImpianto).stream()
                .map(mapper::toPistaDTO)
                .collect(Collectors.toList());
        PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return this.convertToPage(page, pageable);
    }

}
