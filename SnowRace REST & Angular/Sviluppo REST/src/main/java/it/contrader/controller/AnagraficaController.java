package it.contrader.controller;

import it.contrader.dto.AnagraficaDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;
import it.contrader.service.AnagraficaService;
import it.contrader.service.ImpiantoService;
import it.contrader.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anagrafica")
@CrossOrigin(origins = "http://localhost:4200")
public class AnagraficaController extends AbstractController<AnagraficaDTO>{
    @Autowired
    private AnagraficaService service;
    @Autowired
    private UserService userService;
    @GetMapping("/getUserWoAnag")
    public ResponseEntity<List<UserDTO>> getUserWoAnag(){
        return new ResponseEntity<List<UserDTO>>(service.getUserWoAnag(),HttpStatus.OK);
    }


    @PostMapping("/inserta")
    public AnagraficaDTO inserta (@RequestBody AnagraficaDTO dto, @RequestParam("id") Long userId) {
        System.out.println(dto.toString() + "---" + userId + "Aaaaaaaa");

        dto.setUser(userService.read(userId));
        System.out.println(dto.toString());
        return service.insert(dto);
    }

    @GetMapping("/findByUserId")
    public AnagraficaDTO findByUserId(@RequestParam("id") Long userId){
        return service.findByUserId(userId);
    }
}
