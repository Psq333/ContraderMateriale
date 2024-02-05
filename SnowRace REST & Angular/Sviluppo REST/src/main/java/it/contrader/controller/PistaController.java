package it.contrader.controller;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PistaDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Impianto;
import it.contrader.model.User;
import it.contrader.service.ImpiantoService;
import it.contrader.service.PistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/pista")
@CrossOrigin(origins = "http://localhost:4200")
public class PistaController extends AbstractController<PistaDTO>{


    @Autowired
    private PistaService service;
    @GetMapping("/getAllPisteImpianto")
    public ResponseEntity<Page<PistaDTO>> getall(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("idImpianto") Long idImpianto ) {
        return new ResponseEntity<>(service.getAllPaginata(PageRequest.of(pageNumber, pageSize), idImpianto), HttpStatus.OK);
    }
    @DeleteMapping("/deletePista")
    public void deleteUser(@RequestParam("id") long id){
        service.deletePista(id);
    }
}
