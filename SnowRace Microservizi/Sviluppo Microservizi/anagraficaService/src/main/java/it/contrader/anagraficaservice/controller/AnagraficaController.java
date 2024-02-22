package it.contrader.anagraficaservice.controller;

import it.contrader.anagraficaservice.dto.AnagraficaDTO;
import it.contrader.anagraficaservice.service.AnagraficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anag")
public class AnagraficaController {

    @Autowired
    private AnagraficaService service;

    @PostMapping("/insert")
    public AnagraficaDTO register(@RequestBody AnagraficaDTO anagraficaDTO, @RequestParam("id")Long id) {
        return service.save(anagraficaDTO, id);
    }

    @GetMapping("/getAll")
    public List<AnagraficaDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/read")
    public ResponseEntity<?> read(@RequestParam("id") Long id){
        return ResponseEntity.ok()
                .body(service.read(id));
    }

    @GetMapping("/getAllPaginata")
    public ResponseEntity<?> getAllPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return ResponseEntity.ok()
                .body(service.getAllPage(pageNumber, pageSize));
    }

    @GetMapping("/getUserWoAnag")
    public ResponseEntity<?> getUserWoAnag(){
        return ResponseEntity.ok()
                .body(service.getUserWoAnag());
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<?> findByUserId(@RequestParam("id") Long id){
        return ResponseEntity.ok()
                .body(service.findByUserId(id));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id){
        service.delete(id);
        //return ResponseEntity.ok(("User deleted successfully!"));
    }
}
