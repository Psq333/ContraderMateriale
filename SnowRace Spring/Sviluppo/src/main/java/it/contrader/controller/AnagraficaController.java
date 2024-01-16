package it.contrader.controller;

import it.contrader.dto.AnagraficaDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;
import it.contrader.service.AnagraficaService;
import it.contrader.service.UserService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller
@RequestMapping("/anagrafica")
public class AnagraficaController {

    @Autowired
    private AnagraficaService service;
    @Autowired
    private UserService userService;


    @GetMapping("/getall")
    public String getAll(HttpServletRequest request) {
        setAll(request);
        return "/anagrafica/manager";
    }

    //@GetMapping("/read")
    //public String read(HttpServletRequest request, @RequestParam("id") Long iduser) {
     //   request.setAttribute("anagraficaDTO", service.findByUser(iduser));
      //  return "/user/readuser";
    //}

    @PostMapping("/insert")
    public String insert(HttpServletRequest request,
                         @RequestParam("nome") String nome, @RequestParam("cognome") String cognome, @RequestParam("indirizzo") String indirizzo,
                         @RequestParam("data") Date dataNascita, @RequestParam("luogo") String luogoNascita, @RequestParam("user") Long user) {
        AnagraficaDTO anagraficaDTO = AnagraficaDTO.builder()
                .nome(nome)
                .cognome(cognome)
                .indirizzo(indirizzo)
                .dataNascita(dataNascita)
                .luogoNascita(luogoNascita)
                .user(userService.read(user))
                .build();
        this.service.insert(anagraficaDTO);
        setAll(request);
        if (((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().equals("AMMINISTRATORE"))
            return "/anagrafica/manager";
        else {
            String link = "/home" + ((UserDTO) request.getSession().getAttribute("user")).getUsertype().name().toLowerCase();
            return link;
        }
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
        this.service.delete(id);
        setAll(request);
        return "/anagrafica/manager";
    }

    @GetMapping("/preupdate")
    public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
        request.setAttribute("anagraficaDTO", this.service.read(id));
        if (request.getParameter("source")!=null)
            request.setAttribute("source", request.getParameter("source"));
        return "/anagrafica/update";
    }

    @GetMapping("/preinsert")
    public String preInsert(HttpServletRequest request) {
        return "/anagrafica/insert";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request, @RequestParam("id") Long id,
                         @RequestParam("nome") String nome, @RequestParam("cognome") String cognome, @RequestParam("indirizzo") String indirizzo,
                         @RequestParam("data") Date dataNascita, @RequestParam("luogo") String luogoNascita, @RequestParam("user") long user) {
        AnagraficaDTO anagraficaDTO = AnagraficaDTO.builder()
                .id(id)
                .nome(nome)
                .cognome(cognome)
                .indirizzo(indirizzo)
                .dataNascita(dataNascita)
                .luogoNascita(luogoNascita)
                .user(userService.read(user))
                .build();
        this.service.update(anagraficaDTO);
        if (((UserDTO) request.getSession().getAttribute("user")).getUsertype().name().equals("AMMINISTRATORE")) {
            setAll(request);

            if ("readuser".equals(request.getParameter("source"))) {
                request.setAttribute("dto", userService.read(user));
                request.setAttribute("anag", anagraficaDTO);
                return "/user/readuser";
            } else {
                return "/anagrafica/manager";
            }

        } else {
            request.setAttribute("dto", userService.read(user));
            request.setAttribute("anag", anagraficaDTO);
            return "/user/readuser";
        }
    }





    private void setAll(HttpServletRequest request) {
        request.setAttribute("users", userService.getAll());
        request.setAttribute("anagraficaDTO", this.service.getAll());
    }
}
