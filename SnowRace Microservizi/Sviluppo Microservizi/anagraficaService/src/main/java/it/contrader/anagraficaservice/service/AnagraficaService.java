package it.contrader.anagraficaservice.service;

import it.contrader.anagraficaservice.dto.AnagraficaDTO;
import it.contrader.anagraficaservice.dto.UserDTO;
import it.contrader.anagraficaservice.exception.AnagNotFoundException;
import it.contrader.anagraficaservice.exception.UserNotFoundException;
import it.contrader.anagraficaservice.mapper.AnagraficaConverter;
import it.contrader.anagraficaservice.repository.AnagraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import it.contrader.anagraficaservice.feignClient.AuthenticationFeignClient;
import java.util.stream.Collectors;

@Service
public class AnagraficaService {

    @Autowired
    private AuthenticationFeignClient authFC;

    @Autowired
    private AnagraficaRepository repository;

    @Autowired
    private AnagraficaConverter mapper;

    public AnagraficaDTO save(AnagraficaDTO anagraficaDTO, Long id) throws UserNotFoundException {
        anagraficaDTO.setUser(authFC.read(id));
        return mapper.toAnagraficaDTO(repository.save(mapper.toAnagrafica(anagraficaDTO)));
    }

    public List<AnagraficaDTO> getAll(){
        return mapper.toAnagraficaDTOList(repository.findAll());
    }

    public Page<AnagraficaDTO> getAllPage(int pageNumber, int pageSize){
        List<AnagraficaDTO> dtoList = mapper.toAnagraficaDTOList(repository.findAll());
        return new PageImpl<>(dtoList, PageRequest.of(pageNumber, pageSize), dtoList.size());
    }

    public AnagraficaDTO read(Long id){
        return mapper.toAnagraficaDTO(repository.findById(id).orElse(null));
    }

    public List<UserDTO> getUserWoAnag(){
        List<UserDTO> allUsers = authFC.getAll();
        List<UserDTO> usersWoAnag;
        List<AnagraficaDTO> anagraficaDTOList = this.getAll();

        Set<Long> userIdsInAnag = anagraficaDTOList.stream()
                .map(anagraficaDTO -> {
                    UserDTO userDTO = anagraficaDTO.getUser();
                    return userDTO != null ? userDTO.getId() : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        usersWoAnag = allUsers.stream()
                .filter(user -> !userIdsInAnag.contains(user.getId()))
                .collect(Collectors.toList());

        return usersWoAnag;
    }

    public AnagraficaDTO findByUserId(Long id){
        return mapper.toAnagraficaDTO(repository.findByUserId(id).orElse(null));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
