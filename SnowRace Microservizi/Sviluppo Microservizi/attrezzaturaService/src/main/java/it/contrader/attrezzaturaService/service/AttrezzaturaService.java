package it.contrader.attrezzaturaService.service;

import it.contrader.attrezzaturaService.dto.AttrezzaturaDTO;
import it.contrader.attrezzaturaService.dto.ImpiantoDTO;
import it.contrader.attrezzaturaService.feignClient.ImpiantoFeignClient;
import it.contrader.attrezzaturaService.model.Attrezzatura;
import it.contrader.attrezzaturaService.repository.AttrezzaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AttrezzaturaService {


    @Autowired
    private AttrezzaturaRepository repository;


    @Autowired
    private ImpiantoFeignClient feignClient;

    public AttrezzaturaDTO insert (AttrezzaturaDTO attrezzaturaDTO ){
    Attrezzatura att=
        Attrezzatura.builder()
            .nome(attrezzaturaDTO.getNome())
            .descrizione(attrezzaturaDTO.getDescrizione())
            .prezzo(attrezzaturaDTO.getPrezzo())
            .idImpianto(attrezzaturaDTO.getIdImpianto().getId())
            .build();
    Attrezzatura a = repository.save(att);



    return AttrezzaturaDTO.builder()
            .id(a.getId())
            .nome(a.getNome())
            .descrizione(a.getDescrizione())
            .prezzo(a.getPrezzo())
            .idImpianto(feignClient.read(a.getIdImpianto()))
            .build();
    }

    public AttrezzaturaDTO update (AttrezzaturaDTO attrezzaturaDTO){
        Attrezzatura att=
                Attrezzatura.builder()
                        .id(attrezzaturaDTO.getId())
                        .nome(attrezzaturaDTO.getNome())
                        .descrizione(attrezzaturaDTO.getDescrizione())
                        .prezzo(attrezzaturaDTO.getPrezzo())
                        .idImpianto(attrezzaturaDTO.getIdImpianto().getId())
                        .build();
        Attrezzatura a = repository.save(att);

        return AttrezzaturaDTO.builder()
                .id(a.getId())
                .nome(a.getNome())
                .descrizione(a.getDescrizione())
                .prezzo(a.getPrezzo())
                .idImpianto(feignClient.read(a.getIdImpianto()))
                .build();
    }

    public void delete(long id) {repository.deleteById(id); }

    public Page<AttrezzaturaDTO> getall(Pageable pageable) {
        List <Attrezzatura> a = repository.findAll();
        List <AttrezzaturaDTO> listDTO = new ArrayList<>();
        for (Attrezzatura attr: a ){
            listDTO.add(AttrezzaturaDTO.builder()
                            .id(attr.getId())
                            .nome(attr.getNome())
                            .descrizione(attr.getDescrizione())
                            .prezzo(attr.getPrezzo())
                            .idImpianto(feignClient.read(attr.getIdImpianto()))
                            .build());
        }
            return new PageImpl<>(listDTO, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), listDTO.size());


    }

   public AttrezzaturaDTO read  (long id) {

        Attrezzatura a = repository.findById(id).orElse(null);

        return AttrezzaturaDTO.builder()
                .id(a.getId())
                .nome(a.getNome())
                .descrizione(a.getDescrizione())
                .prezzo(a.getPrezzo())
               .idImpianto(feignClient.read(a.getIdImpianto()))
                .build(); }






    public Page <AttrezzaturaDTO> convertToPage(List<AttrezzaturaDTO> attrezzature, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), attrezzature.size());

        return new PageImpl<>(attrezzature.subList(start, end), pageable, attrezzature.size());
    }

    public List<AttrezzaturaDTO> getAll(){
        List <Attrezzatura> a = repository.findAll();
        List <AttrezzaturaDTO> listDTO = new ArrayList<>();
        for (Attrezzatura attr: a ){
            listDTO.add(AttrezzaturaDTO.builder()
                    .id(attr.getId())
                    .nome(attr.getNome())
                    .descrizione(attr.getDescrizione())
                    .prezzo(attr.getPrezzo())
                    .idImpianto(feignClient.read(attr.getIdImpianto()))
                    .build());
        }
        return listDTO;
    }

}
