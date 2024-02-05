package it.contrader.service;

import it.contrader.dao.PistaRepository;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PistaDTO;
import it.contrader.dto.PrenotazioneDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Impianto;
import it.contrader.model.Pista;
import it.contrader.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PistaService extends AbstractService<Pista, PistaDTO>{

    @Autowired
    private PistaRepository repository;
    @Autowired
    private PrenotazioneService prenotazioneService;

    /*public List<PistaDTO> getAll(UserDTO user) {
        if(user.getUsertype().equals(User.Usertype.AMMINISTRATORE) || user.getUsertype().equals(User.Usertype.USER))
            return converter.toDTOList(repository.findAll());
        else if(user.getUsertype().equals(User.Usertype.ADMIN))
            return converter.toDTOList(repository.findByImpianto_Amministratore_Id(user.getId()));
        return null;
    }*/


    public Page<PistaDTO> getAllPaginata(Pageable pageable) {
        Page<Pista> page = repository.findAll(pageable);
        return converter.convertToDTOPage(page);
    }

    @Override
    public Page<PistaDTO> getAllPaginataCustom(Pageable pageable, String id) {
        return null;
    }

    @Override
    public Page<PistaDTO> getAllPaginataAdmin(Pageable pageable, String idAmministratore) {
    Page<Pista> page = repository.findByImpianto_Amministratore_Username(pageable, idAmministratore);
    return converter.convertToDTOPage(page);
    }


    public Page<PistaDTO> getAllPaginata(Pageable pageable, Long idImpianto) {
        Page<Pista> page = repository.findByImpianto_IdImpianto(pageable, idImpianto);
        return converter.convertToDTOPage(page);
    }

    public void deletePista(Long id){
        List<PrenotazioneDTO> prenotazioni = prenotazioneService.findByPista_IdPista(id);
        if (!prenotazioni.isEmpty()) {
            for (PrenotazioneDTO pren : prenotazioni) {
                pren.setPista(null);
                prenotazioneService.update(pren);
            }
        }
        this.delete(id);
    }

    public void deletePisteImpianto(Long idImpianto){
        List<Pista> piste = repository.findByImpianto_IdImpianto(idImpianto);
        if(!piste.isEmpty()){
            for (Pista p:piste){
                deletePista(p.getIdPista());
            }
        }
    }


}


