package it.contrader.controller;


import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PistaDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Impianto;
import it.contrader.model.User;
import it.contrader.service.ImpiantoService;
import it.contrader.service.PistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/pista")
public class PistaController {

    @Autowired
    private PistaService pistaService;
    @Autowired
    private  ImpiantoService impiantoService;

    @GetMapping("/getall")
    public String getAll(HttpServletRequest request) {
        setAll(request);
        return "/pista/getall";
    }

    @GetMapping("/getalluser") // serve semplicemente per farmi restituire la lista e rimandarla alla view dell'user(se mo lo chiedi, è per te paki)
    public String getAllUser(HttpServletRequest request, @RequestParam("idImpianto") Long idImpianto) {
        ImpiantoDTO impianto = impiantoService.read(idImpianto);
        List<PistaDTO> p = impianto.getPiste();
        request.setAttribute("piste",p);
        setAll(request);
        return "/pista/user-getall";
    }

    @GetMapping("/getalladmin") // serve semplicemente per farmi restituire la lista di un admin e rimandarla alla view dell'user(se mo lo chiedi, è per te paki)
    public String getAllAdmin(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        request.setAttribute("impianti",impiantoService.getAll(user));
        request.setAttribute("pisteAdmin", this.pistaService.findByImpianto_Amministratore_Id(user.getId()));
        return "/pista/admin-getall";
    }

    @GetMapping("/read")
    public String read(HttpServletRequest request, @RequestParam("idPista") Long id) {
        request.setAttribute("pistaDTO", pistaService.read(id));
        return "/pista/lettura";
    }

    @PostMapping("/insert")
    public String insert(HttpServletRequest request,
                         @RequestParam("nomePista") String nome,
                         @RequestParam("difficoltaPista") String difficolta,
                         @RequestParam("prenMax") int prenotazioni,
                         @RequestParam("prezzoPista") double prezzo,
                         @RequestParam("idImpianto") Long id_impianto) {
        PistaDTO pistaDTO = PistaDTO.builder()
                .nome(nome)
                .difficolta(difficolta)
                .prezzo(prezzo)
                .prenotazioniMax(prenotazioni)
                .impianto(id_impianto)
                .build();
        this.pistaService.insert(pistaDTO);
        setAll(request);
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        request.setAttribute("impianti",impiantoService.getAll(user));
        if(user.getUsertype().equals(User.Usertype.ADMIN))
            return "redirect:/pista/getalladmin";
        else return "/pista/getall";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, @RequestParam("idPista") Integer idPista) {
        this.pistaService.delete(idPista);
        setAll(request);
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if(user.getUsertype().equals(User.Usertype.ADMIN))
            return "redirect:/pista/getalladmin";
        else return "/pista/getall";
    }

    @GetMapping("/preupdate")
    public String preUpdate(HttpServletRequest request,
                @RequestParam("idPista") Integer idPista) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        request.setAttribute("impianti",impiantoService.getAll(user));
        request.getSession().setAttribute("pistaDTO", this.pistaService.read(idPista));
        return "/pista/modifica";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request,
                 @RequestParam("idPista") Long idPista,
                 @RequestParam("nomePista") String nome,
                 @RequestParam("difficoltaPista") String difficolta,
                 @RequestParam("prezzoPista") double prezzo,
                 @RequestParam("prenMax") int prenotazioni,
                 @RequestParam("idImpianto") Long id_impianto) {
        PistaDTO pistaDTO = PistaDTO.builder()
                .idPista(idPista)
                .nome(nome)
                .difficolta(difficolta)
                .prezzo(prezzo)
                .prenotazioniMax(prenotazioni)
                .impianto(id_impianto)
                .build();
        this.pistaService.update(pistaDTO);
        setAll(request);
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if(user.getUsertype().equals(User.Usertype.ADMIN))
            return "redirect:/pista/getalladmin";
        else return "/pista/getall";

    }

    private void setAll(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        request.setAttribute("impianti",impiantoService.getAll(user));
        request.getSession().setAttribute("piste", this.pistaService.getAll());
    }

}
