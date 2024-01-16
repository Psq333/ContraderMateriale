package it.contrader.controller;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;
import it.contrader.service.ImpiantoService;
import it.contrader.service.UserService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/impianto")
public class ImpiantoController {

    @Autowired
    private ImpiantoService impiantoService;
    @Autowired
    private  UserService userService;


    @GetMapping("/getall")
    public String getAll(HttpServletRequest request) {
        setAll(request);
        List<UserDTO> list = userService.findByUsertype(User.Usertype.ADMIN);
        request.setAttribute("amministratori", list);
        return "/impianto/getall_inserimento";
    }

    @GetMapping("/getalluser")
    public String getAllUser(HttpServletRequest request) {
        setAll(request);
        List<UserDTO> list = userService.findByUsertype(User.Usertype.ADMIN);
        request.setAttribute("amministratori", list);
        return "/impianto/getall_user";
    }



    @GetMapping("/read")
    public String read(HttpServletRequest request, @RequestParam("idImpianto") Long id) {
        request.setAttribute("impiantoDTO", impiantoService.read(id));
        return "/impianto/leggi";
    }

    @PostMapping("/insert")
    public String insert(HttpServletRequest request,
                         @RequestParam("nomeImpianto") String nome, @RequestParam("descrizioneImpianto") String descrizione,
                         @RequestParam("luogoImpianto") String luogo, @RequestParam("amministratoreImpianto") Long amministratore) {

        ImpiantoDTO impiantoDTO = ImpiantoDTO.builder()
                .nome(nome)
                .descrizione(descrizione)
                .luogo(luogo)
                .amministratore(userService.read(amministratore))
                .build();
        this.impiantoService.insert(impiantoDTO);
        setAll(request);
        return "redirect:/impianto/getall";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, @RequestParam("idImpianto") Long idImpianto) {
        this.impiantoService.delete(idImpianto);
        setAll(request);
        return "redirect:/impianto/getall";
    }

    @GetMapping("/preupdate")
    public String preUpdate(HttpServletRequest request, @RequestParam("idImpianto") Long idImpianto) {
        List<UserDTO> list = userService.findByUsertype(User.Usertype.ADMIN);
        request.setAttribute("amministratori", list);
        request.setAttribute("impiantoDTO", this.impiantoService.read(idImpianto));
        return "/impianto/modifica";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request, @RequestParam("idImpianto") Long idImpianto,
                         @RequestParam("nomeImpianto") String nome, @RequestParam("descrizioneImpianto") String descrizione,
                         @RequestParam("luogoImpianto") String luogo, @RequestParam("amministratoreImpianto") Long amministratore) {
        ImpiantoDTO impiantoDTO = ImpiantoDTO.builder()
                .idImpianto(idImpianto)
                .nome(nome)
                .descrizione(descrizione)
                .luogo(luogo)
                .amministratore(userService.read(amministratore))
                .build();
        this.impiantoService.update(impiantoDTO);
        setAll(request);
        return "redirect:/impianto/getall";

    }

    private void setAll(HttpServletRequest request) {
        List<ImpiantoDTO> list = this.impiantoService.getAll((UserDTO) request.getSession().getAttribute("user"));
        request.setAttribute("impianti", list);
    }
}