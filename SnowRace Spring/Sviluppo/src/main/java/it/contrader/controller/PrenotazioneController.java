package it.contrader.controller;

import it.contrader.dto.PistaDTO;
import it.contrader.dto.PrenotazioneDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Pista;
import it.contrader.model.User;
import it.contrader.service.ImpiantoService;
import it.contrader.service.PistaService;
import it.contrader.service.PrenotazioneService;
import it.contrader.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;


@Controller
@RequestMapping("/prenotazione")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private ImpiantoService impiantoService;
    @Autowired
    private PistaService pistaService;
    @Autowired
    private UserService userService;


    @GetMapping("/getall")
    public String getAll(HttpServletRequest request) {
        setAll(request);
        return "/prenotazione/amministratore_prenotazione"; //restituisce la visualizzazione di tutte le prenotazioni
    }

  /*  @GetMapping("/read")
    public String read(HttpServletRequest request, @RequestParam("idPrenotazione") Long id) {
        request.getSession().setAttribute("PrenotazioneDTO", PrenotazioneService.read(id));
        return "...";  //qui ci va una JSP, ma questo metodo non lo stiamo usando, toglilo dall'href della jsp se la prendi da user
    }

   */

    @PostMapping("/insert")
    public String insert(HttpServletRequest request,
                         @RequestParam("idPista") Long idPista, @RequestParam("user") Long id_user, //@RequestParam "idPista" quello che ho anche nella jsp, long idpista è quello assegnato
                         @RequestParam("dataInizio") Date dataInizio, @RequestParam("dataFine") Date dataFine) {
        UserDTO user = (UserDTO)request.getSession().getAttribute("user");
        PrenotazioneDTO prenotazioneDTO = PrenotazioneDTO.builder()
                .pista(pistaService.read(idPista)) //primo nome quello del dto secondo quello qui
                .user(userService.read(id_user))
                .dataInizio(dataInizio)
                .dataFine(dataFine)
                .build();
        this.prenotazioneService.insert(prenotazioneDTO);
        setAll(request);
        if(user.getUsertype().equals(User.Usertype.USER))
            return "/homeuser";
        else return "redirect:/prenotazione/getall";

    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, @RequestParam("idPrenotazione") Long IdPrenotazione) {
        this.prenotazioneService.delete(IdPrenotazione);
        setAll(request);
        if (request.getParameter("source") != null){
            request.setAttribute("users", prenotazioneService.usersWPren());
            return "/prenotazione/ricerca";}
        return "redirect:/prenotazione/getall";
    }

    @GetMapping("/preupdate")
    public String preUpdate(HttpServletRequest request, @RequestParam("idPrenotazione") Long idPrenotazione){
        if (request.getParameter("source") != null)
            request.setAttribute("source", "/prenotazione/ricerca");
        request.setAttribute("impianti", this.impiantoService.getAll());
        request.setAttribute("prenotazioni", this.prenotazioneService.read(idPrenotazione));
    return "/prenotazione/update_prenotazione";}

    @PostMapping("/update")
    public String update(HttpServletRequest request, @RequestParam("idPrenotazione") Long idPrenotazione,
                         @RequestParam("idPista")Long idPista, @RequestParam("user") Long user,
                         @RequestParam("dataInizio") Date dataInizio, @RequestParam("dataFine") Date dataFine) {
        PrenotazioneDTO prenotazioneDTO = PrenotazioneDTO.builder()
                .idPrenotazione(idPrenotazione)
                .pista(pistaService.read(idPista))
                .user(userService.read(user)) //leggo a partire dal suo id e gli do il DTO che vuole comunque perchè è di tipo DTO
                .dataInizio(dataInizio)
                .dataFine(dataFine)
                //.amministratore(userService.read(amministratore))
                .build();
        this.prenotazioneService.update(prenotazioneDTO);
        setAll(request);
        if (request.getParameter("source")!=null){
           //String a = request.getParameter("source");
            request.setAttribute("users", prenotazioneService.usersWPren());
            return "/prenotazione/ricerca";
        }

    return "redirect:/prenotazione/getall";


    }


    @GetMapping ("/prenUser")
    public String user_prenotazione (HttpServletRequest request, @RequestParam("idImpianto") Long idImpianto,@RequestParam("idPista") Long idPista){
        request.setAttribute("impiantoDTO", impiantoService.read(idImpianto));
        request.setAttribute("pistaDTO", pistaService.read(idPista));
        return "/prenotazione/insert_prenotazione";}
        //chiamato dalle Piste

    @GetMapping("/presearch")
    public String presearch(HttpServletRequest request){
        //if (((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().equals("AMMINISTRATORE"))
          // setAll(request);
         //else
        request.setAttribute("users", prenotazioneService.usersWPren());
        request.setAttribute("prenotazioni", prenotazioneService.getAll((UserDTO)request.getSession().getAttribute("user")));
        return "/prenotazione/ricerca";
    }
    @GetMapping("/searchByDate")
    public String searchByDate (HttpServletRequest request, @RequestParam("which") Date which, @RequestParam("who") String who){
        //if (!((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().equals("AMMINISTRATORE")){
            //UserDTO user = userService.read(who);
        //}
        //Long idUser = userService.findByUsername(who).getId();
        request.setAttribute("users", prenotazioneService.usersWPren());
        request.setAttribute("prenotazioni", prenotazioneService.searchByDate(which, who));
        return "/prenotazione/ricerca";
    }

    @GetMapping("/searchByPista")
    public String searchByPista (HttpServletRequest request, @RequestParam("which") String which, @RequestParam("who") String who){
        //Long idUser = userService.findByUsername(who).getId();
        //PistaDTO pistaDTO = pistaService.findByNome(which);
        //Long pista = null;
        //if (pistaDTO != null)
        //    pista = pistaDTO.getIdPista();
        request.setAttribute("users", prenotazioneService.usersWPren());
        request.setAttribute("prenotazioni", prenotazioneService.searchByPista(which, who));
        return "/prenotazione/ricerca";
    }

    private void setAll(HttpServletRequest request) {
        request.setAttribute("prenotazioni", this.prenotazioneService.getAll()); //inserisce prenotazioni generate dalla get all del Serv nella request con il nome prenotazioni
        request.setAttribute("impianti", this.impiantoService.getAll());
        request.setAttribute("user", this.userService.getAll());
    } //la get all che fa tramite il service la va ad inserire in "prenotzioni" e me la restituisce sopra in questa classe alla get all
    //invece che iniettare il service di impianti qui farlo nel service di prenotazione
    //qui nel set all: request.setAttribute("impianti", this.prenotazioneService.getAllImpianti());
    //in Prenotazione service getAllImpianti(){impiantoService.getall();}
}
