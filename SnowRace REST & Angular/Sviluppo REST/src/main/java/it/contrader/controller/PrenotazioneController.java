package it.contrader.controller;

import it.contrader.dto.PrenotazioneDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;
import it.contrader.service.PrenotazioneService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/prenotazione")
@CrossOrigin(origins = "http://localhost:4200")
public class PrenotazioneController extends AbstractController<PrenotazioneDTO>{
    @Autowired
    private PrenotazioneService prenotazioneService;
    @GetMapping("/findByDataInizioIsGreaterThanEqual")
    public ResponseEntity<Page<PrenotazioneDTO>> findByDataInizioIsGreaterThanEqual(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("data") Date dataInizio){
        return new ResponseEntity<>(prenotazioneService.findByDataInizioIsGreaterThanEqual(PageRequest.of(pageNumber, pageSize), dataInizio), HttpStatus.OK);
    }

    @GetMapping("/findByPistaStorico")
    public ResponseEntity<Page<PrenotazioneDTO>> findByPista_IdPista(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("idPista") long idPista){
        return new ResponseEntity<>(prenotazioneService.findByPista_IdPista(PageRequest.of(pageNumber, pageSize), idPista), HttpStatus.OK);
    }
}
