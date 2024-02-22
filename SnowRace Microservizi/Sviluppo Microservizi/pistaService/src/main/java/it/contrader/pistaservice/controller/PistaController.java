package it.contrader.pistaservice.controller;

import it.contrader.pistaservice.dto.PistaDTO;
import it.contrader.pistaservice.service.PistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pista")
public class PistaController {

    @Autowired
    private PistaService service;

    @GetMapping("/getAllPaginata")
    public ResponseEntity<?> getall(@RequestParam("pageSize")int pageSize, @RequestParam("pageNumber") int pageNumber){
        return new ResponseEntity<>(service.getall(PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }
    @GetMapping("/getall")
    public List<PistaDTO> getallPiste (){
        return service.getallPiste();
    }

    @GetMapping("/getallimpiantiadmin")
    public ResponseEntity<?> getallimpiantiadmin(@RequestParam("pageSize")int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("amministratore") String username){
        return ResponseEntity.ok().body(service.getallpisteimpianto(PageRequest.of(pageNumber,pageSize), username));
    }

    @GetMapping("/getallimpiantiadminlist")
    public ResponseEntity<?> getallimpiantiadminlist(@RequestParam("username") String username){
        return ResponseEntity.ok().body(service.getallpisteimpiantolist(username));
    }

    @GetMapping("/getAllPisteImpianto")
    public ResponseEntity<Page<PistaDTO>> getallPisteImpianto(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("id") int idImpianto ) {
        Long l = Long.parseLong(String.valueOf(idImpianto));
        return new ResponseEntity<>(service.getallPisteImpianto(PageRequest.of(pageNumber, pageSize), l), HttpStatus.OK);
    }

    @GetMapping("/read")
    public PistaDTO read(@RequestParam("id") long id){
        return service.read(id);
    }

    @PostMapping("/insert")
    public PistaDTO insert(@RequestBody PistaDTO pistaDTO) {
        return service.insert(pistaDTO);
    }

    @PutMapping("/update")
    public PistaDTO update(@RequestBody PistaDTO pistaDTO){
        return service.update(pistaDTO);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") long id){
        service.delete(id);
    }



}
