package it.contrader.impiantoservice.controller;

import it.contrader.impiantoservice.dto.ImpiantoDTO;
import it.contrader.impiantoservice.dto.ImpiantoUserDTO;
import it.contrader.impiantoservice.service.ImpiantoService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/impianto")
public class ImpiantoController {

    @Autowired
    private ImpiantoService service;

    @GetMapping("/getall")
    public ResponseEntity<Page<ImpiantoUserDTO>> getAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber) {
        return new ResponseEntity<>(service.getAll(PageRequest.of(pageNumber, pageSize)),HttpStatus.OK);
    }

    @GetMapping("/getAllPaginata")
    public ResponseEntity<Page<ImpiantoUserDTO>> getAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("username") String username ) {
        return new ResponseEntity<>(service.getAll(PageRequest.of(pageNumber, pageSize), username),HttpStatus.OK);
    }

    @GetMapping("/listaimpianti")
    public ResponseEntity<List<ImpiantoDTO>> listaimpianti(){
        return new ResponseEntity<>(service.listaImpianti(), HttpStatus.OK);
    }



    @GetMapping("/listaimpiantiadmin")
    public ResponseEntity<?> listaimpiantiadmin(@RequestParam("amministratore") Long id){
        return new ResponseEntity<>(service.listaImpiantiadmin(id), HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<ImpiantoDTO> read(@RequestParam("id") Long id){
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<ImpiantoDTO> insert(@RequestBody @Validated ImpiantoDTO impiantoDTO){
        return new ResponseEntity<>(service.save(impiantoDTO), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ImpiantoDTO> update(@RequestBody @Validated ImpiantoDTO impiantoDTO){
        return new ResponseEntity<>(service.save(impiantoDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id){
        service.delete(id);
    }
}
