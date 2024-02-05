package it.contrader.controller;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ImpiantoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/impianto")
@CrossOrigin(origins = "http://localhost:4200")
public class ImpiantoController extends AbstractController<ImpiantoDTO>{

    @Autowired
    private ImpiantoService service;

    @GetMapping("/getalladmin")
    public Iterable<ImpiantoDTO> getAllAdmin(@RequestParam("username") String username){
        return service.getAllAdmin(username);
    }

    @PostMapping("/insert")
    public ImpiantoDTO insert (@RequestBody @Valid ImpiantoDTO dto) {
        System.out.println(dto.toString());
        return service.insert(dto);
    }
}