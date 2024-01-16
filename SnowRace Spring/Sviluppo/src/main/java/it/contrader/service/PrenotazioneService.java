package it.contrader.service;

import it.contrader.converter.PrenotazioneConverter;
import it.contrader.dao.PrenotazioneRepository;
import it.contrader.dto.PistaDTO;
import it.contrader.dto.PrenotazioneDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Prenotazione;
import it.contrader.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PrenotazioneService extends AbstractService<Prenotazione, PrenotazioneDTO>{

    @Autowired
    private PrenotazioneRepository crudRepository;

    @Autowired
    private PrenotazioneConverter converter;

    @Autowired
    private PistaService pistaService;
    @Autowired
    private UserService userService;

    public List<PrenotazioneDTO> getAll(UserDTO user) {
        if(user.getUsertype().equals(User.Usertype.AMMINISTRATORE))
            return converter.toDTOList((List<Prenotazione>)crudRepository.findAll());
        else if(user.getUsertype().equals(User.Usertype.USER))
            return converter.toDTOList(crudRepository.findByUser_id(user.getId()));
        return null;
    }

   // public List<PrenotazioneDTO> searchByDate(Date data, Long idUser){
        //if (crudRepository.findByDataInizioEqualsAndUser_Id(data, idUser).isEmpty())
        //    return converter.toDTOList((List<Prenotazione>)crudRepository.findByDataInizioGreaterThanAndUser_Id(data, idUser));
        //else
        //    return converter.toDTOList((List<Prenotazione>)crudRepository.findByDataInizioEqualsAndUser_Id(data, idUser));


    //    return converter.toDTOList((List<Prenotazione>)crudRepository.findByDataInizioEqualsOrDataInizioGreaterThanAndUser_Id(data, data,idUser));
   // }

    public List<PrenotazioneDTO> searchByDate(Date data, String user){
        //if (crudRepository.findByDataInizioEqualsAndUser_Id(data, idUser).isEmpty())
        //    return converter.toDTOList((List<Prenotazione>)crudRepository.findByDataInizioGreaterThanAndUser_Id(data, idUser));
        //else
        //    return converter.toDTOList((List<Prenotazione>)crudRepository.findByDataInizioEqualsAndUser_Id(data, idUser));

        Long idUser = userService.findByUsername(user).getId();
        return converter.toDTOList((List<Prenotazione>)crudRepository.findByDataInizioEqualsOrDataInizioGreaterThanAndUser_Id(data, data,idUser));
    }

   // public List<PrenotazioneDTO> searchByPista(Long idPista, Long idUser){
    //    return converter.toDTOList(crudRepository.findByPista_IdPistaAndUser_Id(idPista, idUser));
    //}

    public List<PrenotazioneDTO> searchByPista(String pista, String user){
        //Long idPista = pistaService.findByNome(pista).getIdPista();
        PistaDTO pistaDTO = pistaService.findByNome(pista);
        Long idPista = null;
        if (pistaDTO != null)
            idPista = pistaDTO.getIdPista();
        Long idUser = userService.findByUsername(user).getId();
        return converter.toDTOList(crudRepository.findByPista_IdPistaAndUser_Id(idPista, idUser));
    }

    public List<UserDTO> usersWPren (){
        List<PrenotazioneDTO> prens = this.getAll();
        List<UserDTO> usersWPren = new ArrayList<UserDTO>();

        Set<UserDTO> uniqueUsers = new HashSet<>();

        for (PrenotazioneDTO pren : prens) {
            uniqueUsers.add(pren.getUser());
        }

        usersWPren.addAll(uniqueUsers);
        return usersWPren;
    }


}
