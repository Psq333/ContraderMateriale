package it.contrader.service;



import it.contrader.converter.AttrezzaturaConverter;
import it.contrader.dao.AttrezzaturaRepository;
import it.contrader.dto.AttrezzaturaDTO;
import it.contrader.dto.NoleggioDTO;
import it.contrader.model.Attrezzatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrezzaturaService  extends AbstractService<Attrezzatura, AttrezzaturaDTO> {

    @Autowired
    private AttrezzaturaRepository repository;

    @Autowired
    private AttrezzaturaConverter converter;

    @Autowired
    private NoleggioService noleggioService;

    public Page<AttrezzaturaDTO> getAllPaginata(Pageable pageable) {
        Page<Attrezzatura> page = repository.findAll(pageable);
        return converter.convertToDTOPage(page);

    }
    @Override
    public Page<AttrezzaturaDTO> getAllPaginataCustom(Pageable pageable, String id) {
        return null;
    }
    @Override
    public Page<AttrezzaturaDTO> getAllPaginataAdmin(Pageable pageable, String id) {
        return null;
    }


    @Autowired
    private AttrezzaturaRepository attrezzaturaRepository;
    public void setImpiantoNull(Long id) {
        List<Attrezzatura> attrezzature = attrezzaturaRepository.findByidImpianto_idImpianto(id);
        for (Attrezzatura att : attrezzature) {
            att.setIdImpianto(null);
            attrezzaturaRepository.save(att);
        }
    }
    public void deleteAttrezzatura(Long id){
        List<NoleggioDTO> noleggi = noleggioService.findByIdattrezzatura_idAttrezzatura(id);
        if (!noleggi.isEmpty()){
            for (NoleggioDTO nol: noleggi ) {
                nol.setIdattrezzatura(null);
                noleggioService.update(nol);
            }
        }
        this.delete(id);
    }
}
