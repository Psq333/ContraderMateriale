package it.contrader.noleggioservice.controller;

import it.contrader.noleggioservice.dto.NoleggioDTO;
import it.contrader.noleggioservice.dto.NoleggiosDTO;
import it.contrader.noleggioservice.service.NoleggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noleggio")
public class NoleggioController {

    @Autowired
    private NoleggioService service;

    @PostMapping("/insertNoleggio")
    public ResponseEntity<NoleggioDTO> insert (@RequestBody NoleggiosDTO noleggioDTO) {
        return new ResponseEntity<>(service.save(noleggioDTO), HttpStatus.OK);
    }
    @PutMapping("/updateNoleggio")
    public ResponseEntity <NoleggioDTO> update (@RequestBody NoleggiosDTO noleggioDTO) {
        return new ResponseEntity<>(service.save(noleggioDTO), HttpStatus.OK);
    }


//    @PutMapping("/updNoleggio")
//    public ResponseEntity <NoleggioDTO> update (@RequestBody NoleggioDTO noleggioDTO) {
//        return new ResponseEntity<>(service.save(noleggioDTO), HttpStatus.OK);
//    }


    @GetMapping("/readNoleggio")
    public  NoleggioDTO read (@RequestParam Long id) {
        return service.read(id);
    }

    @DeleteMapping("/deleteNoleggio")
    public void  delete (@RequestParam Long id){
        service.delete(id);
    }

    @GetMapping("/listaNoleggi")
    public ResponseEntity<List<NoleggioDTO>> listaNoleggi(){
        return new ResponseEntity<>(service.listaNoleggi(), HttpStatus.OK);
    }

    @GetMapping("/getAllPaginata")
        public ResponseEntity<Page<NoleggioDTO>> getallPageable(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber) {
        return new ResponseEntity<>(service.getallPageable(PageRequest.of(pageNumber, pageSize)), HttpStatus.OK);
    }

    @GetMapping("/getAllPaginataUser")
        public ResponseEntity<Page<NoleggioDTO>> getallPageable(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("username") String username) {
        return new ResponseEntity<>(service.getAllPaginataUser(PageRequest.of(pageNumber, pageSize), username), HttpStatus.OK);
    }

    @GetMapping("/findAllPageable")
    public Page <NoleggioDTO> findAllPageable (@RequestParam ("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber){
        return service.findAllPageable(PageRequest.of(pageNumber,pageSize));
    }
}
