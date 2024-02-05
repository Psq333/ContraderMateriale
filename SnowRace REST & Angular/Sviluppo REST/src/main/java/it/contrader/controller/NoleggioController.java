package it.contrader.controller;

import it.contrader.dto.NoleggioDTO;
import it.contrader.service.AttrezzaturaService;
import it.contrader.service.NoleggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/noleggio")
@CrossOrigin(origins = "http://localhost:4200")
public class NoleggioController extends AbstractController<NoleggioDTO>{
    @Autowired
    private NoleggioService noleggioService;
    @Autowired
    private AttrezzaturaService attrezzaturaService;
    @PostMapping("/inserta")
    public NoleggioDTO inserta (@RequestBody NoleggioDTO dto, @RequestParam("id") Long attrezzaturaId) {
        System.out.println(dto.toString() + "---" + attrezzaturaId + "Aaaaaaaa");

        dto.setIdattrezzatura(attrezzaturaService.read(attrezzaturaId));
        System.out.println(dto.toString());
        return noleggioService.insert(dto);
    }

    @PutMapping("/updatee")
    public NoleggioDTO update (@RequestBody NoleggioDTO dto, @RequestParam("id") Long attrezzaturaId) {
        System.out.println(dto.toString() + "---" + attrezzaturaId + "Aaaaaaaa");

        dto.setIdattrezzatura(attrezzaturaService.read(attrezzaturaId));
        System.out.println(dto.toString());
        return noleggioService.insert(dto);
    }

    @GetMapping("/getAllPaginataUser")
    public ResponseEntity<Page<NoleggioDTO>> getalluser(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("username") String username ) {
        return new ResponseEntity<>(noleggioService.getAllPaginataUser(PageRequest.of(pageNumber, pageSize), username), HttpStatus.OK);
    }


}