package it.contrader.attrezzaturaService.controller;

import it.contrader.attrezzaturaService.dto.AttrezzaturaDTO;
import it.contrader.attrezzaturaService.service.AttrezzaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/attrezzatura")
public class AttrezzaturaController {

    @Autowired
    private AttrezzaturaService service;

    @PostMapping("/insert")
    public AttrezzaturaDTO insert (@RequestBody @Valid AttrezzaturaDTO dto) {
        System.out.println(dto.toString());
        return service.insert(dto);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") long id){
        service.delete(id);
    }

    @PutMapping("/update")
    public AttrezzaturaDTO update (@RequestBody @Valid AttrezzaturaDTO dto){
        return service.update(dto);
    }

    @GetMapping("/getAllPaginata")
    public ResponseEntity<?> getAllPaginata(@RequestParam("pageSize")int pageSize, @RequestParam("pageNumber") int pageNumber){
        return new ResponseEntity<>(service.getall(PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }

    @GetMapping("/read")
    public AttrezzaturaDTO read(@RequestParam("id") long id) {
        return service.read(id);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getall(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

}

