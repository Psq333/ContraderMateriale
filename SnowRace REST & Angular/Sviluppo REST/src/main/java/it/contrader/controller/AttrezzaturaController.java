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
import it.contrader.dto.AttrezzaturaDTO;
import it.contrader.service.AttrezzaturaService;
@RestController
@RequestMapping("/attrezzatura")
@CrossOrigin(origins = "http://localhost:4200")
public class AttrezzaturaController extends AbstractController<AttrezzaturaDTO>{
    @Autowired
    private AttrezzaturaService attrezzaturaService;


    @DeleteMapping("/deleteAttrezzatura")
    public void deleteAttrezzatura(@RequestParam("id") long id){
        attrezzaturaService.deleteAttrezzatura(id);
    }
}
