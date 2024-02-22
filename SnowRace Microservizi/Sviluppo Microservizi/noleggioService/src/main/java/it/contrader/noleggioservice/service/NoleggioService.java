package it.contrader.noleggioservice.service;

import it.contrader.noleggioservice.dto.NoleggioDTO;
import it.contrader.noleggioservice.dto.NoleggiosDTO;
import it.contrader.noleggioservice.feignClient.AttrezzaturaFeignClient;
import it.contrader.noleggioservice.feignClient.UserFeignClient;
import it.contrader.noleggioservice.mapper.NoleggioConverter;
import it.contrader.noleggioservice.mapper.NoleggiosConverter;
import it.contrader.noleggioservice.model.Noleggio;
import it.contrader.noleggioservice.repository.NoleggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoleggioService {

    @Autowired
    private NoleggioRepository repository;

    @Autowired
    private NoleggioConverter converter;

    @Autowired
    private NoleggiosConverter noleggios;

    @Autowired
    private UserFeignClient userFg;

    @Autowired
    private AttrezzaturaFeignClient attFg;

    public NoleggioDTO save(NoleggiosDTO noleggioDTO) {
        return converter.toNoleggioDTO(repository.save(noleggios.toNoleggios(noleggioDTO)));
    }



    public NoleggioDTO read (Long id){
        return converter.toNoleggioDTO(repository.findById(id).orElse(null));
    }
    public void delete (Long id){
        repository.deleteById(id);
    }





    //getall generica
    public List<NoleggioDTO> listaNoleggi() {
        List<Noleggio> noleggios = repository.findAll();
        return converter.toListNoleggioDTO(noleggios);
    }




    public Page<NoleggioDTO> getallPageable(Pageable pageable) {
        List<NoleggioDTO> n = repository.findAll().stream()
                .map(converter::toNoleggioDTO)
                .collect(Collectors.toList());
        //PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return converter.convertToPage(n, pageable);
    }

    public Page<NoleggioDTO> getAllPaginataUser(Pageable pageable, String username) {
        Page<Noleggio> page = repository.findByUsername(pageable, username);
        return converter.convertToDTOPage(page);
    }


    public Page <NoleggioDTO> findAllPageable (Pageable pageable){
        List <NoleggioDTO> lista= repository.findAll(pageable).stream()
                .map(noleggio -> converter.toNoleggioDTO(noleggio))
                .collect(Collectors.toList());


        for (NoleggioDTO n : lista){
            n.setIdattrezzatura(attFg.read(n.getIdattrezzatura().getIdAttrezzatura()));
            n.setUsername(userFg.read(n.getUsername().getUsername())); // Passa il nome utente (String)
        }



        return converter.convertToPage(lista, pageable);
    }

}
