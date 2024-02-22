package it.contrader.prenotazioneservice.controller;

import it.contrader.prenotazioneservice.dto.PrenotazioneDTO;
import it.contrader.prenotazioneservice.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pren")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService service;

    @PostMapping("/insertPrenotazione")
    public ResponseEntity <PrenotazioneDTO> insert (@RequestBody PrenotazioneDTO prenotazioneDTO) {
        return new ResponseEntity<>(service.save(prenotazioneDTO), HttpStatus.OK);
    }
    @PutMapping("/updatePrenotazione")
    public ResponseEntity <PrenotazioneDTO> update (@RequestBody PrenotazioneDTO prenotazioneDTO) {
        return new ResponseEntity<>(service.save(prenotazioneDTO), HttpStatus.OK);
    }

    @GetMapping("/readPrenotazione")
    public  PrenotazioneDTO read (@RequestParam Long id) {
        return service.read(id);
    }

    @DeleteMapping("/deletePrenotazione")
    public void  delete (@RequestParam Long id){
        service.delete(id);
    }

    //getall generica
    @GetMapping("/listaPrenotazione")
    public ResponseEntity<List<PrenotazioneDTO>> listaprenotazioni(){
        return new ResponseEntity<>(service.listaPrenotazioni(), HttpStatus.OK);
    }

    @GetMapping("/getAllPaginata")
    public ResponseEntity<Page<PrenotazioneDTO>> getallPageable(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber) {
        return new ResponseEntity<>(service.getallPageable(PageRequest.of(pageNumber, pageSize)), HttpStatus.OK);
    }

    @GetMapping("/findAllPageable")
    public Page <PrenotazioneDTO> findAllPageable (@RequestParam ("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber){
        return service.findAllPageable(PageRequest.of(pageNumber,pageSize));
    }

    @GetMapping("/findAllStoricoP")
    public ResponseEntity<Page<PrenotazioneDTO>> findByIdUserAndIdPista(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("idUser") long idUser,  @RequestParam("idPista") long idPista){
        return new ResponseEntity<>(service.findByIdUserAndIdPista(PageRequest.of(pageNumber, pageSize),idUser, idPista), HttpStatus.OK);
    }

    @GetMapping("/findAllStoricoD")
    public ResponseEntity<Page<PrenotazioneDTO>> findByIdUserAndDataInizioIsGreaterThanEqual(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("idUser") long idUser,  @RequestParam ("dataInizio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInizio){
        return new ResponseEntity<>(service.findByIdUserAndDataInizioIsGreaterThanEqual(PageRequest.of(pageNumber, pageSize),idUser, dataInizio), HttpStatus.OK);
    }
}
