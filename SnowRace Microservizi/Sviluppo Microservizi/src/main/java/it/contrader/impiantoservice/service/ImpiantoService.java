package it.contrader.impiantoservice.service;

import feign.FeignException;
import it.contrader.impiantoservice.dto.ImpiantoUserDTO;
import it.contrader.impiantoservice.dto.ImpiantoDTO;
import it.contrader.impiantoservice.exceptions.NotExistAdminException;
import it.contrader.impiantoservice.exceptions.NotFoundImpiantoException;
import it.contrader.impiantoservice.feignClient.UserFeignClient;
import it.contrader.impiantoservice.mapper.ImpiantoMapper;
import it.contrader.impiantoservice.model.Impianto;
import it.contrader.impiantoservice.repository.ImpiantoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ImpiantoService {

    @Autowired
    private ImpiantoRepository repository;

    @Autowired
    private ImpiantoMapper mapper;

    @Autowired
    private UserFeignClient userFeignClient;

    public Page<ImpiantoUserDTO> getAll(Pageable pageable, String username){
        List<ImpiantoUserDTO> impianti =
                toListImpiantoUserDTO(mapper.toListImpiantoDTO(repository.findAllByAmministratore(Objects.requireNonNull(userFeignClient.readusername(username).getBody()).getId())));
        PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return this.convertToPage(impianti, pageable);
    }

    public Page<ImpiantoUserDTO> getAll(Pageable pageable){
        List<ImpiantoUserDTO> impianti = toListImpiantoUserDTO(mapper.toListImpiantoDTO(repository.findAll()));
        PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return this.convertToPage(impianti, pageable);
    }

    public List<ImpiantoDTO> listaImpianti(){
        return mapper.toListImpiantoDTO(repository.findAll());
    }

    public List<ImpiantoDTO> listaImpiantiadmin(Long id){
        return mapper.toListImpiantoDTO(repository.findAllByAmministratore(id));
    }
    public ImpiantoDTO read(Long id){
        Impianto i = repository.findById(id).orElseThrow(()->new NotFoundImpiantoException("L'impianto non esiste"));
        return mapper.toImpiantoDTO(i);
    }

    public ImpiantoDTO save(ImpiantoDTO impiantoDTO) {
            userFeignClient.read(impiantoDTO.getAmministratore());
            return mapper.toImpiantoDTO(repository.save(mapper.toImpianto(impiantoDTO)));
    }

    public void delete(Long id){
        this.read(id);
        repository.deleteById(id);
    }



    public <T> Page<T> convertToPage(List<T> impianti, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), impianti.size());

        return new PageImpl<>(impianti.subList(start, end), pageable, impianti.size());
    }

    private List<ImpiantoUserDTO> toListImpiantoUserDTO(List<ImpiantoDTO> impiantoDTO){
        return impiantoDTO.stream()
                .map(this::addUserDTOtoImpianto)
                .collect(Collectors.toList());
    }

    private ImpiantoUserDTO addUserDTOtoImpianto(ImpiantoDTO impiantoDTO){
        return ImpiantoUserDTO.builder()
                .id(impiantoDTO.getId())
                .nome(impiantoDTO.getNome())
                .descrizione(impiantoDTO.getDescrizione())
                .luogo(impiantoDTO.getLuogo())
                .amministratore(userFeignClient.read(impiantoDTO.getAmministratore()))
                .build();
    }

}
