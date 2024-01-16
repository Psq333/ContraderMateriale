package it.contrader.controller;

import it.contrader.dto.AttrezzaturaDTO;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Impianto;
import it.contrader.service.AttrezzaturaService;
import it.contrader.service.ImpiantoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/attrezzatura")
public class AttrezzaturaController {

    @Autowired
    private AttrezzaturaService attrezzaturaService;
    @Autowired
    private ImpiantoService impiantoservice;

    private void setAll(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        request.setAttribute("impianti", impiantoservice.getAll(user));
        request.getSession().setAttribute("attrezzatura", this.attrezzaturaService.getAll());
    }


    @GetMapping("/getall")
    public String getAll(HttpServletRequest request) {
        setAll(request);
        return "/attrezzatura/getall";
    }


    @GetMapping("/getalluser") // serve semplicemente per farmi restituire la lista e rimandarla alla view dell'user(se mo lo chiedi, è per te paki)
    public String getAllUser(HttpServletRequest request, @RequestParam("idImpianto") Long idImpianto) {
        ImpiantoDTO impianto = impiantoservice.read(idImpianto);
        request.setAttribute("piste",impianto.getPiste());
        setAll(request);
        return "/attrezzatura/user_getall";
    }


    @GetMapping("/read")
    public String read(HttpServletRequest request, @RequestParam("idAttrezzatura") Long id) {
        request.getSession().setAttribute("attrezzaturaDTO", attrezzaturaService.read(id));
        return "/attrezzatura/read";
    }

    @PostMapping("/insert")
    public String insert(HttpServletRequest request,
                         @RequestParam("nomeAttrezzatura") String nome, @RequestParam("descrizioneAttrezzatura") String descrizione,
                         @RequestParam("prezzoAttrezzatura") double prezzo, @RequestParam("idImpianto") Impianto id_impianto) {
        AttrezzaturaDTO attrezzaturaDTO = AttrezzaturaDTO.builder()

                .nome(nome)
                .descrizione(descrizione)
                .prezzo(prezzo)
                .idImpianto (id_impianto)
                .build();
        this.attrezzaturaService.insert(attrezzaturaDTO);
        setAll(request);
        return "/attrezzatura/getall";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, @RequestParam("idAttrezzatura") Integer idAttrezzatura) {
        this.attrezzaturaService.delete(idAttrezzatura);
        setAll(request);
        return "attrezzatura/getall";
    }

    @GetMapping("/preupdate")
    public String preUpdate(HttpServletRequest request, @RequestParam("idAttrezzatura") Integer idAttrezzatura) {
        request.getSession().setAttribute("attrezzaturaDTO", this.attrezzaturaService.read(idAttrezzatura));
        return "/attrezzatura/attrezzatura/update";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request, @RequestParam("idAttrezzatura") Long idAttrezzatura,
                         @RequestParam("nomeAttrezzatura") String nome, @RequestParam("descrizioneAttrezzatura") String descrizione,
                         @RequestParam("prezzoAttrezzatura") double prezzo, @RequestParam("idImpianto") Impianto id_impianto) {
        AttrezzaturaDTO attrezzaturaDTO = AttrezzaturaDTO.builder()
                .idAttrezzatura(idAttrezzatura)
                .nome(nome)
                .descrizione(descrizione)
                .prezzo(prezzo)
                .idImpianto(id_impianto)
                .build();
        this.attrezzaturaService.update(attrezzaturaDTO);
        setAll(request);
        return "/attrezzatura/getall";

    }
















}